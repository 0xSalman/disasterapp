package com.safien.code2015.disasterapp;

import com.safien.code2015.disasterapp.config.CloudInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by salman on 2014-11-15.
 */
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan
public class Application {

    public static void main(String[] args) {

        new SpringApplicationBuilder(Application.class)
                .initializers(new CloudInitializer())
                .run(args);
    }
}
