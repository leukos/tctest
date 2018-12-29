package com.tctest;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws UnirestException, URISyntaxException, IOException {

        String text = new String(Files.readAllBytes(Paths.get(Main.class.getResource("buildTemplate.json").toURI())));
        System.out.println(text);
        JsonNode json = new JsonNode(text);
        System.out.println(Unirest.get("https://teamcity.jetbrains.com/app/rest/buildTypes")
                .header("Accept", "application/json")

                .basicAuth("sebostrass", "3MP56qcu")
                .asString().getBody());
    }
}
