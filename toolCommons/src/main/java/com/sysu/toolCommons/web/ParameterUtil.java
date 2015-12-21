package com.sysu.toolCommons.web;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 2015/11/7.
 */
public class ParameterUtil {

    private HttpServletRequest request;

    public ParameterUtil(HttpServletRequest request){
        this.request = request;
    }

    public String getStringParam(String name){
        return request.getParameter(name);
    }

    public List<String> getStringParmList(String name){
        String[] vals = request.getParameterValues(name);
        List<String> rets = new ArrayList<String>(vals.length);
        for (String s : vals) rets.add(s);

        return rets;
    }

    public int getIntParam(String name){
        String val = request.getParameter(name);
        if (isEmptyAndBlank(val)) return 0;
        else
            return Integer.parseInt(val);
    }

    public List<Integer> getIntParamList(String name){
        String[] vals = request.getParameterValues(name);
        List<Integer> rets = new ArrayList<Integer>(vals.length);
        for (String s : vals){
            if (isEmptyAndBlank(s)) rets.add(0);
            else rets.add(Integer.parseInt(s));
        }

        return rets;
    }

    public float getFloatParam(String name){
        String val = request.getParameter(name);
        if (isEmptyAndBlank(val)) return 0F;
        return Float.parseFloat(val);
    }

    public List<Float> getFloatParamList(String name){
        String[] vals = request.getParameterValues(name);
        List<Float> rets = new ArrayList<Float>(vals.length);
        for (String s : vals){
            if (isEmptyAndBlank(s)) rets.add(0F);
            else rets.add(Float.parseFloat(s));
        }

        return rets;
    }

    public long getLongParam(String name){
        String val = request.getParameter(name);
        if (isEmptyAndBlank(val)) return 0L;
        return Long.parseLong(val);
    }

    public List<Long> getLongParamList(String name){
        String[] vals = request.getParameterValues(name);
        List<Long> rets = new ArrayList<Long>(vals.length);
        for (String s : vals){
            if (isEmptyAndBlank(s)) rets.add(0L);
            else rets.add(Long.parseLong(s));
        }

        return rets;
    }

    public double getDoubleParam(String name){
        String val = request.getParameter(name);
        if (isEmptyAndBlank(val)) return 0D;
        return Double.parseDouble(val);
    }

    public List<Double> getDoubleParmList(String name){
        String[] vals = request.getParameterValues(name);
        List<Double> rets = new ArrayList<Double>(vals.length);

        for (String s : vals){
            if (isEmptyAndBlank(s)) rets.add(0D);
            else rets.add(Double.parseDouble(s));
        }

        return rets;
    }

    private boolean isEmptyAndBlank(String val){
        return StringUtils.isEmpty(val) && StringUtils.isBlank(val);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
