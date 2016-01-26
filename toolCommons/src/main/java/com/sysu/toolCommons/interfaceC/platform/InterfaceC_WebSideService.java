package com.sysu.toolCommons.interfaceC.platform;

import com.sysu.toolCommons.interfaceC.InterfaceC_Exeception;
import com.sysu.toolCommons.result.ResultInfo;

import java.util.Map;

/**
 * 位于ToolAgents平台
 * Created by adam on 2016/1/13.
 */
public interface InterfaceC_WebSideService {

    /**
     * 根据工作流系统以及对应的标示序列，获得会话令牌
     * 对应着标准接口3的 WMConnect方法
     * @param WFMSName 工作流系统名
     * @param token 分配给工作流系统的唯一标示
     * @return 会话令牌
     */
    public String connect(String WFMSName,String token) throws InterfaceC_Exeception;

    /**
     * 根据工作流系统名以及会话令牌，结束会话
     * 对应着标准接口3的 WMDisconnect方法
     * @param WFMSName 工作流系统名
     * @param handle 系统会话令牌
     * @return 成功与否
     */
    public void disconnect(String WFMSName,String handle) throws InterfaceC_Exeception;

    /**
     * 登录/验证/启动第三方应用
     * @param WFMSName
     * @param handle
     * @param appName
     * @return
     * @throws InterfaceC_Exeception
     */
    boolean invokeApplication(String WFMSName, String handle,
                              String appName, String spaceInstId,String workItemId)
            throws InterfaceC_Exeception;

    /**
     * 判断第三方应用的运行状态
     * @param WFMSName
     * @param handle
     * @param appName
     * @return
     * @throws InterfaceC_Exeception
     */
    int requestAppStatus(String WFMSName, String handle,
                         String appName, String spaceInstId,String workItemId)
            throws InterfaceC_Exeception;

    /**
     * 结束第三方应用的会话
     * @param WFMSName
     * @param handle
     * @param appName
     * @return
     * @throws InterfaceC_Exeception
     */
    boolean terminateApp(String WFMSName, String handle,
                         String appName, String spaceInstId,String workItemId)
            throws InterfaceC_Exeception;
}
