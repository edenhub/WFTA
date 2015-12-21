package Test.Util;

import com.sysu.toolService.util.WorkQueueManagerUtil;
import org.junit.Test;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;

import java.io.IOException;

/**
 * Created by adam on 2015/12/21.
 */
public class TestWQGatewayManageUtil {

    @Test
    public void testRequestWorkItem() throws IOException {
        String itemId = "3.1:Step1";
        String handler = "66b5a687-4449-4fe2-a035-bb8d87e689d7";
        String itemStr = WorkQueueManagerUtil.requestWorkItemString(itemId,handler);

        System.out.println(itemStr);
    }

    @Test
    public void testRequestWorkItemInstance() throws IOException {
        String itemId = "3.1:Step1";
        String handler = "66b5a687-4449-4fe2-a035-bb8d87e689d7";
        WorkItemRecord workItemRecord = WorkQueueManagerUtil.requestWorkItemInstance(itemId,handler);

        System.out.println(workItemRecord.toXML());
    }
}
