package com.sysu.toolCommons.interfaceC.platform;


/**
 *工作流系统中使用
 * Created by adam on 2016/1/13.
 */
public interface InterfaceC_ClientSideVisitor {

    /**
     * 根据工作流系统以及对应的标示序列，获得会话令牌
     * 对应着标准接口3的 WMConnect方法
     * @param WFMSName 工作流系统名
     * @param token 分配给工作流系统的唯一标示
     * @return 会话令牌
     */
    public String connect(String WFMSName,String token) throws Exception;

    /**
     * 根据工作流系统名以及会话令牌，结束会话
     * 对应着标准接口3的 WMDisconnect方法
     * @param WFMSName 工作流系统名
     * @param handle 系统会话令牌
     * @return 成功与否
     */
    public void disconnect(String WFMSName,String handle) throws Exception;

    /**
     * 为task的执行建立会话,获得第三方工具的会话令牌
     * @param WFMSName
     * @param handle
     * @param appName
     * @param specInstId
     * @param workItemId
     * @return
     * @throws Exception
     */
    String invokeApp(String WFMSName, String handle,
                      String appName, String specInstId, String workItemId) throws Exception;

    /**
     * 判断第三方应用的状态
     * @param WFMSName
     * @param handle
     * @param appName
     * @param specInstId
     * @param workItemId
     * @return
     * @throws Exception
     */
    String requestAppInfo(String WFMSName, String handle,
                           String appName) throws Exception;

    /**
     * 结束task的该次会话
     * @param WFMSName
     * @param handle
     * @param appName
     * @param specInstId
     * @param workItemId
     * @return
     * @throws Exception
     */
    Boolean terminateApp(String WFMSName, String handle,
                         String appName, String specInstId, String workItemId) throws Exception;
}
