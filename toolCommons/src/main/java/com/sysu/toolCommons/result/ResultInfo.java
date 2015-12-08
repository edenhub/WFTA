package com.sysu.toolCommons.result;

/**
 * Created by adam on 2015/11/17.
 */
public class ResultInfo {

    private Object data;

    private boolean success;

    private String msg;

    private Throwable error;

    public static ResultInfo newSuccessResult(Object data){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setSuccess(true);
        resultInfo.setData(data);

        return resultInfo;
    }

    public static ResultInfo newFailResult(String msg,Throwable error){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setSuccess(false);
        resultInfo.setMsg(msg);
        resultInfo.setError(error);

        return resultInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "data=" + data +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", error=" + error +
                '}';
    }
}
