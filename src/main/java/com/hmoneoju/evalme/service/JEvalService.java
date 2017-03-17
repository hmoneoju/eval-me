package com.hmoneoju.evalme.service;

import com.hmoneoju.evalme.exception.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import org.springframework.stereotype.Component;

@Component("jeval")
public class JEvalService implements MathEvaluatorService {

    @Override
    public String eval(String expression) throws EvaluationException {
        Evaluator evaluator = new Evaluator();
        try {
            return evaluator.evaluate(expression);
        } catch (net.sourceforge.jeval.EvaluationException e) {
            throw new EvaluationException("Error evaluating expression " + expression, e);
        }
    }

}
