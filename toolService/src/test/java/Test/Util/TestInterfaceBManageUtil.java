package Test.Util;

import com.sysu.toolService.util.InterfaceBManagerUtil;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by adam on 2015/12/26.
 */
public class TestInterfaceBManageUtil {
    private String idSpec = "UID_ba9f200f-c009-4d76-be6a-b341ff435259";
    private String verSpec = "2.1";
    private String uriSpec = "SaveHoke";

    private String taskIdSpec = "Step1";

    @Test
    public void testRequestTaskResource() throws IOException {
        String rsStr = InterfaceBManagerUtil.requestTaskResoucreSpec(idSpec,verSpec,uriSpec,taskIdSpec);

        System.out.println(rsStr);
    }

    @Test
    public void testRequestTaskAllocate() throws IOException, DocumentException {
        InterfaceBManagerUtil.TaskAllocateRolesInfo rolesInfo =
                InterfaceBManagerUtil.requestTaskAllocateRoles(idSpec,verSpec,uriSpec,taskIdSpec);
        for (String role : rolesInfo.getRoles()){
            System.out.println(role);
        }
    }
}
