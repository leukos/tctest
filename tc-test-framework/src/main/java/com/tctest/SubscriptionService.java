package com.tctest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SubscriptionService {
    Map<String, List<Listener>> subscribers = new ConcurrentHashMap<>();

    Executor ex = Executors.newSingleThreadExecutor();

    public void subscribe(String key, Listener listener) {
        subscribers.compute(key, (k, l) -> {
            List<Listener> listeners = Optional.ofNullable(l).map(list -> new ArrayList<>(list)).orElseGet(() -> {
                ex.execute(() -> reallyExpensiveSubscribe(key));
                return new ArrayList<>();
            });
            listeners.add(listener);
            return listeners;
        });
        System.out.println(listener + " has been subscribed to " + key);
    }

    public void reallyExpensiveSubscribe(String key) {
        try {
            Thread.sleep(1000);
            System.out.println(key + "Subscribed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void reallyExpensiveUnsubscribe(String key) {
        try {
            Thread.sleep(1000);
            System.out.println(key + "Unsubscribed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unsubscribe(String key, Listener listener) {
        subscribers.computeIfPresent(key, (k, l) -> {
            List<Listener> listeners = new ArrayList<>(l);
            listeners.remove(listener);
            if (listeners.isEmpty()) {
                listeners = null;
                ex.execute(() -> reallyExpensiveUnsubscribe(key));
            }
            return listeners;
        });
    }

    public void fireAll(String key, String event) {
        subscribers.getOrDefault(key, Collections.emptyList()).forEach(l -> l.eventUpdate(event));
    }
}
