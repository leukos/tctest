package com.tctest;

import com.tctest.util.Util;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.tctest.util.Util.*;

/**
 * Says "Hi" to the user.
 */
@Mojo(name = "sayhi")
public class GreetingMojo extends AbstractMojo {

    @Parameter
    String testClass;
    @Parameter
    String testMethod;

    @Parameter(defaultValue = "localhost", required = true)
    String serverBaseUrl;

    @Parameter(required = true)
    String username;

    @Parameter(required = true)
    String password;

    public void execute() throws MojoExecutionException {
        Class clazz = null;
        try {
            getLog().info("Loading the class");
            clazz = getClass().getClassLoader().loadClass(testClass);
        } catch (ClassNotFoundException e) {
            throw new MojoExecutionException("error loading class " + e.getMessage(), e);
        }
        try {
            Method method = clazz.getMethod(testMethod);
            PropertyCombinationIterator iterator = new GCDPropertyCombinationIterator(getTemplateParameters(method));

            getLog().info("Creating a sub-project for the distributed series computation of " + testClass + ":" + testMethod);
            iterator.forEachRemaining(conf -> {
                getLog().info("Enqueuing execution " + iterator.getCurrentConfiguration() + " of " + iterator.getNumberOfPossibleConfigurations() +
                        " with configuration " + Util.configurationToString(conf));
            });
            getLog().info("Creating composite project for artifact consumption");
        } catch (NoSuchMethodException e) {
            throw new MojoExecutionException("error loading test " + e.getMessage(), e);
        }
        getLog().info("Hello, world.");
    }
}