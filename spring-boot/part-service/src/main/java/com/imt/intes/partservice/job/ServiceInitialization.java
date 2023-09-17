package com.imt.intes.partservice.job;

import com.imt.intes.partservice.dto.PartDto;
import com.imt.intes.partservice.mapper.PartMapper;
import com.imt.intes.partservice.repository.PartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ServiceInitialization implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("[ENV] admin.name: {}", environment.getProperty("spring.security.user.name"));
        LOGGER.info("[ENV] admin.password: {}", environment.getProperty("spring.security.user.password"));
        LOGGER.info("[ENV] datasource.url: {}", environment.getProperty("spring.datasource.url"));
        LOGGER.info("[ENV] datasource.username: {}", environment.getProperty("spring.datasource.username"));
        LOGGER.info("[ENV] datasource.password: {}", environment.getProperty("spring.datasource.password"));
    }
}
