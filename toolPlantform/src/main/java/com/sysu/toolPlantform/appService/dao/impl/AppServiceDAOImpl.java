package com.sysu.toolPlantform.appService.dao.impl;

import com.sysu.toolPlantform.appService.dao.AppServiceDAO;
import com.sysu.toolPlantform.appService.domain.AppServiceInfo;
import com.sysu.toolPlantform.database.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by adam on 2016/4/14.
 */

@Component
public class AppServiceDAOImpl implements AppServiceDAO {

    @Autowired
    private BaseDAO baseDAO;

    public BaseDAO getBaseDAO() {
        return baseDAO;
    }

    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    public SqlMapClientTemplate getSqlMapClientTemplate(){
        return baseDAO.getSqlMapClientTemplate();
    }

    @Override
    public void createAppService(AppServiceInfo serviceInfo) {
        getSqlMapClientTemplate().insert("createAppService",serviceInfo);
    }

    @Override
    public void updateAppServiceByName(String appName,AppServiceInfo appServiceInfo) {
        appServiceInfo.setAppName(appName);
        getSqlMapClientTemplate().update("updateAppServiceByName",appServiceInfo);
    }

    @Override
    public AppServiceInfo selectAppServiceByName(String appName) {
        return (AppServiceInfo) getSqlMapClientTemplate().queryForObject("selectAppServiceByName",appName);
    }

    @Override
    public void deleteAppServiceByName(String appName) {
        getSqlMapClientTemplate().delete("deleteAppServiceByName",appName);
    }

    @Override
    public void reUseAppServiceByName(String appName) {
        getSqlMapClientTemplate().update("reUseAppServiceByName",appName);
    }

    @Override
    public void removeAppServiceByName(String appName) {
        getSqlMapClientTemplate().delete("removeAppServiceByName",appName);
    }
}
