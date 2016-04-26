package com.sysu.toolPlantform.appService.action.impl;

import com.sysu.toolPlantform.appService.action.AppServiceAction;
import com.sysu.toolPlantform.appService.domain.AppServiceInfo;

/**
 * Created by adam on 2016/4/22.
 */
public class AppServiceActionImpl4SvcMngCtal implements AppServiceAction {
    @Override
    public void newAppService(AppServiceInfo appServiceInfo) {

    }

    @Override
    public void modifyAppServiceByAppName(String appName, AppServiceInfo appServiceInfo) {

    }

    @Override
    public boolean onlineAppServiceByAppName(String appName) {
        return false;
    }

    @Override
    public void offlineAppServiceByAppName(String appName) {

    }

    @Override
    public void removeAppServiceByAppName(String appName) {

    }

    @Override
    public AppServiceInfo queryAppServiceByName(String appName) {
        return null;
    }
}
