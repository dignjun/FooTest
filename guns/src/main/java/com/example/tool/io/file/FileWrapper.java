package com.example.tool.io.file;

import com.example.tool.io.FileUtil;
import com.example.tool.util.CharsetUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 文件包装器，扩展文件对象
 * Created by DINGJUN on 2019.03.11.
 */
public class FileWrapper {
    protected File file;
    protected Charset charset;

    /** 默认编码：UTF-8 */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    // ------------------------------------------------------- Constructor start
    /**
     * 构造
     * @param file 文件
     * @param charset 编码，使用 {@link CharsetUtil}
     */
    public FileWrapper(File file, Charset charset) {
        this.file = file;
        this.charset = charset;
    }
    // ------------------------------------------------------- Constructor end

    // ------------------------------------------------------- Setters and Getters start start
    /**
     * 获得文件
     * @return 文件
     */
    public File getFile() {
        return file;
    }

    /**
     * 设置文件
     * @param file 文件
     * @return 自身
     */
    public FileWrapper setFile(File file) {
        this.file = file;
        return this;
    }

    /**
     * 获得字符集编码
     * @return 编码
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * 设置字符集编码
     * @param charset 编码
     * @return 自身
     */
    public FileWrapper setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }
    // ------------------------------------------------------- Setters and Getters start end

    /**
     * 可读的文件大小
     * @return 大小
     */
    public String readableFileSize() {
        return FileUtil.readableFileSize(file.length());
    }

}
