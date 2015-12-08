package com.sysu.toolCommons.util;


import com.sysu.toolCommons.result.ResultInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 2015/11/17.
 */
public abstract class AjaxExeTemplate implements SysLogger{
    private ResultInfo resultInfo = new ResultInfo();

    public void exe(HttpServletRequest request,HttpServletResponse response){
        try {
            if (logger.isInfoEnabled())
                logger.info("do try struct");
            resultInfo.setSuccess(true);
            resultInfo.setMsg("success");
            Object data = doExe(request,response);
            resultInfo.setData(data);
            WebUtils.writeJSON(resultInfo,response);
        }catch (Exception ex){
            if (logger.isInfoEnabled())
                logger.info("do exception struct");
            resultInfo.setError(ex);
            resultInfo.setSuccess(false);
            resultInfo.setData(null);
            holdException(request, response, ex);
            resultInfo.setMsg(ex.getMessage());
            WebUtils.writeJSON(resultInfo,response);
        }
    }

    public void setSuccess(boolean success){
        resultInfo.setSuccess(success);
    }

    public boolean isSuccess(boolean isSuccess){
        return resultInfo.isSuccess();
    }

    public void setData(Object data){
        resultInfo.setData(data);
    }

    public Object getData(){
        return resultInfo.getData();
    }

    public void setMsg(String msg){
        resultInfo.setMsg(msg);
    }

    public String getMsg(){
        return resultInfo.getMsg();
    }

    public Throwable getError(){
        return resultInfo.getError();
    }

    public void setError(Throwable throwable){
        resultInfo.setError(throwable);
    }

    public abstract Object doExe(HttpServletRequest request,HttpServletResponse response) throws Exception;

    public abstract void holdException(HttpServletRequest request,HttpServletResponse respose,Exception ex);
}
