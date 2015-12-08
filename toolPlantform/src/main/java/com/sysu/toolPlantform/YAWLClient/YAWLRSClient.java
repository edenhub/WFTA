package com.sysu.toolPlantform.YAWLClient;

import java.io.IOException;

/**
 * Created by adam on 2015/12/8.
 */
public interface YAWLRSClient {

    /**
     * 保证会话有效,YAWL的会话是通过handler来标示，handler有生存时间，所以要经常变换
     * @throws IOException
     */
    void keepSession() throws IOException;

    public String getRoles() throws IOException;

    public String getParticipantRoles(String pid) throws IOException;
}
