package com.sysu.toolPlantform.WSClient;

import com.sysu.toolCommons.cache.CacheFuncAction;
import com.sysu.toolCommons.interfaceC.tool.InterfaceC_ToolSideService;
import com.sysu.toolPlantform.util.CacheInstanceUtil;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by adam on 2016/4/16.
 */
public class WSCallClientFactory {

    private static WSCallClientFactory instance = new WSCallClientFactory();

    private CacheFuncAction<String,JaxWsProxyFactoryBean> jaxWsClientCache;

    private WSCallClientFactory(){
        jaxWsClientCache = CacheInstanceUtil.getInstance().getJaxWsClientCache();
    }

    public static WSCallClientFactory getInstance(){
        return instance;
    }

    public void shutDown(){
        jaxWsClientCache.shutDown();
    }

    private boolean validateServiceUrl(String url,boolean withSuffix){
        if (withSuffix) return url.startsWith("http://") && url.endsWith("?wsdl");
        else return url.startsWith("http://");
    }

    public InterfaceC_ToolSideService createICWSToolSideClient(String serviceUrl,boolean withSuffix) throws Exception{
        JaxWsProxyFactoryBean factoryBean = createWsFactoryBean(serviceUrl,withSuffix);
        factoryBean.setServiceClass(InterfaceC_ToolSideService.class);
        factoryBean.setAddress(serviceUrl);

        return factoryBean.create(InterfaceC_ToolSideService.class);
    }

    public JaxWsProxyFactoryBean createWsFactoryBean(String serviceUrl,boolean withSuffix) throws Exception{
        if (!withSuffix)
            serviceUrl += "?wsdl";

        JaxWsProxyFactoryBean factoryBean = jaxWsClientCache.getCache(serviceUrl);
        if (factoryBean != null) return factoryBean;

        if (!validateServiceUrl(serviceUrl,withSuffix))
            throw new Exception("服务地址有误");
        factoryBean = new JaxWsProxyFactoryBean();
        jaxWsClientCache.putCache(serviceUrl,factoryBean);
        return factoryBean;
    }
}
