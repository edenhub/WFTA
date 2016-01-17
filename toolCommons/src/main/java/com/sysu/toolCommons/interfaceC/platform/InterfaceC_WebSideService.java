package com.sysu.toolCommons.interfaceC.platform;

import com.sysu.toolCommons.interfaceC.InterfaceC_Exeception;

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
}
