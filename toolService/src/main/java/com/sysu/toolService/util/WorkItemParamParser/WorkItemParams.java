package com.sysu.toolService.util.WorkItemParamParser;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by adam on 2016/1/9.
 */
public class WorkItemParams {

    private List<WorkItemParam> output;

    private List<WorkItemParam> input;

    private List<WorkItemParam> params;

    public WorkItemParams(){
        output = new LinkedList<WorkItemParam>();
        input = new LinkedList<WorkItemParam>();
        params = new LinkedList<WorkItemParam>();
    }

    public void addOutputParam(WorkItemParam param){
        output.add(param);
    }

    public void addInputParam(WorkItemParam param){
        input.add(param);
    }

    public void addParam(WorkItemParam param){
        params.add(param);
    }

    public List<WorkItemParam> getOutput() {
        return output;
    }


    public List<WorkItemParam> getInput() {
        return input;
    }

    public List<WorkItemParam> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "WorkItemParams{" +
                "output=" + output +
                ", input=" + input +
                ", params=" + params +
                '}';
    }

    public static class WorkItemParam{
        private int index;

        private int ordering;

        private String name;

        private String type;

        private String defaultValue;


        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getOrdering() {
            return ordering;
        }

        public void setOrdering(int ordering) {
            this.ordering = ordering;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        @Override
        public String toString() {
            return "WorkItemParam{" +
                    "index=" + index +
                    ", ordering=" + ordering +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", defaultValue='" + defaultValue + '\'' +
                    '}';
        }
    }
}
