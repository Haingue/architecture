package com.imt.services.joboffer.configuration;

import com.imt.services.joboffer.service.JobOfferService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MainConfiguration {

    @Bean
    public List<ToolCallback> findTools(JobOfferService jobOfferService) {
        return List.of(ToolCallbacks.from(jobOfferService));
    }

}
