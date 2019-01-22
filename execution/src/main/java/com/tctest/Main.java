package com.tctest;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws UnirestException, URISyntaxException, IOException {

        String text = new String(Files.readAllBytes(Paths.get(Main.class.getResource("buildTemplate.ftl").toURI())));
        System.out.println(text);
        System.out.println(Unirest.post("http://localhost/app/rest/buildTypes")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .basicAuth("admin", "3MP56qcu")
                .body(JsonRequestBuilder.builder().buildId("Tctest4_Build").buildName("Build").projectId("Tctest4").projectName("Tctest4").buildJson())
                .asString().getBody());

    }

    @Test
    public void test() {

    }
}
