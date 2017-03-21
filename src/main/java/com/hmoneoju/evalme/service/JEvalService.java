package com.hmoneoju.evalme.service;

import com.hmoneoju.evalme.exception.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("jeval")
public class JEvalService implements MathEvaluatorService {

    private static final Logger logger = LoggerFactory.getLogger(JEvalService.class);

    @Override
    public String eval(String expression) throws EvaluationException {
        Evaluator evaluator = new Evaluator();
        try {
            String result = evaluator.evaluate(expression);
            logger.info("{} = {}", expression, result);
            return result;
        } catch (net.sourceforge.jeval.EvaluationException e) {
            throw new EvaluationException("["+expression +"] is not a valid expression", e);
        }
    }

}
