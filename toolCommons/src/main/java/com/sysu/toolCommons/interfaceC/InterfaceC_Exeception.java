package com.sysu.toolCommons.interfaceC;

/**
 * Created by adam on 2016/1/13.
 */
public class InterfaceC_Exeception extends Exception {

    public InterfaceC_Exeception(){
        super();
    }

    public InterfaceC_Exeception(String msg){
        super(msg);
    }

    public InterfaceC_Exeception(String msg, Throwable throwable){
        super(msg,throwable);
    }
}
