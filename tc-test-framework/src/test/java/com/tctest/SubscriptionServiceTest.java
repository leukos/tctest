package com.tctest;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SubscriptionServiceTest {

    @Test
    public void testScenario() throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        SubscriptionService ss = new SubscriptionService();
        for (int i = 1; i <= 100; i++) {
            final Listener l = new Listener("Listener " + i);
            ex.submit(() -> ss.subscribe("reuters", l));
        }
        ex.shutdown();
        try {
            ex.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {

        }
        System.out.println(ss);
    }

}
