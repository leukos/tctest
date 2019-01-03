package com.tctest;

import com.mashape.unirest.http.JsonNode;
import freemarker.template.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
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

    public JsonNode buildJson() throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), "/");

        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = cfg.getTemplate("buildTemplate.ftl");
        StringWriter writer = new StringWriter();
        template.process(values, writer);

        JsonNode node = new JsonNode(writer.toString());
        writer.close();
        return node;
    }

    public static JsonRequestBuilder builder() {
        return new JsonRequestBuilder();
    }
}
