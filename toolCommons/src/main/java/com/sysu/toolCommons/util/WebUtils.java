package com.sysu.toolCommons.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by adam on 2015/11/17.
 */
public class WebUtils implements SysLogger{

    private WebUtils(){}

    public static void writeJSON(Object obj,HttpServletResponse response){
        response.setContentType("application/json;charset=UTF-8");
        String jsonStr = JSON.toJSONString(obj);
        try {
            response.getWriter().write(jsonStr);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
