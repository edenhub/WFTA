package com.sysu.toolService.util.WorkItemParamParser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by adam on 2016/1/9.
 */
public class WorkItemParamParser {

    public static enum OrderType{
        Index,Ordering
    }

    private static WorkItemParamParser instance = new WorkItemParamParser();

    private SAXReader reader;

    private WorkItemParamParser(){
        reader = new SAXReader();
    }

    public static WorkItemParamParser getInstance(){
        return instance;
    }

    private String wrapperParamStr(String paramStr){
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(paramStr);

        return sb.toString();
    }

    private String parseElementTextTrim(Element e){
        String ret = null;
        if (e != null){
            ret = e.getText();
        }

        return ret;
    }

    private class IndexComparator implements Comparator<WorkItemParams.WorkItemParam> {

        @Override
        public int compare(WorkItemParams.WorkItemParam o1, WorkItemParams.WorkItemParam o2) {
            if (o1.getIndex() < o2.getIndex()) return -1;
            else if (o1.getIndex() == o2.getIndex()) return 0;
            else return 1;
        }
    }

    public Comparator<WorkItemParams.WorkItemParam> newIndexComparator(){
        return new IndexComparator();
    }

    public Comparator<WorkItemParams.WorkItemParam> newOrderingComparator(){
        return new OrderingComparator();
    }

    private class OrderingComparator implements Comparator<WorkItemParams.WorkItemParam>{

        @Override
        public int compare(WorkItemParams.WorkItemParam o1, WorkItemParams.WorkItemParam o2) {
            if (o1.getOrdering() < o2.getOrdering()) return -1;
            else if (o1.getOrdering() == o2.getOrdering()) return 0;
            else return 1;
        }
    }

    public WorkItemParams parse(String paramStr,OrderType orderType)
            throws UnsupportedEncodingException, DocumentException {
        WorkItemParams params = parse(paramStr);
        return parse(params,orderType);
    }

    public WorkItemParams parse(WorkItemParams params,OrderType orderType)
            throws UnsupportedEncodingException, DocumentException {
        Comparator comparator = null;
        switch (orderType){
            case Index:{
                comparator = newIndexComparator();
                break;
            }
            case Ordering:{
                comparator = newOrderingComparator();
                break;
            }
            default:{
                throw new RuntimeException("Error OrderType");
            }
        }

        List<WorkItemParams.WorkItemParam> outputs = params.getOutput();
        Collections.sort(outputs,comparator);
        List<WorkItemParams.WorkItemParam> inputs = params.getInput();
        Collections.sort(inputs,comparator);
        List<WorkItemParams.WorkItemParam> allParam = params.getParams();
        Collections.sort(allParam,comparator);

        return params;
    }

    public WorkItemParams parse(String paramStr) throws DocumentException, UnsupportedEncodingException {
        Document doc = reader.read(new ByteArrayInputStream(paramStr.getBytes("UTF-8")));
        WorkItemParams retParams = new WorkItemParams();

        Element root = doc.getRootElement();
        List<Element> outputs = root.selectNodes("outputParam");
        List<Element> inputs = root.selectNodes("inputParam");

        for (Element e : outputs){
            String index = parseElementTextTrim(e.element("index"));
            String ordering = parseElementTextTrim(e.element("ordering"));
            String name = parseElementTextTrim(e.element("name"));
            String type = parseElementTextTrim(e.element("type"));
            String defaultValue = parseElementTextTrim(e.element("defaultValue"));

            WorkItemParams.WorkItemParam param = new WorkItemParams.WorkItemParam();
            param.setIndex(Integer.parseInt(index));
            param.setOrdering(Integer.parseInt(ordering));
            param.setName(name);
            param.setType(type);
            param.setDefaultValue(defaultValue);

            retParams.addOutputParam(param);
            retParams.addParam(param);
        }

        for(Element e : inputs){
            String index = parseElementTextTrim(e.element("index"));
            String ordering = parseElementTextTrim(e.element("ordering"));
            String name = parseElementTextTrim(e.element("name"));
            String type = parseElementTextTrim(e.element("type"));
            String defaultValue = parseElementTextTrim(e.element("defaultValue"));

            WorkItemParams.WorkItemParam param = new WorkItemParams.WorkItemParam();
            param.setIndex(Integer.parseInt(index));
            param.setOrdering(Integer.parseInt(ordering));
            param.setName(name);
            param.setType(type);
            param.setDefaultValue(defaultValue);

            retParams.addInputParam(param);
            retParams.addParam(param);
        }

        return retParams;
    }
}
