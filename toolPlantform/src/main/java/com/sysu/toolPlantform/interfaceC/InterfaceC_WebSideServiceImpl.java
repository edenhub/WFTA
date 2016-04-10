package com.sysu.toolPlantform.interfaceC;

import com.sysu.toolCommons.cache.CacheFuncAction;
import com.sysu.toolCommons.interfaceC.InterfaceC_Exeception;
import com.sysu.toolCommons.interfaceC.platform.InterfaceC_WebSideService;
import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolCommons.util.EncryptUtils;
import com.sysu.toolPlantform.util.CacheInstanceUtil;
import com.sysu.toolPlantform.util.SysInfoConfigProperties;
import com.sysu.toolPlantform.util.TokenValidateUtil;
import com.sysu.toolPlantform.web.HttpClient4ResultInfo;
import com.sysu.toolPlantform.web.HttpUrlValidator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adam on 2016/1/13.
 */

@Component
public class InterfaceC_WebSideServiceImpl implements InterfaceC_WebSideService {
    private CacheFuncAction<String,String> sessionCache;

    private CacheFuncAction<String,String> appCache;

    private SysInfoConfigProperties configPros = SysInfoConfigProperties.getInstance();

    @PostConstruct
    public void init(){
        sessionCache = CacheInstanceUtil.getInstance().getSessionCache();
        appCache = CacheInstanceUtil.getInstance().getAppCache();
    }

    @PreDestroy
    public void destroy(){
        sessionCache.shutDown();
        appCache.shutDown();
    }

    @Override
    public String connect(String WFMSName, String token) throws InterfaceC_Exeception {
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle != null) return srcHandle;
        boolean isValidate = TokenValidateUtil.isWFMSTokenValidate(WFMSName,token);
        String handle = null;
        if (isValidate){
            handle = TokenValidateUtil.generateHandle();
            sessionCache.putCache(WFMSName,handle);
        }else{
            throw new InterfaceC_Exeception("invalidate name with token");
        }
        return handle;
    }

    @Override
    public void disconnect(String WFMSName, String handle) throws InterfaceC_Exeception {
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle != null){
            sessionCache.removeCache(WFMSName);
        }
    }

    private String generateUniqueId(String appName,String specInstId,String workItemId){
        StringBuilder sb = new StringBuilder();
        sb.append(appName).append("_").append(specInstId).append("_").append(workItemId);

        return EncryptUtils.encryptMD5(sb.toString());
    }

    @Override
    public boolean invokeApplication(String WFMSName, String handle,
                                     String appName, String spaceInstId,String workItemId)
            throws Exception {
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle == null || !srcHandle.equals(handle))throw new InterfaceC_Exeception("error WFMSName with handle");
        String appId = generateUniqueId(appName,spaceInstId,workItemId);
        String invokeUrl = configPros.getAppInvokeUrl(appName);
        Map<String,String> params = new HashMap<String, String>();
        params.put("appToken",configPros.getAppToken(appName));
        params.put("specInstId",spaceInstId);
        params.put("workItemId",workItemId);
        ResultInfo ri = new HttpClient4ResultInfo().requestPost(invokeUrl,params);
        if (ri.getError() != null){
            throw new Exception(ri.getError());
        }
        String appHandle = ri.getData().toString();
        appCache.putCache(appId,appHandle);
        return true;
    }

    @Override
    public String requestAppStatus(String WFMSName, String handle, String appName)
            throws InterfaceC_Exeception{
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle == null || !srcHandle.equals(handle)) throw new InterfaceC_Exeception("error WFMSName with handle");
//        String appId = generateUniqueId(appName,spaceInstId,workItemId);
//        String appHandle = appCache.getCache(appId);
//        if (appHandle == null) return AppSataus.STATUS_TERMINATE;
        String appUrl = configPros.getAppUrl(appName);
        if (!HttpUrlValidator.validate(appUrl)) return AppSataus.STATUS_TERMINATE;
        return AppSataus.STATUS_RUNNING;
    }

    @Override
    public boolean terminateApp(String WFMSName, String handle,
                                String appName, String spaceInstId,String workItemId)
            throws InterfaceC_Exeception{
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle == null || !srcHandle.equals(handle)) throw new InterfaceC_Exeception("error WFMSName with handle");
        String appId = generateUniqueId(appName,spaceInstId,workItemId);
        appCache.removeCache(appId);
        return true;
    }

    public static class AppSataus{
        /**
         * 停止状态
         */
        public static String STATUS_TERMINATE = "<terminate>";

        /**
         * 活动状态
         */
        public static String STATUS_RUNNING = "<running>";
    }
}
