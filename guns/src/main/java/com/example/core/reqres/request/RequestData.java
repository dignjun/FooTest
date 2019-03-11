package com.example.core.reqres.request;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求数据封装
 */
@Data
public class RequestData implements Serializable {
    private JSONObject data;
    private String ip;
    private String url;
}


























