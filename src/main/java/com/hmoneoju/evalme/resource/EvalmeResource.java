package com.hmoneoju.evalme.resource;

import com.hmoneoju.evalme.model.Operation;
import com.hmoneoju.evalme.service.MathEvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EvalmeResource {

    @Autowired
    private MathEvaluatorService mathEvaluatorService;

    @RequestMapping(value="/eval", method = RequestMethod.POST)
    @ResponseBody
    public Operation eval(@RequestParam String expression) {
        String evalResult = mathEvaluatorService.eval(expression);
        Operation result = new Operation();
        result.setExpression(expression);
        result.setResult(evalResult);
        return result;
    }

}
