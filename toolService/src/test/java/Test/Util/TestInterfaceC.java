package Test.Util;

import com.sysu.toolCommons.interfaceC.platform.InterfaceC_ClientSideVisitor;
import com.sysu.toolService.interfaceC.YInterfaceC_ClientSideVisitorImpl;
import org.junit.Test;

/**
 * Created by adam on 2016/1/31.
 */
public class TestInterfaceC {

    public static String WFMSName = "YAWL";

    public static String token = "09ad17ac-a7d5-3148-8bdd-88394a9f89eb";

    public static String appName = "formengine";

    @Test
    public void testRequest() throws Exception {
        InterfaceC_ClientSideVisitor visitor = new YInterfaceC_ClientSideVisitorImpl();
        String handle = visitor.connect(WFMSName,token);
        String state = visitor.requestAppInfo(WFMSName,handle,appName);

        boolean isAppHandle = visitor.invokeApp(WFMSName,handle,appName,"aa","ss");

        System.out.println(state);
        System.out.println(isAppHandle);
    }
}
