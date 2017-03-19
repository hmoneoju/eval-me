package com.hmoneoju.evalme.exception;

public class EvalmeException extends RuntimeException {
    private int errorCode;

    public EvalmeException(String message) {
        super(message);
    }

    public EvalmeException(String message, Throwable e) {
        super(message, e);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
