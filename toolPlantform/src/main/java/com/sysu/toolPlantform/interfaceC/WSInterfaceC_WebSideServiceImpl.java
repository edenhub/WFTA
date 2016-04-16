package com.sysu.toolPlantform.interfaceC;

import com.sysu.toolCommons.cache.CacheFuncAction;
import com.sysu.toolCommons.interfaceC.InterfaceC_Exeception;
import com.sysu.toolCommons.interfaceC.app.AppInfo;
import com.sysu.toolCommons.interfaceC.platform.InterfaceC_WebSideService;
import com.sysu.toolCommons.interfaceC.tool.InterfaceC_ToolSideService;
import com.sysu.toolCommons.util.EncryptUtils;
import com.sysu.toolPlantform.WSClient.WSCallClientFactory;
import com.sysu.toolPlantform.appService.action.AppServiceAction;
import com.sysu.toolPlantform.appService.domain.AppServiceInfo;
import com.sysu.toolPlantform.util.CacheInstanceUtil;
import com.sysu.toolPlantform.util.SysInfoConfigProperties;
import com.sysu.toolPlantform.util.TokenValidateUtil;
import com.sysu.toolPlantform.web.HttpUrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by adam on 2016/4/16.
 */

@Component("WSICWebSideService")
public class WSInterfaceC_WebSideServiceImpl implements InterfaceC_WebSideService {

    private CacheFuncAction<String,String> sessionCache;

    private CacheFuncAction<String,String> appCache;

    private WSCallClientFactory callClientFactory;

    private SysInfoConfigProperties configPros = SysInfoConfigProperties.getInstance();


    @Autowired
    private AppServiceAction appServiceAction;

    public AppServiceAction getAppServiceAction() {
        return appServiceAction;
    }

    public void setAppServiceAction(AppServiceAction appServiceAction) {
        this.appServiceAction = appServiceAction;
    }

    @PostConstruct
    public void init(){
        sessionCache = CacheInstanceUtil.getInstance().getSessionCache();
        appCache = CacheInstanceUtil.getInstance().getAppCache();
        callClientFactory = WSCallClientFactory.getInstance();
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
        boolean isValidate = TokenValidateUtil.isWFMSTokenValidate(WFMSName, token);
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
    public String invokeApplication(String WFMSName, String handle, String appName,
                                     String spaceInstId, String workItemId)
            throws Exception {
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle == null || !srcHandle.equals(handle))
            throw new InterfaceC_Exeception("error WFMSName with handle");
        String appId = generateUniqueId(appName,spaceInstId,workItemId);

        AppServiceInfo serviceInfo = appServiceAction.queryAppServiceByName(appName);
        if (serviceInfo == null || !serviceInfo.isUseable()) return "";
        String serviceUrl = serviceInfo.getServiceUrl();
        InterfaceC_ToolSideService toolSideService = callClientFactory.createICWSToolSideClient(serviceUrl,false);
        String ret = toolSideService.invokeApplication(WFMSName,serviceInfo.getAppToken(),spaceInstId,workItemId);
        appCache.putCache(appId,ret);
        return ret;
    }

    @Override
    public String requestAppStatus(String WFMSName, String handle,
                                   String appName) throws InterfaceC_Exeception {
        String srcHandle = sessionCache.getCache(WFMSName);
        if (srcHandle == null || !srcHandle.equals(handle)) throw new InterfaceC_Exeception("error WFMSName with handle");
//        String appId = generateUniqueId(appName,spaceInstId,workItemId);
//        String appHandle = appCache.getCache(appId);
//        if (appHandle == null) return AppSataus.STATUS_TERMINATE;
        AppServiceInfo serviceInfo = appServiceAction.queryAppServiceByName(appName);
//        System.out.println(serviceInfo);
        if (serviceInfo == null || !serviceInfo.isUseable()) return "";
        String appUrl = configPros.getAppUrl(appName);
        if (!HttpUrlValidator.validate(appUrl)) return AppSataus.STATUS_TERMINATE;
        return AppSataus.STATUS_RUNNING;
    }

    @Override
    public boolean terminateApp(String WFMSName, String handle,
                                String appName, String spaceInstId, String workItemId)
            throws InterfaceC_Exeception {
        AppServiceInfo serviceInfo = appServiceAction.queryAppServiceByName(appName);
        if (serviceInfo == null || !serviceInfo.isUseable()) return false;
        String serviceUrl = serviceInfo.getServiceUrl();
        InterfaceC_ToolSideService toolSideService = null;
        boolean ret = false;
        try {
            toolSideService = callClientFactory.createICWSToolSideClient(serviceUrl,false);
            ret = toolSideService.terminateApp(WFMSName,handle,spaceInstId,workItemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
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

        public static String STATUS_OFFLINE = "<offline>";
    }
}
