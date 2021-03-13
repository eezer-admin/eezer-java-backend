/*
package org.eezer.service.config;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@PropertySource("classpath:" + UnitTestServiceConfig.UNIT_TEST_SERVICE_PROPERTIES_FILE)
@ComponentScan({ ServiceConfig.SERVICE_BASE_PACKAGE })
public class UnitTestServiceConfig extends AbstractMongoConfiguration {

    */
/**
     * Service properties filename.
     *//*

    public static final String UNIT_TEST_SERVICE_PROPERTIES_FILE = "eezer-backend-service.properties";

    @Autowired
    private Environment env;

    @Override
    public MongoClient mongoClient() {
        return new Fongo(getDatabaseName()).getMongo();
    }

    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("mongo.db.name");
    }

}
*/
