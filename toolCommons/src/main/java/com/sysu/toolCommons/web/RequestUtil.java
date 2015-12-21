package com.sysu.toolCommons.web;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by adam on 2015/11/7.
 */
public class RequestUtil {

    private HttpServletRequest request;

    public RequestUtil(HttpServletRequest request){
        this.request = request;
    }

    public RequestUtil setAttribute(String name,Object value){
        request.setAttribute(name,value);

        return this;
    }

    public Object getAttribute(String name){
        return request.getAttribute(name);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
