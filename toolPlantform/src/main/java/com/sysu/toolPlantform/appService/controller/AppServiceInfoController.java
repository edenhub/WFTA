package com.sysu.toolPlantform.appService.controller;

import com.sysu.toolCommons.util.AjaxExeTemplate;
import com.sysu.toolCommons.web.ParameterUtil;
import com.sysu.toolPlantform.appService.action.AppServiceAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 2016/4/14.
 */

@Controller
public class AppServiceInfoController {

    @Autowired
    private AppServiceAction appServiceAction;

    public AppServiceAction getAppServiceAction() {
        return appServiceAction;
    }

    public void setAppServiceAction(AppServiceAction appServiceAction) {
        this.appServiceAction = appServiceAction;
    }

    @RequestMapping("/app/register")
    public void register(HttpServletRequest request,HttpServletResponse response){

    }

    @RequestMapping("/app/online")
    public void online(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String appName = params.getStringParam("appName");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                boolean ret = appServiceAction.onlineAppServiceByAppName(appName);
                return ret;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("online error",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/app/offline")
    public void offline(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String appName = params.getStringParam("appName");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                appServiceAction.offlineAppServiceByAppName(appName);
                return null;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("offline error",ex);
            }
        }.exe(request, response);
    }
}
