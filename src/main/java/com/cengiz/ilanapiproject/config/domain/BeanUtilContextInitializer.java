package com.cengiz.ilanapiproject.config.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Component
public class BeanUtilContextInitializer {

    private final ApplicationContext context;

    public BeanUtilContextInitializer(ApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    public void init() {
        BeanUtil.setApplicationContext(context);
    }
}
