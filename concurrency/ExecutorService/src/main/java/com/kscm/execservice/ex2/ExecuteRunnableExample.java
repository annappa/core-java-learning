package com.kscm.execservice.ex2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteRunnableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        /* There is no way of obtaining the result of the executed Runnable, if necessary. You will have to use a Callable for that  */
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        executorService.shutdown();

    }
}
