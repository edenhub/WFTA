package Test.Util;

import com.sysu.toolCommons.result.ResultInfo;
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

    @Test
    public void testUpdate() throws IOException {
        String itemId = "20.1:Step1";
        String handle = "7f453a46-9497-4bac-80fe-4bd18be295ec";
        String updateStr = "<Step1><strate1>null4444444</strate1></Step1>";

        ResultInfo resultInfo = WorkQueueManagerUtil.updateWorkItemData(itemId,handle,updateStr);

        System.out.println(resultInfo);
        resultInfo.getError().printStackTrace();
    }

    @Test
    public void complateUpdate() throws IOException {
        String itemId = "20.1:Step1";
        String handle = "7f453a46-9497-4bac-80fe-4bd18be295ec";
        String pid = "PA-2ac65bb2-7b9a-4a78-8f0a-24de1385fc2a";

        ResultInfo resultInfo = WorkQueueManagerUtil.completeWorkItemData(itemId, handle, pid);

        System.out.println(resultInfo);
        resultInfo.getError().printStackTrace();
    }

    @Test
    public void updateAndComplate() throws IOException {
        String itemId = "21.1:Step1";
        String handle = "7f453a46-9497-4bac-80fe-4bd18be295ec";
        String updateStr = "<Step1><strate1>null4444444</strate1></Step1>";
        String pid = "PA-2ac65bb2-7b9a-4a78-8f0a-24de1385fc2a";

        ResultInfo resultInfo = WorkQueueManagerUtil.updateAndCompleteWorkItemData(itemId, handle, pid,updateStr);

        System.out.println(resultInfo);
        resultInfo.getError().printStackTrace();
    }
}
