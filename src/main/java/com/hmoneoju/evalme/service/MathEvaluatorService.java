package com.hmoneoju.evalme.service;

import com.hmoneoju.evalme.exception.EvaluationException;

public interface MathEvaluatorService {
    String eval(String expression) throws EvaluationException;
}
