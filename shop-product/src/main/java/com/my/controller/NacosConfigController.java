package com.my.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RefreshScope
public class NacosConfigController {
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Value("${config.appName}")
    private String appName;
    @Value("${config.env}")
    private String env;
    @RequestMapping("/test-config")
    public String testConfig(){
        return applicationContext.getEnvironment().getProperty("config.appName");
    }
    @RequestMapping("/test-config2")
    public String testConfig2(){
        return appName;
    }

    @RequestMapping("/test-config3")
    public String testConfig3(){
        return env;
    }
}
