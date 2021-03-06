package com.hmoneoju.evalme.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OperationError {

    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
