package Test.Util;

import com.sysu.toolService.util.WorkQueueManagerUtil;
import org.junit.Test;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;

import java.io.IOException;

/**
 * Created by adam on 2015/12/21.
 */
public class TestWQGatewayManageUtil {
    String itemId = "4.1:Step1";
    String handler = "e174d119-09a4-425f-94d9-5a70d64fc720";

    @Test
    public void testRequestWorkItem() throws IOException {

        String itemStr = WorkQueueManagerUtil.requestWorkItemString(itemId,handler);

        System.out.println(itemStr);
    }

    @Test
    public void testRequestWorkItemInstance() throws IOException {
//        String itemId = "3.1:Step1";
//        String handler = "66b5a687-4449-4fe2-a035-bb8d87e689d7";
        WorkItemRecord workItemRecord = WorkQueueManagerUtil.requestWorkItemInstance(itemId,handler);

        System.out.println(workItemRecord.toXML());
    }
}
