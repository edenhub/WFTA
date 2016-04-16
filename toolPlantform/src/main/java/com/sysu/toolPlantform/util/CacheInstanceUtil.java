package com.sysu.toolPlantform.util;

import com.sysu.toolCommons.cache.CacheFuncAction;
import com.sysu.toolCommons.cache.LogActionEasyCache;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by adam on 2016/1/13.
 */
public class CacheInstanceUtil {

    private SysInfoConfigProperties configProperties = SysInfoConfigProperties.getInstance();

    private CacheInstanceUtil(){
        sessionCache = new LogActionEasyCache<String, String>(
                configProperties.getSessionCacheName(),
                configProperties.getSessionCachePeriod(),
                configProperties.getSessionCacheLive()
        );

        appCache = new LogActionEasyCache<String, String>(
                configProperties.getAppCacheName(),
                configProperties.getAppCachePeriod(),
                configProperties.getAppCacheLive()
        );

        jaxWsClientCache = new LogActionEasyCache<String, JaxWsProxyFactoryBean>(
                configProperties.getJaxWsClientName(),
                configProperties.getJaxWsClientPeriod(),
                configProperties.getJaxWsClientLive()
        );
    }

    private static CacheInstanceUtil instance = new CacheInstanceUtil();

    //appName or WFMSNAME -> handle
    private CacheFuncAction<String,String> sessionCache;

    private CacheFuncAction<String,String> appCache;

    private CacheFuncAction<String,JaxWsProxyFactoryBean> jaxWsClientCache;

    public static CacheInstanceUtil getInstance() {
        return instance;
    }

    public CacheFuncAction<String, String> getSessionCache() {
        return sessionCache;
    }

    public CacheFuncAction<String, String> getAppCache() {
        return appCache;
    }

    public CacheFuncAction<String, JaxWsProxyFactoryBean> getJaxWsClientCache() {
        return jaxWsClientCache;
    }
}
