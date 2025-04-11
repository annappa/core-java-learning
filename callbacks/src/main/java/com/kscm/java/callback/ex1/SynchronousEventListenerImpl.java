package com.kscm.java.callback.ex1;

public class SynchronousEventListenerImpl implements EventListener {

    @Override
    public String onTrigger(){
        return "Synchronously running callback function";
    }
}
