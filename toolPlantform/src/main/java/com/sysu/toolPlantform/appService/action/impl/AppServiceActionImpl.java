package com.sysu.toolPlantform.appService.action.impl;

import com.sysu.toolPlantform.appService.action.AppServiceAction;
import com.sysu.toolPlantform.appService.dao.AppServiceDAO;
import com.sysu.toolPlantform.appService.domain.AppServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by adam on 2016/4/14.
 */
@Component
public class AppServiceActionImpl implements AppServiceAction {

    @Autowired
    private AppServiceDAO appServiceDAO;

    @Override
    public void newAppService(AppServiceInfo appServiceInfo) {
        appServiceDAO.createAppService(appServiceInfo);
    }

    @Override
    public void modifyAppServiceByAppName(String appName, AppServiceInfo appServiceInfo) {
        appServiceDAO.updateAppServiceByName(appName,appServiceInfo);
    }

    @Override
    public boolean onlineAppServiceByAppName(String appName) {
        AppServiceInfo appServiceInfo = queryAppServiceByName(appName);
        if (appServiceInfo == null) return false;
        if(!appServiceInfo.isUseable())
            appServiceDAO.reUseAppServiceByName(appName);
        return true;
    }

    @Override
    public void offlineAppServiceByAppName(String appName) {
        appServiceDAO.deleteAppServiceByName(appName);
    }

    @Override
    public void removeAppServiceByAppName(String appName){
        appServiceDAO.removeAppServiceByName(appName);
    }

    @Override
    public AppServiceInfo queryAppServiceByName(String appName) {
        return appServiceDAO.selectAppServiceByName(appName);
    }
}
