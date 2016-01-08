package com.sysu.toolPlantform.YAWLClient;

import org.yawlfoundation.yawl.engine.YSpecificationID;

import java.io.IOException;

/**
 * Created by adam on 2015/12/8.
 */
public interface YAWLWQClient {

    /**
     * 初始化
     */
    public void init();

    /**
     * 保持会话有效
     * @throws IOException
     */
    public void keepSession() throws IOException;

    /**
     * 获得task的xml描述
     * @param itemId
     * @param handler
     * @return
     * @throws IOException
     */
    public String getWorkItem(String itemId,String handler) throws IOException;

    String updateWorkItem(String itemId, String handler, String updateStr) throws IOException;

    String completeWorkItem(String pid, String itemId, String handle) throws IOException;

    String updateAndCompleteWorkItem(String itemId, String handle, String updateStr, String pid) throws IOException;
}
