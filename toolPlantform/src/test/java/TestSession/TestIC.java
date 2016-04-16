package TestSession;

import com.sysu.toolCommons.interfaceC.tool.InterfaceC_ToolSideService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by adam on 2016/4/14.
 */
public class TestIC {

    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(InterfaceC_ToolSideService.class);
        factory.setAddress("http://localhost:8081/form/ic/icFormService?wsdl");
        InterfaceC_ToolSideService hello = (InterfaceC_ToolSideService) factory.create();
//        String retInfo = hello.testAction("adam");
//        System.out.println(retInfo);
//    String xml = hello.sayHello("zhangsan");
        // JSONObject obj = hello.sayJson("liqineng");
        // parserXml(xml);
//    String str = hello.sayJsonStr("liqineng");
//    ParseJsonStr(str);
        // System.out.println(xml);
    }
}
