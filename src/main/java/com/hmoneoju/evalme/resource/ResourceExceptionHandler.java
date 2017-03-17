package com.hmoneoju.evalme.resource;

import com.hmoneoju.evalme.exception.EvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EvaluationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody Object handleInvalidExpression(EvaluationException e) {
        return e.getMessage();
    }

}
