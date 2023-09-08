package org.crustproject.entrypoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EntityScan(basePackages = {"org.crustproject.**"})
@ComponentScan(basePackages = "org.crustproject.**")
public class EntryPointConfig {
    public static void main(String[] args) {
        SpringApplication.run(EntryPointConfig.class, args);
    }
}
