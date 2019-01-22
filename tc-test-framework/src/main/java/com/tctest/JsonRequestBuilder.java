package com.tctest;

import com.mashape.unirest.http.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class JsonRequestBuilder {
    private final Map<String, Object> values = new HashMap<>();

    public JsonRequestBuilder projectId(String projectId) {
        values.putIfAbsent("projectId", projectId);
        return this;
    }

    public JsonRequestBuilder projectName(String projectName) {
        values.putIfAbsent("projectName", projectName);
        return this;
    }

    public JsonRequestBuilder buildId(String buildId) {
        values.putIfAbsent("buildId", buildId);
        return this;
    }

    public JsonRequestBuilder buildName(String buildName) {
        values.putIfAbsent("buildName", buildName);
        return this;
    }

    public JsonNode buildJson() {
        JsonNode node = new JsonNode("");
        return node;
    }

    public static JsonRequestBuilder builder() {
        return new JsonRequestBuilder();
    }
}