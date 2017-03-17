package com.hmoneoju.evalme.service;

import com.hmoneoju.evalme.exception.EvaluationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JEvalServiceTest {

    @Autowired
    @Qualifier("jeval")
    JEvalService jEvalService;

    @Test
    public void validOperations() {
        assertEquals("4.0", jEvalService.eval("2+2"));
        assertEquals("37.0", jEvalService.eval("(2+3)*5+12"));
        assertEquals("-3.0",jEvalService.eval("-4+1"));
    }

    @Test()
    public void invalidOperation() {
        assertInvalidExpression("(2+4*1))");
        assertInvalidExpression("2+4*1) asasas )");
    }

    private void assertInvalidExpression(String expression) {
        try {
            jEvalService.eval(expression);
            fail();
        } catch (EvaluationException e) {
        }
    }

}
