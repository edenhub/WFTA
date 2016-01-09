package com.sysu.toolPlantform.controller;

import com.sysu.toolCommons.util.AjaxExeTemplate;
import com.sysu.toolCommons.util.AssertUtils;
import com.sysu.toolCommons.util.SysLogger;
import com.sysu.toolCommons.web.ParameterUtil;
import com.sysu.toolPlantform.YAWLClient.YAWLWQClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

//        System.out.println(itemId);
//        System.out.println(handler);

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

    @RequestMapping("/workItemParameter")
    public void getWorkItemDataSchema(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String itemId = params.getStringParam("itemId");
        final String handler = params.getStringParam("handle");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                if (!AssertUtils.assertNotNullAndBlank(itemId) || !AssertUtils.assertNotNullAndBlank(handler)){
                    throw new Exception("itemId or handler is error");
                }

                String itemStr = yawlwqClient.getWorkItemParams(itemId,handler);
                return itemStr;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("getWorkitemDataSchema error ",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/updateWorkItem")
    public void updateWorkItem(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String itemId = params.getStringParam("itemId");
        final String handle = params.getStringParam("handle");
        final String updateStr = params.getStringParam("updateStr");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                if (!AssertUtils.assertNotNullAndBlank(itemId) || !AssertUtils.assertNotNullAndBlank(handle)) {
                    throw new Exception("itemId or handler is error");
                }

                String ret = yawlwqClient.updateWorkItem(itemId,handle,updateStr);
                return ret;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("update workitem error ",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/completeWorkItem")
    public void completeWorkItem(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String pid = params.getStringParam("pid");
        final String itemId = params.getStringParam("itemId");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                if (!AssertUtils.assertNotNullAndBlank(itemId) ||
                        !AssertUtils.assertNotNullAndBlank(pid)) {
                    throw new Exception("itemId or handler is error");
                }

                String ret = yawlwqClient.completeWorkItem(pid,itemId);
                return ret;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("complate workitem error",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/updateAndCompleteWI")
    public void updateAndCompleteWorkItem(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil params = new ParameterUtil(request);
        final String pid = params.getStringParam("pid");
        final String itemId = params.getStringParam("itemId");
        final String handle = params.getStringParam("handle");
        final String updateStr = params.getStringParam("updateStr");

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                if (!AssertUtils.assertNotNullAndBlank(itemId) ||
                        !AssertUtils.assertNotNullAndBlank(handle) ||
                        !AssertUtils.assertNotNullAndBlank(pid) ||
                        !AssertUtils.assertNotNullAndBlank(updateStr)) {
                    throw new Exception("itemId or handler is error");
                }

                String ret = yawlwqClient.updateAndCompleteWorkItem(itemId,handle,updateStr,pid);
                if (yawlwqClient.isSuccess(ret))
                    return ret;
                else{
                    throw new Exception("update and complete workItem error : "+ret);
                }
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("update and complete workItem error",ex);
            }
        }.exe(request, response);
    }
}
