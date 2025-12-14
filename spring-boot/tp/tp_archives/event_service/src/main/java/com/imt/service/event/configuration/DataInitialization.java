package com.imt.service.event.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitialization implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitialization.class);

    @Override
    public void run(String... args) throws Exception {
        LOGGER.debug("Data initialized");
    }

}
