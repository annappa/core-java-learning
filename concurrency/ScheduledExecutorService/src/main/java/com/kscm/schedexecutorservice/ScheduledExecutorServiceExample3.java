package com.kscm.schedexecutorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample3 {
    public static void main(String[] args) {
        //First a ScheduledExecutorService is created with 5 threads in.
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        //an anonymous implementation of the Callable interface is created and passed to the schedule() method
        //The two last parameters specify that the Callable should be executed after 5 seconds.
        ScheduledFuture scheduledFuture =
                scheduledExecutorService.schedule(new Runnable() {
                                                      public void run() {
                                                          System.out.println("Executed!");
                                                      }
                                                  },
                        5,
                        TimeUnit.SECONDS);

        try {
            System.out.println("result = " + scheduledFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
    }
}
