package com.kscm.java.callback.ex1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class Test {
    public static void main(String[] args) {
        EventListener listener = new SynchronousEventListenerImpl();
        SynchronousEventConsumer synchronousEventConsumer = new SynchronousEventConsumer(listener);
        String result = synchronousEventConsumer.doSynchronousOperation();

        assertNotNull(result);
        assertEquals("Synchronously running callback function", result);
    }
}
