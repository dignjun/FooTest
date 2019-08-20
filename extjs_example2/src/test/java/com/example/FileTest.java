package com.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作测试
 */
public class FileTest {
    /**
     * 1：作用：将一个文件的内容（文件夹和文件）复制到另一个文件夹
     * 2：如果指定filter，则对需要过滤的文件或者文件夹进行过滤，若filter为null，则全部进行复制
     *  2.1：文件过滤器的使用：一个含有文件的多级文件夹文件目录，如果指定的filter在前面的文件夹没有匹配到，则不会进行复制
     *  此时即使子文件夹或者文件夹中的文件也都不会进行复制，也就是说这个匹配是从外往里逐层复制。
     *
     */
    @Test
    public void test1() {
        File sd = new File("D:\\test");
        File dd = new File("D:\\test2");

        // 文件名过滤器，不起作用-原因为2.1说明
//        ArrayList<String> ss = new ArrayList<>();
//        ss.add("12test.txt");
//        NameFileFilter nff = new NameFileFilter(ss);
        // 正则表达式过滤器，不起作用-原因为2.1说明
//        RegexFileFilter rff = new RegexFileFilter("13test.txt");
        // 文件名前缀过滤器，不起作用，将过滤的名称修改为最外层的文件夹名称，有文件夹没有文件，将文件夹中的文件修改为过滤的名称，文件夹和文件复制成功。
        PrefixFileFilter pff = new PrefixFileFilter("2test");
        try {
            FileUtils.copyDirectory(sd, dd, pff);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
