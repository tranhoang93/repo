package com.thanh.boot;

import com.thanh.boot.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {ApplicationProperties.class})
public class BootTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootTemplateApplication.class, args);
    }

}
