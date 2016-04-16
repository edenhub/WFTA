package com.sysu.toolPlantform.appService.dao;

import com.sysu.toolPlantform.appService.domain.AppServiceInfo;

/**
 * Created by adam on 2016/4/14.
 */
public interface AppServiceDAO {

    public void createAppService(AppServiceInfo serviceInfo);

    public void updateAppServiceByName(String appName,AppServiceInfo appServiceInfo);

    public AppServiceInfo selectAppServiceByName(String appName);

    public void deleteAppServiceByName(String appName);

    public void reUseAppServiceByName(String appName);

    public void removeAppServiceByName(String appName);
}
