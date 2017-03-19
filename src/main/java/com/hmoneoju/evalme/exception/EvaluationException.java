package com.hmoneoju.evalme.exception;

public class EvaluationException extends EvalmeException {

    public static final int ERROR_CODE = 1001;

    public EvaluationException(String message) {
        super(message);
        setErrorCode(ERROR_CODE);
    }

    public EvaluationException(String message, Throwable e) {
        super(message, e);
        setErrorCode(ERROR_CODE);
    }

}
