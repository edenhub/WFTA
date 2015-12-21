package com.sysu.toolService.util;

import com.sysu.toolCommons.result.ResultInfo;
import com.sysu.toolService.util.ResponseHandler.ResultInfoResponseHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jdom.Element;
import org.yawlfoundation.yawl.resourcing.resource.AbstractResourceAttribute;
import org.yawlfoundation.yawl.resourcing.resource.Role;
import org.yawlfoundation.yawl.resourcing.rsInterface.ResourceGatewayException;
import org.yawlfoundation.yawl.util.JDOMUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 2015/12/7.
 */
public class ResourceManagerUtil {

    private static SystemInfoProperties sysInfo;

    private ResourceManagerUtil(){
    }

    static {
        sysInfo = SystemInfoProperties.getInstance();
    }

    private static final String ROLES_PATH = "roles";

    private static final String PARTI2ROLES_PATH_PRE = "participantRoles/";



    /*********************   YAWL's code' *******************/

    /**
     * Converts a string version of a JDOM Element into a List of instantiated
     * ResourceAttribute child objects (i.e. Role, Position, Capability, OrgGroup)
     * @param xml the string to be converted to a JDOM Element
     * @param className the type of child class to instantiate
     * @return a List of instantiated objects
     */
    private static List<AbstractResourceAttribute> xmlStringToResourceAttributeList(
            String xml, String className) {
        List<AbstractResourceAttribute> result = new ArrayList<AbstractResourceAttribute>();

        // get List of child elements
        List eList = getChildren(xml);
        if (eList != null) {
            for (Object o : eList) {

                // instantiate a class of the appropriate type
                AbstractResourceAttribute ra = newAttributeClass(className);
                if (ra != null) {

                    // pass the element to the new object to repopulate members
                    ra.reconstitute((Element) o);
                    result.add(ra);
                }
            }
        }
        return result;
    }

    /**
     * Converts the string passed to a JDOM Element and returns its child Elements
     * @param s the xml string to be converted
     * @return a list of child elements of the converted element passed
     */
    private static List getChildren(String s) {
        if (s == null) return null;
        Element parent = JDOMUtil.stringToElement(s);
        return (parent != null) ? parent.getChildren() : null;
    }

    /**
     * Creates a new class instance of the name passed.
     * @pre className is the valid name of a class that extends from
     *      the base AbstractResourceAtttribute
     * @param className the name of the extended class to create
     * @return An instantiated class of type 'className', or null if there's a problem
     */
    private static AbstractResourceAttribute newAttributeClass(String className) {
        String pkg = "org.yawlfoundation.yawl.resourcing.resource." ;
        try {
            return (AbstractResourceAttribute) Class.forName(pkg + className)
                    .newInstance() ;
        }
        catch (Exception cnfe) {
            return null ;
        }
    }

    // PRIVATE METHODS //

    private static boolean successful(String result) {
        return (result != null) && (! result.startsWith("<failure>"));
    }


    private static String successCheck(String xml) throws ResourceGatewayException {
        if (successful(xml)) {
            return xml;
        }
        else throw new ResourceGatewayException(xml);
    }

    /*********************   YAWL's code' *******************/



    public static List<Role> requestRoles() throws IOException, ResourceGatewayException {
        String url = sysInfo.getPlantFormRSPath()+ROLES_PATH;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();

        ResultInfo result = httpClient.execute(httpGet,rh);

        if (result.isSuccess()){
            String rsStr = (String)result.getData();
            rsStr = successCheck(rsStr);
            List ret = xmlStringToResourceAttributeList(rsStr,"Role");
            return ret;
        }else{
            throw new IOException(result.getMsg(),result.getError());
        }
    }

    public static List<Role> requestParticipatantRoles(String pid) throws IOException, ResourceGatewayException {
        String url = sysInfo.getPlantFormRSPath()+PARTI2ROLES_PATH_PRE+pid;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        ResponseHandler<ResultInfo> rh = new ResultInfoResponseHandler();

        ResultInfo result = httpClient.execute(httpGet,rh);

        if (result.isSuccess()){
            String rsStr = (String)result.getData();
            rsStr = successCheck(rsStr);
            List ret = xmlStringToResourceAttributeList(rsStr,"Role");
            return ret;
        }else{
            throw new IOException(result.getMsg(),result.getError());
        }
    }
}
