package org.eezer.service.bootstrap;

import org.eezer.service.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = ServiceConfig.SERVICE_BASE_PACKAGE)
public class Application {

    private static final String CONTEXT_PATH_PROPERTY_NAME = "server.servlet.context-path";

    public static void main(String[] args) {
        System.setProperty(CONTEXT_PATH_PROPERTY_NAME, ServiceConfig.CONTEXT_PATH);
        SpringApplication.run(Application.class, args);
    }

}
