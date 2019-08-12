package example;

import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * properties文件的读取方式，以及编码原则
 * 1.class.getResourceAsStream -- classpath下的文件要以绝对路径"/"开始
 * 2.PropertiesLoaderUtils.loadAllResource -- 本质上还是通过方式1处理，classpath下的文件不能以绝对路径"/"开始
 * 3.ResourceBundle.getBundle -- classpath下的文件不能以绝对路径"/"开始
 *
 * 注：通过1.2.3读取配置文件，到输出中文乱码解决方式的分析
 * 正常流程： 假定properties文件默认编码就是ISO-8859-1
 *           或者properties文件在java中的getResourceAsStream方式读取默认就是ISO-8859-1编码
 *           1.2.3读取到配置文件之后，然后解码（ISO-8859-1）-编码（GBK）解决了乱码问题
 *
 * 疑问点：文件编辑的时候已经有一个编码，
 *        如idea中的配置test.properties文件编码为GBK，
 *        getResourceAsStream读取按照ISO-8859-1，
 *        解决乱码方式：解码（ISO-8859-1）-编码（GBK）
 * 结论：文件编码（GBK）-- 文件读取（ISO-8859-1）-- 解码（ISO-8859-1）-- 编码（GBK）
 * 产生疑问原因：老认为的错误的顺序是：GBK--ISO-8859-1--GBK--ISO-8859-1
 */
public class PropertiesTest {

    @Test
    public void test1() throws Exception{
        // 第一种方式：class.getResourceAsStream()
        InputStream rs = PropertiesTest.class.getResourceAsStream("/prop/test.properties");
        // 直接读取中文，乱码，处理中文乱码
        InputStreamReader streamReader = new InputStreamReader(rs, "GBk");

        Properties properties = new Properties();
        properties.load(rs);
        String value= properties.getProperty("key");
        System.out.println(value);    // 乱码
        String string = new String(value.getBytes("ISO-8859-1"), "GBK");
        System.out.println(string);

//        Properties properties2 = new Properties();
//        properties2.load(streamReader);
//        String value2= properties2.getProperty("key");
//        System.out.println(value2);

    }

    @Test
    public void test2() throws Exception {
        /**
         * 第二种方式:通过Spring的PropertiesLoaderUtils加载
         * 通过这个测试可以明显的看出properties文件的默认的编码集：ISO-8859-1
         * 因为Spring是使用JDK中的class.getResourceAsStream进行properties文件的解析
         * 所以转换为正常显示文本：就是使用ISO-8859-1进行解码，再使用GBK进行编码就可以正常显示
         *
         */
        Properties properties1 = PropertiesLoaderUtils.loadAllProperties("prop/test.properties"); // classpath下的路径不能使用"/"开始，相对路径写法
        String key1 = properties1.getProperty("key");
        System.out.println(key1);   // 乱码

        String s = new String(properties1.getProperty("key").getBytes("ISO-8859-1"), "GBK");
        System.out.println(s);
    }

    @Test
    public void test3() throws Exception {
        /**
         * 第三种：java.util.ResourceBundle
         * 注意： 文件不要带有后缀，也就是test.properties文件只要给出文件名就可以了，即test
         *
         */
        ResourceBundle bundle = ResourceBundle.getBundle("prop/test");
        String s = bundle.getString("key");
        System.out.println(s);  // 乱码

        String s1 = new String(bundle.getString("key").getBytes("ISO-8859-1"), "gbk");
        System.out.println(s1);
    }
}