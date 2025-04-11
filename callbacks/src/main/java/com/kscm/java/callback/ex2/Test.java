package com.kscm.java.callback.ex2;

import org.mockito.Mockito;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class Test {
    public static void main(String[] args) {
        //EventListener listener = Mockito.mock(AsynchronousEventListenerImpl.class);
        AsynchronousEventListenerImpl listener = new AsynchronousEventListenerImpl();
        AsynchronousEventConsumer synchronousEventListenerConsumer = new AsynchronousEventConsumer(listener);
        synchronousEventListenerConsumer.doAsynchronousOperation();

        //verify(listener, timeout(1000).times(1)).onTrigger();
    }
}
