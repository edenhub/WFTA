package com.sysu.toolPlantform.interfaceC;

import com.sysu.toolCommons.cache.EasyCache;
import com.sysu.toolCommons.interfaceC.InterfaceC_Exeception;
import com.sysu.toolCommons.interfaceC.platform.InterfaceC_WebSideService;
import com.sysu.toolPlantform.util.CacheInstanceUtil;
import com.sysu.toolPlantform.util.TokenValidateUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by adam on 2016/1/13.
 */

@Component
public class InterfaceC_WebSideServiceImpl implements InterfaceC_WebSideService {
    private EasyCache<String,String> sessionCache;

    @PostConstruct
    public void init(){
        sessionCache = CacheInstanceUtil.getInstance().getSessionCache();
    }

    @PreDestroy
    public void destroy(){
        sessionCache.shutDown();
    }

    @Override
    public String connect(String WFMSName, String token) throws InterfaceC_Exeception {
        boolean isValidate = TokenValidateUtil.isWFMSTokenValidate(WFMSName,token);
        String handle = null;
        if (isValidate){
            handle = TokenValidateUtil.generateHandle();
            sessionCache.putCache(WFMSName,handle);
        }else{
            throw new InterfaceC_Exeception("invalidate WFMS name with token");
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
}
