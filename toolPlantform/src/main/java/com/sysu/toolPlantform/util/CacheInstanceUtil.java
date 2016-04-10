package com.sysu.toolPlantform.util;

import com.sysu.toolCommons.cache.CacheFuncAction;
import com.sysu.toolCommons.cache.LogActionEasyCache;

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
    }

    private static CacheInstanceUtil instance = new CacheInstanceUtil();

    //appName or WFMSNAME -> handle
    private CacheFuncAction<String,String> sessionCache;

    private CacheFuncAction<String,String> appCache;

    public static CacheInstanceUtil getInstance() {
        return instance;
    }

    public CacheFuncAction<String, String> getSessionCache() {
        return sessionCache;
    }

    public CacheFuncAction<String, String> getAppCache() {
        return appCache;
    }
}
