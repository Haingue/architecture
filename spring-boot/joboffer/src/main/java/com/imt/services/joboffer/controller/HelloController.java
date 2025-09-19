package com.imt.services.joboffer.controller;

import com.imt.services.joboffer.dto.JobOfferDto;
import com.imt.services.joboffer.mapper.JobOfferMapper;
import com.imt.services.joboffer.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @GetMapping
    public String hello() {
        return "Hello world !";
    }

    @GetMapping(path = "/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<List<JobOfferDto>> stream() {
        return Flux
                .interval(Duration.ofSeconds(1))
                .take(50)
                .map((o) -> jobOfferRepository.findAll().stream()
                        .map(JobOfferMapper.INSTANCE::toDto)
                        .toList());
    }

}
