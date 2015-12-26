package com.sysu.toolPlantform.YAWLClient.impl;

import com.sysu.toolPlantform.YAWLClient.YAWLAdminInfo;
import com.sysu.toolPlantform.YAWLClient.YAWLInterfaceBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.interfaceB.InterfaceB_EnvironmentBasedClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by adam on 2015/12/25.
 */
@Component
public class YAWLInterfaceBClientImpl implements YAWLInterfaceBClient {

    private InterfaceB_EnvironmentBasedClient ibClient;

    public InterfaceB_EnvironmentBasedClient getIbClient() {
        return ibClient;
    }

    @Autowired
    private YAWLAdminInfo yawlAdminInfo;

    private Lock lock;

    private String handler;

    public YAWLAdminInfo getYawlAdminInfo() {
        return yawlAdminInfo;
    }

    public void setYawlAdminInfo(YAWLAdminInfo yawlAdminInfo) {
        this.yawlAdminInfo = yawlAdminInfo;
    }

    @Override
    @PostConstruct
    public void init(){
        ibClient = new InterfaceB_EnvironmentBasedClient(yawlAdminInfo.getInterfaceBUrl());
        lock = new ReentrantLock();
    }

    @Override
    public void keepSeesion() throws IOException{
        lock.lock();

        try{
            if (handler == null){
                handler = ibClient.connect(yawlAdminInfo.getUsername(),yawlAdminInfo.getPassword());
            }else{
                boolean isConn = Boolean.parseBoolean(ibClient.checkConnection(handler));
                if (!isConn){
                    handler = ibClient.connect(yawlAdminInfo.getUsername(),yawlAdminInfo.getPassword());
                }
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String getTaskResouceSepces(YSpecificationID specID, String taskId) throws IOException {
        keepSeesion();
        String rsStr = ibClient.getResourcingSpecs(specID,taskId,handler);
        return rsStr;
    }

    @Override
    public String getTaskAllocateRoles(YSpecificationID specId,String taskId) throws IOException {
        String rsStr = getTaskResouceSepces(specId,taskId);
        return rsStr;
    }
}
