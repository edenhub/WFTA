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

    @RequestMapping("connect")
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

    @RequestMapping("disconnect")
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
}
