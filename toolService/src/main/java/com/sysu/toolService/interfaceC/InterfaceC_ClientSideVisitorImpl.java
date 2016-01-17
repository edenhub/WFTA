package com.sysu.toolService.interfaceC;

import com.sysu.toolCommons.interfaceC.platform.InterfaceC_ClientSideVisitor;
import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolService.util.ResponseHandler.ResultInfoResponseHandler;
import com.sysu.toolService.util.SystemInfoProperties;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adam on 2016/1/14.
 */
public class InterfaceC_ClientSideVisitorImpl implements InterfaceC_ClientSideVisitor {

    private SystemInfoProperties infos = SystemInfoProperties.getInstance();

    private final String ROOT_URL = infos.getPlatformInterfaceCURL();

    private final String CONNECT_PATH = "connect";

    private final String DISCONNECT_PATH = "disconnect";

    protected ResultInfo requestGet(String url,Map<String,String> params) throws IOException {
        return requestPost(url,params);
    }

    protected ResultInfo requestPost(String url,Map<String,String> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
        if (params != null && params.size() != 0){
            for (Map.Entry<String,String> e : params.entrySet()){
                requestParams.add(new BasicNameValuePair(e.getKey(),e.getValue()));
            }
        }
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(requestParams, "UTF-8");
        httpPost.setEntity(formEntity);
        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();
        ResultInfo result = httpClient.execute(httpPost, rh);

        return result;
    }

    @Override
    public String connect(String WFMSName, String token) throws Exception {
        String url = ROOT_URL+CONNECT_PATH;
        Map<String,String> params = new HashMap<String, String>();
        params.put("WFMSName",WFMSName);
        params.put("token",token);

        ResultInfo ri =  requestPost(url,params);
        if (ri.getError() == null){
            return (String) ri.getData();
        }else{
            throw new Exception(ri.getError());
        }
    }

    @Override
    public void disconnect(String WFMSName, String handle) throws Exception {
        String url = ROOT_URL+DISCONNECT_PATH;
        Map<String,String> params = new HashMap<String, String>();
        params.put("WFMSName",WFMSName);
        params.put("handle",handle);

        ResultInfo ri =  requestPost(url,params);
        if (ri.getData() != null){
            throw new Exception(ri.getError());
        }
    }
}
