package org.eezer.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Service configuration class that scans this service for Spring beans.
 */
@Configuration
@PropertySource("classpath:" + ServiceConfig.SERVICE_PROPERTIES_FILE)
@EnableMongoRepositories(basePackages = ServiceConfig.MONGODB_REPOSITORIES_PACKAGE)
public class ServiceConfig {

    /**
     * The base package of this service.
     */
    public static final String SERVICE_BASE_PACKAGE = "org.eezer.service";

    /**
     * The base package of mongodb repositories.
     */
    public static final String MONGODB_REPOSITORIES_PACKAGE = "org.eezer.service.domain.repository";

    /**
     * Service properties filename.
     */
    public static final String SERVICE_PROPERTIES_FILE = "eezer-backend-service.properties";

    /**
     * Application context path.
     */
    public static final String CONTEXT_PATH = "/api";

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Create the BCryptPasswordEncoder bean.
     *
     * @return the bean
     */
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
