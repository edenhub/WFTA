package Test.Util;

import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolService.util.WorkItemParamParser.WorkItemParamParser;
import com.sysu.toolService.util.WorkItemParamParser.WorkItemParams;
import com.sysu.toolService.util.WorkQueueManagerUtil;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

        ResultInfo resultInfo = WorkQueueManagerUtil.completeWorkItemData(itemId, pid);

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

    @Test
    public void testGetWorkItemParams() throws IOException, DocumentException {
        String itemId = "29.2:Determine_Credit_Requirements";
//        String itemId = "24.2:Determine_Credit_Requirements";
        String handle = "6db7ca82-b86d-4999-9879-229f35e448e2";

        ResultInfo ret = WorkQueueManagerUtil.requestWorkItemParams(itemId, handle);

        System.out.println(ret);
    }

    @Test
    public void testParseWorkItemParams() throws DocumentException, UnsupportedEncodingException {
        WorkItemParamParser paramParser = WorkItemParamParser.getInstance();
        String str = "<params>\n" +
                "  <outputParam>\n" +
                "    <index>1</index>\n" +
                "    <ordering>1</ordering>\n" +
                "    <name>userSex</name>\n" +
                "    <type>int</type>\n" +
                "    <namespace>http://www.w3.org/2001/XMLSchema</namespace>\n" +
                "    <defaultValue>0</defaultValue>\n" +
                "  </outputParam>\n" +
                "  <outputParam>\n" +
                "    <index>0</index>\n" +
                "    <ordering>0</ordering>\n" +
                "    <name>userName</name>\n" +
                "    <type>string</type>\n" +
                "    <namespace>http://www.w3.org/2001/XMLSchema</namespace>\n" +
                "  </outputParam>\n" +
                "  <outputParam>\n" +
                "    <index>2</index>\n" +
                "    <ordering>2</ordering>\n" +
                "    <name>userAge</name>\n" +
                "    <type>int</type>\n" +
                "    <namespace>http://www.w3.org/2001/XMLSchema</namespace>\n" +
                "  </outputParam>\n" +
                "  <outputParam>\n" +
                "    <index>3</index>\n" +
                "    <ordering>3</ordering>\n" +
                "    <name>userInCome</name>\n" +
                "    <type>double</type>\n" +
                "    <namespace>http://www.w3.org/2001/XMLSchema</namespace>\n" +
                "  </outputParam>\n" +
                "</params>";
        WorkItemParams params = paramParser.parse(str);
        WorkItemParams paramsSort = paramParser.parse(str, WorkItemParamParser.OrderType.Ordering);

        System.out.println(params);
        System.out.println(paramsSort);
    }
}
