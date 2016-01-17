package com.sysu.toolService.util;

import com.sysu.toolCommons.util.SysLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by adam on 2015/12/8.
 */
public class SystemInfoProperties extends Properties implements SysLogger{

    private String confPath = "/SystemInfo.properties";

    private static SystemInfoProperties instance;

    public static synchronized SystemInfoProperties getInstance(){
        if (instance == null){
            instance = new SystemInfoProperties();
        }

        return instance;
    }

    {
        try {
            load(SystemInfoProperties.class.getResourceAsStream(confPath));
        } catch (IOException e) {
            logger.error(SystemInfoProperties.class+" fail to load conf file",e);
        }
    }

    private SystemInfoProperties(){}

    public String getPlantFormHost(){
        return getProperty("sys.platform.host","http://localhost:8082");
    }

    public String getPlantFormRSPath(){
        String host = getPlantFormHost();
        String contentName = getProperty("sys.platform.content","tpf");
        String rsPathName = getProperty("sys.platform.rsPath", "remoting");
        return host+"/"+contentName+"/"+rsPathName+"/";
    }

    public String getPlantFormWQPath(){
        String host = getPlantFormHost();
        String contentName = getProperty("sys.platform.content","tpf");
        String wqPathName = getProperty("sys.platform.wqPath","remoting");

        return host+"/"+contentName+"/"+wqPathName+"/";
    }

    public String getPlantFormInterfaceBPath(){
        String host = getPlantFormHost();
        String contentName = getProperty("sys.platform.content","tpf");
        String ibPathName = getProperty("sys.platform.ibPath","remoting");

        return host+"/"+contentName+"/"+ibPathName+"/";
    }

    public String getPlatformInterfaceCURL(){
        return getProperty("sys.platform.interfaceC.url","http://localhost:8082/ic/");
    }
}
