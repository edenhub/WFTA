package com.sysu.toolPlantform.YAWLClient;

/**
 * Created by adam on 2015/12/8.
 */
public class YAWLAdminInfo {
    private String username;

    private String password;

    private String rsGatewayUrl;

    private String wqGatewayUrl;

    private String interfaceBUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRsGatewayUrl() {
        return rsGatewayUrl;
    }

    public void setRsGatewayUrl(String rsGatewayUrl) {
        this.rsGatewayUrl = rsGatewayUrl;
    }

    public String getWqGatewayUrl() {
        return wqGatewayUrl;
    }

    public void setWqGatewayUrl(String wqGatewayUrl) {
        this.wqGatewayUrl = wqGatewayUrl;
    }

    public String getInterfaceBUrl() {
        return interfaceBUrl;
    }

    public void setInterfaceBUrl(String interfaceBUrl) {
        this.interfaceBUrl = interfaceBUrl;
    }
}
