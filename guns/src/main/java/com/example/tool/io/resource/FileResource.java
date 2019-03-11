package com.example.tool.io.resource;

import com.example.tool.io.FileUtil;
import com.example.tool.util.StrUtil;

import java.io.File;

/**
 * 文件资源访问对象
 * Created by DINGJUN on 2019.03.11.
 */
public class FileResource extends UrlResource {
    // ----------------------------------------------------------------------- Constructor start
    /**
     * 构造
     *
     * @param file 文件
     */
    public FileResource(File file) {
        this(file, file.getName());
    }

    /**
     * 构造
     *
     * @param file 文件
     * @param fileName 文件名，如果为null获取文件本身的文件名
     */
    public FileResource(File file, String fileName) {
        super(URLUtil.getURL(file), StrUtil.isBlank(fileName) ? file.getName() : fileName);
    }

    /**
     * 构造
     *
     * @param path 文件绝对路径或相对ClassPath路径，但是这个路径不能指向一个jar包中的文件
     */
    public FileResource(String path) {
        this(FileUtil.file(path));
    }
    // ----------------------------------------------------------------------- Constructor end

}
