package com.hmoneoju.evalme.exception;

public class EvalmeException extends RuntimeException {

    public static final int ERROR_CODE = 1001;

    private int errorCode;

    public EvalmeException(String message) {
        super(message);
        setErrorCode(ERROR_CODE);
    }

    public EvalmeException(String message, Throwable e) {
        super(message, e);
        setErrorCode(ERROR_CODE);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
