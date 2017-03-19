package com.hmoneoju.evalme.resource;

import com.hmoneoju.evalme.exception.EvaluationException;
import com.hmoneoju.evalme.model.OperationError;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceExceptionHandler {

    private static Logger logger = Logger.getLogger(ResourceExceptionHandler.class);

    @ExceptionHandler(EvaluationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody OperationError handleInvalidExpression(EvaluationException e) {

        logger.error(e);

        OperationError operationError = new OperationError();
        operationError.setErrorCode(e.getErrorCode());
        operationError.setMessage(e.getMessage());

        return operationError;
    }

}
