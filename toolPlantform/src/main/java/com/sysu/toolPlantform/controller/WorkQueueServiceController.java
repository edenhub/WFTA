package com.sysu.toolPlantform.controller;

import com.sysu.toolCommons.util.AjaxExeTemplate;
import com.sysu.toolCommons.util.AssertUtils;
import com.sysu.toolCommons.util.SysLogger;
import com.sysu.toolCommons.web.ParameterUtil;
import com.sysu.toolPlantform.YAWLClient.YAWLWQClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 2015/12/20.
 */

@Controller
public class WorkQueueServiceController implements SysLogger{

    @Autowired
    private YAWLWQClient yawlwqClient;

    public YAWLWQClient getYawlwqClient() {
        return yawlwqClient;
    }

    public void setYawlwqClient(YAWLWQClient yawlwqClient) {
        this.yawlwqClient = yawlwqClient;
    }

    @RequestMapping("/workitem")
    public void getWorkItem(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String itemId = params.getStringParam("itemId");
        final String handler = params.getStringParam("handler");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                if (!AssertUtils.assertNotNullAndBlank(itemId) || !AssertUtils.assertNotNullAndBlank(handler)){
                    throw new Exception("itemId or handler is error");
                }

                String itemStr = yawlwqClient.getWorkItem(itemId,handler);
                return itemStr;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("getWorkitem error ",ex);
            }
        }.exe(request, response);


    }
}