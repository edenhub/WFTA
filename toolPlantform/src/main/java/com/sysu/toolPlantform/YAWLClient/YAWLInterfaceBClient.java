package com.sysu.toolPlantform.YAWLClient;

import org.yawlfoundation.yawl.engine.YSpecificationID;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by adam on 2015/12/25.
 */
public interface YAWLInterfaceBClient {

    @PostConstruct
    void init();

    void keepSeesion() throws IOException;

    /**
     * 获取editor中给task设置的resouce信息
     * @param specID
     * @param taskId
     * @return
     */
    String getTaskResouceSepces(YSpecificationID specID, String taskId) throws IOException;

    /**
     * 获取editor中给task设置的roles信息
     * @param specId
     * @param taskId
     * @return 存在则返回Json串，否则返回null
     */
    String getTaskAllocateRoles(YSpecificationID specId, String taskId) throws IOException;
}
