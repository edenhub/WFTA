package com.sysu.toolPlantform.YAWLClient.impl;

import com.sysu.toolPlantform.YAWLClient.YAWLAdminInfo;
import com.sysu.toolPlantform.YAWLClient.YAWLRSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.yawlfoundation.yawl.resourcing.rsInterface.ResourceGatewayClient;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by adam on 2015/12/8.
 */
@Component
public class YAWLRSClientImpl implements YAWLRSClient{

    private ResourceGatewayClient rsGatewayClient;

    public ResourceGatewayClient getRsGatewayClient() {
        return rsGatewayClient;
    }

    @Autowired
    @Qualifier("YAWLAdminInfo")
    private YAWLAdminInfo adminInfo;

    public YAWLAdminInfo getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(YAWLAdminInfo adminInfo) {
        this.adminInfo = adminInfo;
    }

    private String handler;

    @PostConstruct
    public void init(){
        rsGatewayClient = new ResourceGatewayClient(adminInfo.getRsGatewayUrl());
    }

    @Override
    public void keepSession() throws IOException {
        if (handler == null){
            handler = rsGatewayClient.connect(adminInfo.getUsername(),adminInfo.getPassword());
        }else{
            boolean isConn = Boolean.parseBoolean(rsGatewayClient.checkConnection(handler));
            if (!isConn){
                handler = rsGatewayClient.connect(adminInfo.getUsername(),adminInfo.getPassword());
            }
        }
    }

    @Override
    public String getRoles() throws IOException {
        keepSession();
        String ret = rsGatewayClient.getRoles(handler);
        return ret;
    }

    @Override
    public String getParticipantRoles(String pid) throws IOException {
        keepSession();
        String ret = rsGatewayClient.getParticipantRoles(pid,handler);
        return ret;
    }
}
