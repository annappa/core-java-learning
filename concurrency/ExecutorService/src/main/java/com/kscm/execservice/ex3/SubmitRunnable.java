package com.kscm.execservice.ex3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        /* The submit() method returns a Java Future object which can be used to check when the Runnable has completed. */
        Future future = executorService.submit(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        try {
            future.get();  //returns null if the task has finished correctly.
            System.out.println("isDone: " + future.isDone());
            System.out.println("isCancelled: " + future.isCancelled());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
