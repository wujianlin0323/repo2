package com.my.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebInterceptor {
    @Resource
    private Environment env;
    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars = new HashMap();
            vars.put("picUrl", env.getProperty("picUrl"));
            vars.put("baseUrl", env.getProperty("baseUrl"));
            viewResolver.setStaticVariables(vars);
        }
    }
}
