package com.hmoneoju.evalme.configuration;

import com.hmoneoju.evalme.service.MathEvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EvalmeConfiguration {

    @Autowired
    private ApplicationContext ctx;

    @Bean
    public MathEvaluatorService mathEvaluatorService(@Value("${evalme.math.engine}") String engine) {
        return (MathEvaluatorService) ctx.getBean(engine);
    }

}
