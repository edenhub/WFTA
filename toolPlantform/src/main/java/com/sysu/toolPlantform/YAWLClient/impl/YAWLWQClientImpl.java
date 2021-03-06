package com.sysu.toolPlantform.YAWLClient.impl;

import com.sysu.toolPlantform.YAWLClient.YAWLAdminInfo;
import com.sysu.toolPlantform.YAWLClient.YAWLWQClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.resourcing.rsInterface.WorkQueueGatewayClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by adam on 2015/12/8.
 */

@Component
public class YAWLWQClientImpl implements YAWLWQClient{

//    private WorkQueueGatewayClient wqGatewayClient;

    private String handler;

    private Lock lock;

    @Autowired
    @Qualifier("YAWLAdminInfo")
    private YAWLAdminInfo adminInfo;

    public YAWLAdminInfo getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(YAWLAdminInfo adminInfo) {
        this.adminInfo = adminInfo;
    }

    @PostConstruct
    @Override
    public void init() {
//        wqGatewayClient = new WorkQueueGatewayClient(adminInfo.getWqGatewayUrl());
        lock = new ReentrantLock();
    }

    protected WorkQueueGatewayClient newWorkQueueGatewayClientInstance(){
        WorkQueueGatewayClient wqGatewayClient = new WorkQueueGatewayClient(adminInfo.getWqGatewayUrl());
        return wqGatewayClient;
    }

    @Override
    public void keepSession(WorkQueueGatewayClient wqGatewayClient) throws IOException {
        lock.lock();

        try {
            if (handler == null){
                handler = wqGatewayClient.connect(adminInfo.getUsername(),adminInfo.getPassword());
            }else{
                boolean isConn = Boolean.parseBoolean(wqGatewayClient.checkConnection(handler));
                if (!isConn){
                    handler = wqGatewayClient.connect(adminInfo.getUsername(),adminInfo.getPassword());
                }
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String getWorkItem(String itemId,String handler) throws IOException {
        WorkQueueGatewayClient wqGatewayClient = newWorkQueueGatewayClientInstance();
        String item = wqGatewayClient.getWorkItem(itemId,handler);
        return item;
    }

    @Override
    public String getWorkItemParams(String itemId,String handle) throws IOException{
        WorkQueueGatewayClient wqGatewayClient = newWorkQueueGatewayClientInstance();
        String schema = wqGatewayClient.getWorkItemParameters(itemId,handle);

        return schema;
    }

    @Override
    public String updateWorkItem(String itemId, String handler, String updateStr) throws IOException{
        WorkQueueGatewayClient wqGatewayClient = newWorkQueueGatewayClientInstance();

        String retStr = wqGatewayClient.updateWorkItemData(itemId, updateStr, handler);
        return retStr;
    }

    @Override
    public String completeWorkItem(String pid, String itemId) throws IOException{
        WorkQueueGatewayClient wqGatewayClient = newWorkQueueGatewayClientInstance();
        keepSession(wqGatewayClient);
        String ret = wqGatewayClient.completeItem(pid,itemId,this.handler);

        return ret;
    }

    @Override
    public String updateAndCompleteWorkItem(String itemId, String handle, String updateStr, String pid)
            throws IOException{
        WorkQueueGatewayClient wqGatewayClient = newWorkQueueGatewayClientInstance();
        String updateRet = updateWorkItem(itemId,handle,updateStr);
        if (!wqGatewayClient.successful(updateRet)){
            return updateRet;
        }
        String complateRet = completeWorkItem(pid, itemId);
        if (!wqGatewayClient.successful(complateRet)){
            return complateRet;
        }

        return "<success/>";
    }

    @Override
    public boolean isSuccess(String str){
        WorkQueueGatewayClient wqGatewayClient = newWorkQueueGatewayClientInstance();
        return wqGatewayClient.successful(str);
    }
}
