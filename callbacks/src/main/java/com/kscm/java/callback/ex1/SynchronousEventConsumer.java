package com.kscm.java.callback.ex1;

public class SynchronousEventConsumer {
    private final EventListener eventListener;

    public SynchronousEventConsumer(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public String doSynchronousOperation(){
        System.out.println("Performing callback before synchronous Task");
        // any other custom operations
        return eventListener.onTrigger();
    }
}
