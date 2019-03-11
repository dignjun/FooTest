package com.example.tool.io.resource;

import com.example.tool.io.FileUtil;

import java.io.File;

/**
 * Web root资源访问对象
 *
 * Created by DINGJUN on 2019.03.11.
 */
public class WebAppResource extends FileResource {
    /**
     * 构造
     *
     * @param path 相对于Web root的路径
     */
    public WebAppResource(String path) {
        super(new File(FileUtil.getWebRoot(), path));
    }

}
