package com.sysu.toolPlantform.controller;

import com.sysu.toolCommons.util.AjaxExeTemplate;
import com.sysu.toolCommons.util.SysLogger;
import com.sysu.toolCommons.web.ParameterUtil;
import com.sysu.toolPlantform.YAWLClient.YAWLInterfaceBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yawlfoundation.yawl.engine.YSpecificationID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 2015/12/26.
 */

@Controller
public class InterfaceBServiceController implements SysLogger{

    @Autowired
    private YAWLInterfaceBClient ibClient;

    public YAWLInterfaceBClient getIbClient() {
        return ibClient;
    }

    public void setIbClient(YAWLInterfaceBClient ibClient) {
        this.ibClient = ibClient;
    }

    @RequestMapping("/taskResource")
    public void getTaskResource(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil param = new ParameterUtil(request);

        String identifierSpec = param.getStringParam("idSpec");
        String versionSpec = param.getStringParam("verSpec");
        String uriSpec = param.getStringParam("uriSpec");
        final String taskIdSpec = param.getStringParam("taskIdSpec");

        final YSpecificationID id = new YSpecificationID(identifierSpec,versionSpec,uriSpec);

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                String retStr = ibClient.getTaskResouceSepces(id,taskIdSpec);

                return retStr;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("getTaskResource error",ex);
            }
        }.exe(request, response);
    }

    @RequestMapping("/taskAllocateRoles")
    public void getTaskAllocateRoles(HttpServletRequest request,HttpServletResponse response){
        ParameterUtil param = new ParameterUtil(request);

        String identifierSpec = param.getStringParam("idSpec");
        String versionSpec = param.getStringParam("verSpec");
        String uriSpec = param.getStringParam("uriSpec");
        final String taskIdSpec = param.getStringParam("taskIdSpec");

        final YSpecificationID id = new YSpecificationID(identifierSpec,versionSpec,uriSpec);

        new AjaxExeTemplate() {
            @Override
            public Object doExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
                String retStr = ibClient.getTaskAllocateRoles(id,taskIdSpec);

                return retStr;
            }

            @Override
            public void holdException(HttpServletRequest request, HttpServletResponse respose, Exception ex) {
                logger.error("getTaskAllocateRoels error",ex);
            }
        }.exe(request, response);
    }
}
