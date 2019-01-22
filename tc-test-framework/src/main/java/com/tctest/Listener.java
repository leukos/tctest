package com.tctest;

public class Listener {
    private final String name;

    public Listener(String name) {
        this.name = name;
    }

    public void eventUpdate(String event) {
    }

    @Override
    public String toString() {
        return name;
    }
}