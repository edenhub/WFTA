package com.sysu.toolCommons.interfaceC.tool;

import com.sysu.toolCommons.interfaceC.app.AppInfo;

import javax.jws.WebService;

/**
 * Created by adam on 2016/4/14.
 */
@WebService
public interface InterfaceC_ToolSideService {

//    public String testAction(String name);

    public String invokeApplication(String WFMSName, String handle, String spaceInstId,String workItemId)
            throws Exception;

    public boolean terminateApp(String WFMSName, String handle, String spaceInstId,String workItemId)
            throws Exception;

    public String requestAppInfo(String WFMSName, String handle, String appName);
}
