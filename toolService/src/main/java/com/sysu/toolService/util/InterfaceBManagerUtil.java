package com.sysu.toolService.util;

import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolService.util.ResponseHandler.ResultInfoResponseHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 2015/12/26.
 */
public class InterfaceBManagerUtil {

    private static SystemInfoProperties sysInfo;

    private InterfaceBManagerUtil(){}

    static {
        sysInfo = SystemInfoProperties.getInstance();
    }

    private static final String RESOURCE_SPEC_PATH = "taskResource";

    private static final String ALLOCATE_ROLES_PATH = "taskAllocateRoles";

    private static boolean isSuccess(String rsStr){
        if (rsStr.contains("<failure>")) return false;
        return true;
    }

    public static String requestTaskResoucreSpec(String idSpec,
                                                 String verSpec,
                                                 String uriSpec,
                                                 String taskIdSpec) throws IOException {

        String url = sysInfo.getPlantFormInterfaceBPath()+RESOURCE_SPEC_PATH
                +"?idSpec="+idSpec+"&verSpec="+verSpec+"&uriSpec="+uriSpec+"&taskIdSpec="+taskIdSpec;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();

        ResultInfo result = httpClient.execute(httpGet,rh);

        if (result.isSuccess()){
            String rsStr = (String)result.getData();
            return rsStr;
        }else{
            throw new IOException(result.getMsg(),result.getError());
        }
    }

    public static class TaskAllocateRolesInfo{
        private boolean isSuccess;

        private String msg;

        private List<String> roles;

        public boolean isSuccess() {
            return isSuccess;
        }

        public void setSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public static TaskAllocateRolesInfo newSuccessInfo(List<String> roles){
            TaskAllocateRolesInfo rolesInfo = new TaskAllocateRolesInfo();
            rolesInfo.setRoles(roles);
            rolesInfo.setSuccess(true);

            return rolesInfo;
        }

        public static TaskAllocateRolesInfo newFailInfo(String msg){
            TaskAllocateRolesInfo rolesInfo = new TaskAllocateRolesInfo();
            rolesInfo.setSuccess(false);
            rolesInfo.setMsg(msg);

            return rolesInfo;
        }
    }

    public static TaskAllocateRolesInfo requestTaskAllocateRoles(String idSpec,
                                                        String verSpec,
                                                        String uriSpec,
                                                        String taskIdSpec) throws IOException, DocumentException {
        String url = sysInfo.getPlantFormInterfaceBPath()+ALLOCATE_ROLES_PATH
                +"?idSpec="+idSpec+"&verSpec="+verSpec+"&uriSpec="+uriSpec+"&taskIdSpec="+taskIdSpec;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();

        ResultInfo result = httpClient.execute(httpGet,rh);

        if (result.isSuccess()){
            String rsStr = (String)result.getData();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new StringReader(rsStr));
            if (isSuccess(rsStr)){
                Element root = doc.getRootElement();
                Element offer = root.element("offer");
                Element distributionSet = offer.element("distributionSet");
                Element initialSet = distributionSet.element("initialSet");

                List<Element> roles = initialSet.content();
                List<String> roleIDs = new ArrayList<String>(roles.size());
                for (Element e : roles){
                    roleIDs.add(e.getText());
                }

                return TaskAllocateRolesInfo.newSuccessInfo(roleIDs);
            }else{
                List<Node> reasons = doc.selectNodes("/response/failure/reason");
                String reason = reasons.get(0).getText();
                return TaskAllocateRolesInfo.newFailInfo(reason);
            }
        }else{
            throw new IOException(result.getMsg(),result.getError());
        }
    }


}
