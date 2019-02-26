package org.smart4j.framework.bean;

import java.io.InputStream;

/**
 * 封装上传文件参数
 * Created by DINGJUN on 2018/5/14.
 */
public class FileParam {

    private String fieldName;           //表示文件表单的字段名
    private String fileName;            //表示上传文件的文件名
    private long fileSize;              //表示上传文件的文件大小
    private String contentType;         //表示上传文件的Content-Type，可以判断文件类型
    private InputStream inputStream;    //表示上传文件的字节流输入

    public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inputStream) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
