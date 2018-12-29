package com.tctest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ParameterExtension.class)
public class MainTest {

    @TemplateParams(value = {
            @TemplateParam(name = "test1", values = {"1", "2"}),
            @TemplateParam(name = "test2", values = {"3", "4"})
    })
    @Test
    public void test() {
        Assertions.assertTrue(true);
    }


}
