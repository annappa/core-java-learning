package com.kscm.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureGetWithTimeout {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(new Callable(){
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        try {
            //The example above waits for a maximum of 1000 milliseconds for the result to be available in the Future.
            // If no result is available within 1000 milliseconds, a TimeoutException is thrown.
            Object result =
                    future.get(1000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        } catch (TimeoutException e) {
            // thrown if timeout time interval passes
            // before a result is available.
        }

        executorService.shutdown();
    }
}
