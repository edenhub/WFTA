package com.sysu.toolService.util;

import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolService.util.ResponseHandler.ResultInfoResponseHandler;
import com.sysu.toolService.util.WorkItemParamParser.WorkItemParamParser;
import com.sysu.toolService.util.WorkItemParamParser.WorkItemParams;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.DocumentException;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.resourcing.rsInterface.ResourceMarshaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adam on 2015/12/20.
 */
public class WorkQueueManagerUtil {

    private static SystemInfoProperties sysInfo;

    private static final String WORKITEM_PATH = "workitem";

    private static final String UPDATE_WORKITEM_PATH = "updateWorkItem";

    private static final String WORKITEM_PARAM_PATH = "workItemParameter";

    private static final String COMPLETE_WORKITEM_PATH = "completeWorkItem";

    private static final String UPDATE_AND_COMPLETE_PATH = "updateAndCompleteWI";

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

    public static ResultInfo requestWorkItemParams(String itemId,String handle) throws IOException, DocumentException {
        String url = sysInfo.getPlantFormWQPath()+WORKITEM_PARAM_PATH;
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
        requestParams.add(new BasicNameValuePair("itemId",itemId));
        requestParams.add(new BasicNameValuePair("handle",handle));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(requestParams,"UTF-8");
        httpPost.setEntity(formEntity);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();
        ResultInfo result = httpClients.execute(httpPost,rh);

        if (result.getError() == null){
            String paramStr = (String) result.getData();
            WorkItemParams workItemParams = WorkItemParamParser.getInstance()
                    .parse(paramStr, WorkItemParamParser.OrderType.Ordering);
            result.setData(workItemParams);
        }

        return result;
    }

    public static ResultInfo updateWorkItemData(String itemId,String handle,String updateStr) throws IOException{
        String url = sysInfo.getPlantFormWQPath()+UPDATE_WORKITEM_PATH;
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
        requestParams.add(new BasicNameValuePair("itemId",itemId));
        requestParams.add(new BasicNameValuePair("handle",handle));
        requestParams.add(new BasicNameValuePair("updateStr",updateStr));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(requestParams,"UTF-8");
        httpPost.setEntity(formEntity);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();
        ResultInfo result = httpClients.execute(httpPost,rh);

        return result;
    }

    public static ResultInfo completeWorkItemData(String itemId,String handle,String pid) throws IOException{
        String url = sysInfo.getPlantFormWQPath()+COMPLETE_WORKITEM_PATH;
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
        requestParams.add(new BasicNameValuePair("itemId",itemId));
        requestParams.add(new BasicNameValuePair("pid",pid));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(requestParams,"UTF-8");
        httpPost.setEntity(formEntity);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();
        ResultInfo result = httpClients.execute(httpPost,rh);

        return result;
    }

    public static ResultInfo updateAndCompleteWorkItemData(String itemId,String handle,String pid,String updateStr) throws IOException{
        String url = sysInfo.getPlantFormWQPath()+UPDATE_AND_COMPLETE_PATH;
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
        requestParams.add(new BasicNameValuePair("itemId",itemId));
        requestParams.add(new BasicNameValuePair("handle",handle));
        requestParams.add(new BasicNameValuePair("pid",pid));
        requestParams.add(new BasicNameValuePair("updateStr",updateStr));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(requestParams,"UTF-8");
        httpPost.setEntity(formEntity);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();
        ResultInfo result = httpClients.execute(httpPost,rh);

        return result;
    }

    private static boolean successful(String result) {
        return (result != null) && (! result.startsWith("<failure>"));
    }
}
