package com.sysu.toolPlantform.util;

import com.sysu.toolCommons.cache.EasyCache;
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
    private EasyCache<String,String> sessionCache;

    private EasyCache<String,String> appCache;

    public static CacheInstanceUtil getInstance() {
        return instance;
    }

    public EasyCache<String, String> getSessionCache() {
        return sessionCache;
    }

    public EasyCache<String, String> getAppCache() {
        return appCache;
    }
}
