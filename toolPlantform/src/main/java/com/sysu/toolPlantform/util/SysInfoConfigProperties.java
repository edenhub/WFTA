package com.sysu.toolPlantform.util;

import com.sysu.toolCommons.util.SysLogger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by adam on 2016/1/13.
 */
public class SysInfoConfigProperties extends Properties implements SysLogger{
    private String confPath = "/SysInfoConfig.properties";

    private static SysInfoConfigProperties instance;

    public static synchronized SysInfoConfigProperties getInstance(){
        if (instance == null){
            instance = new SysInfoConfigProperties();
        }

        return instance;
    }

    {
        try {
            load(SysInfoConfigProperties.class.getResourceAsStream(confPath));
        } catch (IOException e) {
            logger.error(SysInfoConfigProperties.class+" fail to load conf file",e);
        }
    }

    private long calMultiplate(String str){
        String[] slices = str.split("\\*");
        long ret = 1;
        for(String s : slices){
            s = s.trim();
            if (s == null || s.trim().equals(""))
                return 0;
            else
                ret *= Long.parseLong(s);
        }

        return ret;
    }

    public String getSessionCacheName(){
        return getProperty("config.cache.name.session","Session_Cache");
    }

    public long getSessionCachePeriod(){
        return calMultiplate(getProperty("config.cache.period.session","1000 * 60 * 30"));
    }

    public long getSessionCacheLive(){
        return calMultiplate(getProperty("config.cache.live.session","1000 * 60 * 30"));
    }


    public String getAppCacheName(){
        return getProperty("config.cache.name.app","Session_Cache");
    }

    public long getAppCachePeriod(){
        return calMultiplate(getProperty("config.cache.period.app","1000 * 60 * 30"));
    }

    public long getAppCacheLive(){
        return calMultiplate(getProperty("config.cache.live.app","1000 * 60 * 30"));
    }

    public String getWorkFlowToken(String WFMSName){
        return getProperty("config.token."+WFMSName);
    }

    public String getAppToken(String appName){
        return getProperty("config.app.token."+appName);
    }

    public String getAppUrl(String appName){
        return getProperty("config.app.url."+appName);
    }

    public String getAppInvokeUrl(String appName){
        String url =  getProperty("config.app.invoke.url."+appName);
        String appUrl = getAppUrl(appName);
//        System.out.println(appName);
//        System.out.println(url);
//        System.out.println(appUrl);
        if (!url.startsWith(appUrl)) return null;

        return url;
    }
}
