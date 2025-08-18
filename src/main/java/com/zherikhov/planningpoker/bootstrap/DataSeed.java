package com.zherikhov.planningpoker.bootstrap;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeed {

    @Bean
    public ApplicationRunner dataInitializer() {
        return args -> {
            // Initialize database with sample data here.
        };
    }
}
