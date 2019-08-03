package example;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class KnowledgeInstaller {

    public static void main(final String[] args) throws Exception {
        File knityHome = new File(System.getProperty("knity.home", "."));
        long old = System.currentTimeMillis();
        System.out.println("Knowledge installing");
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Arg(Knowledge Base File Path) is required.");
        File file = new File(args[0]);  // 知识文件路径/abc/k.data
        if (!file.exists()) {
            final File file1 = file;
            File[] listFiles = file.getParentFile().listFiles(new FilenameFilter() { // 获取文件夹/abc中的文件，名称以k.data结尾
                public boolean accept(File dir, String name) {
                    return name.endsWith(file1.getName());
                }
            });
            if (listFiles.length == 0) {
                throw new IllegalArgumentException("Knowledge Base File NOT Valid.");
            }
            Arrays.sort(listFiles, new Comparator<File>() {

                public int compare(File o1, File o2) {
                    return (int) (o2.lastModified() - o1.lastModified());  // 获取最新的文件文件
                }
            });
            file = listFiles[0];
        }
        System.out.println("::start to install file : " + file.getAbsolutePath());
        byte[] body = FileUtils.readFileToByteArray(file);  // 获取文件的字节数组
        if (body == null || body.length == 0) throw new IllegalArgumentException("Knowledge Base File is empty.");
        byte[] moduleBytes = decrypt(body); // 对文件进行解码
        if (!knityHome.exists())
            throw new IllegalStateException("KnityHome dir is not found.(" + knityHome.getAbsolutePath() + ")");
        if (knityHome.getName().equals("app")) knityHome = knityHome.getParentFile();
        File appDir = new File(knityHome, "app");    // engine8/app
        File faqDir = new File(appDir, "faq");      // engine8/app/fap
        File isegDir = new File(appDir, "iseg");    // engine8/app/iseg
        File knowledgeInstallDir = new File(knityHome, "KnowledgeInstall");// engine8/KnowledgeInstall
        File workingDir = decompress(knowledgeInstallDir, moduleBytes);     // workingDir：engine8/KnowledgeInstall/很多的zip文件
        String datestr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        List<File> files = new ArrayList<File>(Arrays.asList(workingDir.listFiles())); // 获取workingDir中的zip文件集合
        Collections.sort(files, new Comparator<File>() { // 文件进行排序

            public int compare(File o1, File o2) {
                if (o1.getName().equals("ds.zip"))
                    return -1;
                if (o2.getName().equals("ds.zip"))
                    return 1;
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (File f : files) {  // .data文件解码之后的zip文件集合
            if (f.isFile() && f.getName().endsWith(".zip")) {  // 处理zip文件
                File moduleDir = decompress(workingDir, FileUtils.readFileToByteArray(f)); // 解压获取模块文件，$ibotui
                if (moduleDir.getName().equals("wordclass")) {      // 文件名是wordclass -- 移动到engine8/app/wordclass
                    File oldWordClassDir = new File(appDir, "wordclass");
                    if (oldWordClassDir.exists())
                        FileUtils.moveDirectory(oldWordClassDir, new File(appDir, "wordclass_backup_" + datestr));
                    FileUtils.moveDirectory(moduleDir, oldWordClassDir);
                } else if (moduleDir.getName().equals("dict")) { // dict -- engine8/app/iseg/dict
                    File oldDictDir = new File(isegDir, "dict");
                    if (oldDictDir.exists())
                        FileUtils.moveDirectory(oldDictDir, new File(isegDir, "dict_backup_" + datestr));
                    FileUtils.moveDirectory(moduleDir, oldDictDir);
                } else if (f.getName().endsWith("ds.zip") && moduleDir != null && moduleDir.exists()) { // ds.zip -- engine8/app/ds
                    moduleDir = new File(workingDir, "ds"); // engine8/KnowledgeInstall/ds
                    File oldModuleDir = new File(appDir, "ds"); // engine8/app/ds
                    //验证licence是否一致
                    File authfile = new File(moduleDir, "auth/validation.data"); // engine8/KnowledgeInstall/ds/auth/validation.data
                    if (!authfile.exists()) {
                        System.out.println("ERROR:auth file not found");
                        System.exit(1);
                    }
                    File licenceFile = null;
                    URL resource = KnowledgeInstaller.class.getResource("/conf/AI_LICENCE_KEY");
                    if (resource != null) {
                        licenceFile = new File(resource.getFile());
                        System.out.println("licence file:" + licenceFile);
                    }
                    if (licenceFile == null) {
                        File licenceFolder = knityHome;
                        do {//考虑cluster模式可能会统一放在上层目录
                            File _licenceFile = new File(licenceFolder, "conf/AI_LICENCE_KEY");
                            if (_licenceFile.exists()) {
                                licenceFile = _licenceFile;
                                break;
                            }
                        } while ((licenceFolder = licenceFolder.getParentFile()) != null);
                    }
                    if (licenceFile == null) {
                        //寻找类路径的licence
                        System.out.println("ERROR:licence file not found");
                        System.exit(1);
                    }
                    byte[] authdata = FileUtils.readFileToByteArray(authfile);
                    byte[] licenceData = FileUtils.readFileToByteArray(licenceFile);
                    if (!Arrays.equals(authdata, licenceData) && !Boolean.getBoolean("dontCheckLicence")) {
                        System.out.println("ERROR:licence not match");
                        System.exit(1);
                    }
                    if (oldModuleDir.exists())
                        FileUtils.moveDirectory(oldModuleDir, new File(appDir, "ds_backup_" + datestr));
                    FileUtils.moveDirectory(moduleDir, oldModuleDir);
                } else if (f.getName().startsWith("__")) {  // __.zip  -- engine8/app/
                    String name = f.getName().substring(0, f.getName().length() - ".zip".length()).replace("__", "/");
                    File oldDir = new File(appDir, name);
                    if (oldDir.exists())
                        FileUtils.moveDirectory(oldDir, new File(oldDir.getParentFile(), oldDir.getName() + "_backup_" + datestr));
                    FileUtils.moveDirectory(moduleDir, oldDir);
                } else {
                    File oldModuleDir = new File(faqDir, moduleDir.getName());
                    if (oldModuleDir.exists())  // 其他.zip -- engine8/app/fap/其他（文件夹）
                        FileUtils.moveDirectory(oldModuleDir, new File(faqDir, moduleDir.getName() + "_backup_" + datestr));
                    FileUtils.moveDirectory(moduleDir, oldModuleDir);
                }
            } else {//其他单独的文件，非目录
                if (f.getName().startsWith("__")) {  // __开头文件 -- engine8/app/文件名（文件夹）
                    String name = f.getName().replace("__", "/");
                    File oldFile = new File(appDir, name);
                    if (oldFile.exists())
                        FileUtils.moveFile(oldFile, new File(oldFile.getParentFile(), oldFile.getName() + "_backup_" + datestr));
                    FileUtils.moveFile(f, oldFile);
                }
            }
        }
        File mark = new File(appDir, "PRESET_INSTALLED");
        mark.createNewFile();
        System.out.println("Knowledge installed in " + (System.currentTimeMillis() - old) + "ms");
    }

    public static void main111(String[] args) throws Exception {
        byte[] testBytes = FileUtils.readFileToByteArray(new File("/Users/libo/Downloads/knowledge_test.zip"));
        decompress(new File("/Users/libo/Downloads/"), testBytes);
    }

    private static File decompress(File dir, byte[] moduleBytes) {

        if (!dir.exists()) dir.mkdirs();

        File retDir = null;

        ZipInputStream zipIn = null;
        try {
            zipIn = new ZipInputStream(new ByteArrayInputStream(moduleBytes));

            ZipEntry entry = null;
            while ((entry = zipIn.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    System.out.println("Decompressing " + entry.getName() + " (" + entry.getSize() + ")");
                    byte[] buf = null;
                    if (entry.getSize() == -1) {
                        ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
                        IOUtils.copy(zipIn, bufOut);
                        bufOut.flush();
                        buf = bufOut.toByteArray();
                    } else {
                        buf = new byte[(int) entry.getSize()];
                        int readBytes = readBytes(zipIn, buf);
                        if (readBytes < buf.length) {
                            throw new IOException("Could not completely read jar entry " + entry.getName() + "(" + readBytes + "/" + buf.length + ")");
                        }
                    }
                    File newFile = new File(dir, entry.getName());
                    File currentDir = newFile.getParentFile();
                    currentDir.mkdirs();
                    if (retDir == null || currentDir.getAbsolutePath().length() < retDir.getAbsolutePath().length()) {
                        retDir = currentDir;
                    }
                    newFile.createNewFile();
                    FileUtils.writeByteArrayToFile(newFile, buf);
                }
            }
            zipIn.close();
            zipIn = null;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zipIn != null) {
                try {
                    zipIn.close();
                } catch (Throwable t) {
                }
            }
        }

        return retDir;
    }

    private static byte[] decrypt(byte[] src) throws Exception {

        byte[] ivBytes = new byte[8];
        System.arraycopy(src, 0, ivBytes, 0, 8);

        byte[] cipherBytes = new byte[src.length - 8];
        System.arraycopy(src, 8, cipherBytes, 0, cipherBytes.length);

        String keystr = String.valueOf(Math.E);
        byte[] key = keystr.substring(2, 10).getBytes();

        SecretKeySpec skey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, skey, iv);

        return cipher.doFinal(cipherBytes);
    }

    private static int readBytes(InputStream in, byte[] bytes) throws IOException {
        int totalRead = 0;
        int numRead = 0;
        while (totalRead < bytes.length
                && (numRead = in.read(bytes, totalRead, bytes.length - totalRead)) >= 0) {
            totalRead += numRead;
        }
        return totalRead;
    }

    @Test
    public void installTest() throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:90/testExt.action");
        httpPost.setConfig(requestConfig);

        File file = new File("E:\\g_knowledge20190528115809.data");

        FileEntity fileEntity = new FileEntity(file);
        fileEntity.setContentType("multipart");
        httpPost.setEntity(fileEntity);

        httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        int statusCode= httpResponse.getStatusLine().getStatusCode();
        if(statusCode == 200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            StringBuffer buffer = new StringBuffer();
            String str = "";
            while(!StringUtils.isEmpty(str = reader.readLine())) {
                buffer.append(str);
            }
            System.out.println(buffer.toString());
        }

        httpClient.close();
        if(httpResponse!=null){
            httpResponse.close();
        }
    }
}
