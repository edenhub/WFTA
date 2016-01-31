package com.sysu.toolService.interfaceC;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sysu.toolCommons.interfaceC.platform.InterfaceC_ClientSideVisitor;
import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolService.util.SystemInfoProperties;
import org.yawlfoundation.yawl.engine.interfce.Interface_Client;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adam on 2016/1/24.
 */
public class YInterfaceC_ClientSideVisitorImpl extends Interface_Client implements InterfaceC_ClientSideVisitor {

    private SystemInfoProperties infos = SystemInfoProperties.getInstance();

    private final String ROOT_URL = infos.getPlatformInterfaceCURL();

    private final String CONNECT_PATH = "connect";

    private final String DISCONNECT_PATH = "disconnect";

    private final String INVOKE_PATH = "invoke";

    private final String INFO_PATH = "info";

    private final String TERMINATE_PATH = "terminate";

    private Gson gson = new GsonBuilder().create();

    protected ResultInfo parse2ResultInfo(String jsonStr){
        return gson.fromJson(jsonStr,ResultInfo.class);
    }

    @Override
    public String connect(String WFMSName, String token) throws Exception {
        String url = ROOT_URL+CONNECT_PATH;
        Map<String,String> params = new HashMap<String, String>();
        params.put("WFMSName",WFMSName);
        params.put("token",token);

        String jsonStr = executePost(url,params);
        ResultInfo ri = parse2ResultInfo(jsonStr);
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

        String jsonStr = executePost(url,params);
        ResultInfo ri =  parse2ResultInfo(jsonStr);
        if (ri.getError() != null){
            throw new Exception(ri.getError());
        }
    }

    @Override
    public Boolean invokeApp(String WFMSName, String handle,
                             String appName, String specInstId, String workItemId) throws Exception {
        String url = ROOT_URL+INVOKE_PATH;
        Map<String,String> params = new HashMap<String, String>();
        params.put("WFMSName",WFMSName);
        params.put("handle",handle);
        params.put("appName",appName);
        params.put("specInstId",specInstId);
        params.put("workItemId",workItemId);

        String jsonStr = executePost(url,params);
        ResultInfo ri =  parse2ResultInfo(jsonStr);
        if (ri.getError() != null){
            throw new Exception(ri.getError());
        }

        return (Boolean)ri.getData();
    }

    @Override
    public String requestAppInfo(String WFMSName, String handle,
                                  String appName) throws Exception {
        String url = ROOT_URL+INFO_PATH;
        Map<String,String> params = new HashMap<String, String>();
        params.put("WFMSName",WFMSName);
        params.put("handle",handle);
        params.put("appName",appName);
//        params.put("specInstId",specInstId);
//        params.put("workItemId",workItemId);

        String jsonStr = executePost(url,params);
        ResultInfo ri =  parse2ResultInfo(jsonStr);
        if (ri.getError() != null){
            throw new Exception(ri.getError());
        }

        return (String)ri.getData();
    }

    @Override
    public Boolean terminateApp(String WFMSName, String handle,
                                String appName, String specInstId, String workItemId) throws Exception {
        String url = ROOT_URL+TERMINATE_PATH;
        Map<String,String> params = new HashMap<String, String>();
        params.put("WFMSName",WFMSName);
        params.put("handle",handle);
        params.put("appName",appName);
        params.put("specInstId",specInstId);
        params.put("workItemId",workItemId);

        String jsonStr = executePost(url,params);
        ResultInfo ri =  parse2ResultInfo(jsonStr);
        if (ri.getError() != null){
            throw new Exception(ri.getError());
        }

        return (Boolean)ri.getData();
    }
}
