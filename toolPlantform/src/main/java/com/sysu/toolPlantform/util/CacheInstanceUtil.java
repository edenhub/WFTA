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
    }

    private static CacheInstanceUtil instance = new CacheInstanceUtil();

    private EasyCache<String,String> sessionCache;

    public static CacheInstanceUtil getInstance() {
        return instance;
    }

    public EasyCache<String, String> getSessionCache() {
        return sessionCache;
    }
}
