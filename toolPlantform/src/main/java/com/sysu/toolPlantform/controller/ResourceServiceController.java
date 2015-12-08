package com.sysu.toolPlantform.controller;

import com.sysu.toolCommons.util.AjaxExeTemplate;
import com.sysu.toolCommons.util.AssertUtils;
import com.sysu.toolCommons.util.SysLogger;
import com.sysu.toolPlantform.YAWLClient.YAWLRSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 2015/12/8.
 */

@Controller
public class ResourceServiceController implements SysLogger{

    @Autowired
    private YAWLRSClient yawlrsClient;

    public YAWLRSClient getYawlrsClient() {
        return yawlrsClient;
    }

    public void setYawlrsClient(YAWLRSClient yawlrsClient) {
        this.yawlrsClient = yawlrsClient;
    }

    @RequestMapping(value = "/roles")
    public void getRoles(HttpServletRequest request,HttpServletResponse response){
        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                String rsStr = yawlrsClient.getRoles();
                System.out.println(rsStr+"===");
                return rsStr;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error(ResourceServiceController.class+" getRoles失败",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping(value = "/participantRoles/{pid}")
    public void getParticipantRoles(HttpServletRequest request,HttpServletResponse response,
                            @PathVariable final String pid){
        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                if (!AssertUtils.assertNotNullAndBlank(pid)){
                    throw new Exception("pid is error");
                }

                String rsStr = yawlrsClient.getParticipantRoles(pid);
                return rsStr;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error(ResourceServiceController.class+" getParticipantRoles失败",ex);
            }
        }.exe(request, response);
    }
}
