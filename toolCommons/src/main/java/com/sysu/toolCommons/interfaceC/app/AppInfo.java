package com.sysu.toolCommons.interfaceC.app;

import com.sysu.toolCommons.result.ResultInfo;

/**
 * Created by adam on 2016/4/14.
 */
public class AppInfo extends ResultInfo {

    private int errorCode;

    private String appName;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
