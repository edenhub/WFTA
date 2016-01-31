package com.sysu.toolPlantform.web;

import com.sysu.toolCommons.result.ResultInfo;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adam on 2016/1/31.
 */
public class HttpClient4ResultInfo {

    public ResultInfo requestGet(String url,Map<String,String> params) throws IOException {
        return requestPost(url,params);
    }

    public ResultInfo requestPost(String url,Map<String,String> params) throws IOException {
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
}
