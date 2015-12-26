package Test.Util;

import com.sysu.toolService.util.ResourceManagerUtil;
import org.junit.Test;
import org.yawlfoundation.yawl.resourcing.resource.Participant;
import org.yawlfoundation.yawl.resourcing.resource.Role;
import org.yawlfoundation.yawl.resourcing.rsInterface.ResourceGatewayException;

import java.io.IOException;
import java.util.List;

/**
 * Created by adam on 2015/12/8.
 */
public class TestRSManageUtil {

    @Test
    public void testGetRoles() throws IOException, ResourceGatewayException {
        List<Role> roles = ResourceManagerUtil.requestRoles();
        for (Role role : roles){
            System.out.println(role.getID());
            System.out.println(role.getName());
        }
    }

    @Test
    public void testParticipatantRoles() throws IOException, ResourceGatewayException {
        String pid = "PA-65de4a58-7f24-42b0-ae91-c65102d404b9";

        List<Role> roles = ResourceManagerUtil.requestParticipatantRoles(pid);
        for (Role role : roles){
            System.out.println(role.getID());
            System.out.println(role.getName());
        }
    }

    @Test
    public void testParticipantInfo() throws IOException, ResourceGatewayException {
        String pid = "PA-2ac65bb2-7b9a-4a78-8f0a-24de1385fc2a";

        Participant pat = ResourceManagerUtil.requestParticipantInstance(pid);

        System.out.println(pat.toXML());
        System.out.println(pat.getFullName());
    }
}
