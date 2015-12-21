package com.sysu.toolService.util;

import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolService.util.ResponseHandler.ResultInfoResponseHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.resourcing.rsInterface.ResourceMarshaller;

import java.io.IOException;

/**
 * Created by adam on 2015/12/20.
 */
public class WorkQueueManagerUtil {

    private static SystemInfoProperties sysInfo;

    private static final String WORKITEM_PATH = "workitem";

    private static ResourceMarshaller resourceMarshaller;

    private WorkQueueManagerUtil(){
    }

    static {
        sysInfo = SystemInfoProperties.getInstance();
        resourceMarshaller = new ResourceMarshaller();
    }

    public static String requestWorkItemString(String itemId,String handler) throws IOException {
        String url = sysInfo.getPlantFormWQPath()+WORKITEM_PATH+"?itemId="+itemId+"&handler="+handler;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();

        ResultInfo result = httpClient.execute(httpGet,rh);

        if (result.isSuccess()){

            String itemStr = (String)result.getData();

            return itemStr;
        }else{
            throw new IOException(result.getMsg(),result.getError());
        }
    }

    public static WorkItemRecord requestWorkItemInstance(String itemId,String handler) throws IOException {
        String retStr = requestWorkItemString(itemId,handler);
        WorkItemRecord workItemRecord = resourceMarshaller.unmarshallWorkItemRecord(retStr);

        return workItemRecord;
    }

    private static boolean successful(String result) {
        return (result != null) && (! result.startsWith("<failure>"));
    }
}
