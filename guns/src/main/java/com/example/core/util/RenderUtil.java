package com.example.core.util;

import com.alibaba.fastjson.JSON;
import com.example.model.exception.ServiceException;
import com.example.model.exception.enums.CoreExceptionEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 渲染工具类
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public class RenderUtil {
    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.WRITE_ERROR);
        }
    }
}
