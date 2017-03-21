package com.hmoneoju.evalme.resource;

import com.hmoneoju.evalme.exception.EvalmeException;
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
        OperationError operationError = buildOperationError(e);
        return operationError;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody OperationError handleUncaughtException(Throwable e) {
        EvalmeException evalmeException = new EvalmeException("Unknown error", e);
        OperationError operationError = buildOperationError(evalmeException);
        return operationError;
    }

    private OperationError buildOperationError(EvalmeException e) {
        logger.error("Error thrown", e);

        OperationError operationError = new OperationError();
        operationError.setErrorCode(e.getErrorCode());
        operationError.setMessage(e.getMessage());
        return operationError;
    }

}
