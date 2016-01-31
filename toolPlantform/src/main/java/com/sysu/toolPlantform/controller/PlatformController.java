package com.sysu.toolPlantform.controller;

import com.sysu.toolCommons.interfaceC.platform.InterfaceC_WebSideService;
import com.sysu.toolCommons.util.AjaxExeTemplate;
import com.sysu.toolCommons.web.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 2016/1/14.
 */

@Controller
public class PlatformController {

    @Autowired
    private InterfaceC_WebSideService icWebSideService;

    public InterfaceC_WebSideService getIcWebSideService() {
        return icWebSideService;
    }

    public void setIcWebSideService(InterfaceC_WebSideService icWebSideService) {
        this.icWebSideService = icWebSideService;
    }

    /**
     *  设定yawl的custom form重定向到这个url，然后将appHandle带上，再转到formengine去
     *  达到验证的目的和安全性
     *  but，暂时未连接起来
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/formServiceGateway")
    public String formServiceGateway(HttpServletRequest request,HttpServletResponse response){
        System.out.println("form service gateway");

        //加域可以跨域
        return "redirect:http://www.baidu.com?aa=www";
    }

    @RequestMapping("/connect")
    public void actionConnect(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String WFMSName = params.getStringParam("WFMSName");
        final String token = params.getStringParam("token");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                return icWebSideService.connect(WFMSName,token);
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("connect error",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/disconnect")
    public void actionDisConnect(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String WFMSName = params.getStringParam("WFMSName");
        final String handle = params.getStringParam("handle");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                icWebSideService.disconnect(WFMSName,handle);
                return null;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("disconnect error",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/invoke")
    public void actionInvoke(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String WFMSName = params.getStringParam("WFMSName");
        final String handle = params.getStringParam("handle");
        final String appName = params.getStringParam("appName");
        final String specInstId = params.getStringParam("specInstId");
        final String workItemId = params.getStringParam("workItemId");

//        System.out.println(WFMSName+"\n"+handle+"\n"+appName+"\n"+specInstId+"\n"+workItemId);

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                return icWebSideService.invokeApplication(WFMSName,handle,appName,specInstId,workItemId);
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("actionInvoke error",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/info")
    public void actionRequestAppInfo(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String WFMSName = params.getStringParam("WFMSName");
        final String handle = params.getStringParam("handle");
        final String appName = params.getStringParam("appName");
//        final String specInstId = params.getStringParam("specInstId");
//        final String workItemId = params.getStringParam("workItemId");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                return icWebSideService.requestAppStatus(WFMSName,handle,appName);
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("actionRequestAppInfo error",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/terminate")
    public void actionTerminate(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String WFMSName = params.getStringParam("WFMSName");
        final String handle = params.getStringParam("handle");
        final String appName = params.getStringParam("appName");
        final String specInstId = params.getStringParam("specInstId");
        final String workItemId = params.getStringParam("workItemId");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                return icWebSideService.terminateApp(WFMSName,handle,appName,specInstId,workItemId);
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("actionTerminate error",ex);
            }
        }.exe(request, response);
    }
}
