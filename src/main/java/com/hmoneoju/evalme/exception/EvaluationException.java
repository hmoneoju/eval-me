package com.hmoneoju.evalme.exception;

public class EvaluationException extends RuntimeException {

    public EvaluationException(String message) {
        super(message);
    }

    public EvaluationException(String message, Throwable e) {
        super(message, e);
    }

}
