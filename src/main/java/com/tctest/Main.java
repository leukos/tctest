package com.tctest;

import com.mashape.unirest.http.Unirest;

public class Main {

    public static void main(String[] args) {
        Unirest.post("https://teamcity.jetbrains.com:80");
    }
}
