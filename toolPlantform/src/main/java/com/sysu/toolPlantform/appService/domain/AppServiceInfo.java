package com.sysu.toolPlantform.appService.domain;

/**
 * Created by adam on 2016/4/14.
 */
public class AppServiceInfo {

    private String appName;

    private String serviceUrl;

    private String appToken;

    private boolean state = true;

    private boolean useable = true;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isUseable() {
        return useable;
    }

    public void setUseable(boolean useable) {
        this.useable = useable;
    }

    @Override
    public String toString() {
        return "AppServiceInfo{" +
                "appName='" + appName + '\'' +
                ", serviceUrl='" + serviceUrl + '\'' +
                ", appToken='" + appToken + '\'' +
                ", state=" + state +
                ", useable=" + useable +
                '}';
    }
}
