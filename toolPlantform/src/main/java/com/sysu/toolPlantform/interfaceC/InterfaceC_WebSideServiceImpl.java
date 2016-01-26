package com.sysu.toolPlantform.interfaceC;

import com.sysu.toolCommons.cache.EasyCache;
import com.sysu.toolCommons.interfaceC.InterfaceC_Exeception;
import com.sysu.toolCommons.interfaceC.platform.InterfaceC_WebSideService;
import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolCommons.util.EncryptUtils;
import com.sysu.toolPlantform.util.CacheInstanceUtil;
import com.sysu.toolPlantform.util.TokenValidateUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * Created by adam on 2016/1/13.
 */

@Component
public class InterfaceC_WebSideServiceImpl implements InterfaceC_WebSideService {
    private EasyCache<String,String> sessionCache;

    private EasyCache<String,String> appCache;

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
            throws InterfaceC_Exeception{
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle == null || !srcHandle.equals(handle))throw new InterfaceC_Exeception("error WFMSName with handle");
        String appId = generateUniqueId(appName,spaceInstId,workItemId);
        appCache.putCache(appId,TokenValidateUtil.generateHandle());
        return true;
    }

    @Override
    public int requestAppStatus(String WFMSName, String handle,
                                String appName, String spaceInstId,String workItemId)
            throws InterfaceC_Exeception{
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle == null || !srcHandle.equals(handle)) throw new InterfaceC_Exeception("error WFMSName with handle");
        String appId = generateUniqueId(appName,spaceInstId,workItemId);
        String appHandle = appCache.getCache(appId);
        if (appHandle == null) return AppSataus.STATUS_TERMINATE;
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

    private static class AppSataus{
        /**
         * 停止状态
         */
        public static int STATUS_TERMINATE = 0;

        /**
         * 活动状态
         */
        public static int STATUS_RUNNING = 1;
    }
}
