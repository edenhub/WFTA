package com.sysu.toolPlantform.appService.action;

import com.sysu.toolPlantform.appService.domain.AppServiceInfo;

/**
 * Created by adam on 2016/4/14.
 */
public interface AppServiceAction {

    public void newAppService(AppServiceInfo appServiceInfo);

    public void modifyAppServiceByAppName(String appName,AppServiceInfo appServiceInfo);

    public boolean onlineAppServiceByAppName(String appName);

    public void offlineAppServiceByAppName(String appName);

    void removeAppServiceByAppName(String appName);

    public AppServiceInfo queryAppServiceByName(String appName);

}
