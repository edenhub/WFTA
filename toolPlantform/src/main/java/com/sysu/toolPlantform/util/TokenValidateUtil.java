package com.sysu.toolPlantform.util;

import java.util.UUID;

/**
 * Created by adam on 2016/1/14.
 */
public class TokenValidateUtil {

    private static SysInfoConfigProperties configProperties = SysInfoConfigProperties.getInstance();

    /**
     * 该阶段只用配置文件管理token,生成规则为 WFMSName_Timestamp
     * @param name
     * @return
     */
    public static String generateToken(String name){
        UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
        return uuid.toString();
    }

    public static String generateHandle(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static boolean isWFMSTokenValidate(String WFMSName,String token){
        String proName = "config.token."+WFMSName;
        String dest = configProperties.getProperty(proName);
        if (dest == null || dest.trim().equals("") || !dest.equals(token)) return false;

        return true;
    }
}
