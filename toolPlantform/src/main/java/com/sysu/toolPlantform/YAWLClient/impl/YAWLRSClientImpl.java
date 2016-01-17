package com.sysu.toolPlantform.YAWLClient.impl;

import com.sysu.toolPlantform.YAWLClient.YAWLAdminInfo;
import com.sysu.toolPlantform.YAWLClient.YAWLRSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.yawlfoundation.yawl.resourcing.rsInterface.ResourceGatewayClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by adam on 2015/12/8.
 */
@Component
public class YAWLRSClientImpl implements YAWLRSClient{

//    private ResourceGatewayClient rsGatewayClient;

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

    private Lock lock;

    @PostConstruct
    public void init(){
//        rsGatewayClient = new ResourceGatewayClient(adminInfo.getRsGatewayUrl());
        lock = new ReentrantLock();
    }

    protected ResourceGatewayClient newResourceGatewayClientInstance(){
        ResourceGatewayClient rsGatewayClient = new ResourceGatewayClient(adminInfo.getRsGatewayUrl());
        return rsGatewayClient;
    }

    @Override
    public void keepSession(ResourceGatewayClient rsGatewayClient) throws IOException {
        lock.lock();

        try{
            if (handler == null){
                handler = rsGatewayClient.connect(adminInfo.getUsername(),adminInfo.getPassword());
            }else{
                boolean isConn = Boolean.parseBoolean(rsGatewayClient.checkConnection(handler));
                if (!isConn){
                    handler = rsGatewayClient.connect(adminInfo.getUsername(),adminInfo.getPassword());
                }
            }
        }finally {
            lock.unlock();
        }

    }

    @Override
    public String getRoles() throws IOException {
        ResourceGatewayClient rsGatewayClient = newResourceGatewayClientInstance();
        keepSession(rsGatewayClient);
        String ret = rsGatewayClient.getRoles(handler);
        return ret;
    }

    @Override
    public String getParticipantRoles(String pid) throws IOException {
        ResourceGatewayClient rsGatewayClient = newResourceGatewayClientInstance();
        keepSession(rsGatewayClient);
        String ret = rsGatewayClient.getParticipantRoles(pid,handler);
        return ret;
    }

    @Override
    public String getParticipantInfo(String pid) throws IOException{
        ResourceGatewayClient rsGatewayClient = newResourceGatewayClientInstance();
        keepSession(rsGatewayClient);
        String pInfo = rsGatewayClient.getParticipant(pid,handler);
        return pInfo;
    }
}
