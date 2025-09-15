package com.imt.services.joboffer.controller;

import com.imt.services.joboffer.dto.JobOfferDto;
import com.imt.services.joboffer.dto.wrapper.PaginatedResponseDto;
import com.imt.services.joboffer.service.JobOfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@RestController
@RequestMapping("/service/job-offer")
public class JobOfferController {

    private static final Logger logger = LoggerFactory.getLogger(JobOfferController.class);

    private final Sinks.Many<ServerSentEvent<JobOfferDto>> notificationSink = Sinks.many().multicast().directBestEffort();

    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping(path = "/subscribe", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ServerSentEvent<JobOfferDto>> subscribe() {
        return Flux.create(sink -> {
            sink.next(ServerSentEvent.<JobOfferDto>builder()
                    .id(UUID.randomUUID().toString())
                    .event("open")
                    .build());
            Disposable disposable = notificationSink.asFlux().subscribe(sink::next);
            sink.onCancel(disposable);
        });
    }

    @GetMapping("/{jobOfferId}")
    public ResponseEntity<JobOfferDto> findOneJobOffer(@PathVariable UUID jobOfferId) {
        return ResponseEntity.ok(jobOfferService.findOne(jobOfferId));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<JobOfferDto>> searchJobOffers(
            @RequestParam(required = false, name = "title", defaultValue = "*") String title,
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size
    ) {
        logger.info("Searching job offers for title {}", title);
        title = title.replace("*", "%");
        PaginatedResponseDto<JobOfferDto> response = jobOfferService.search(title, page, size);
        if (response.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<JobOfferDto> publishJobOffer(@RequestBody JobOfferDto jobOffer, Authentication authentication) {
        logger.info("Publishing job offer: {}", jobOffer);
        JobOfferDto resultJobOffer = jobOfferService.publishNewJobOffer(jobOffer, authentication.getName());

        notificationSink.tryEmitNext(ServerSentEvent.<JobOfferDto>builder()
                .data(resultJobOffer)
                .build());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resultJobOffer);
    }

    @PutMapping
    public ResponseEntity<JobOfferDto> updateJobOffer(@RequestBody JobOfferDto jobOffer, Authentication authentication) {
        logger.info("Update job offer [id={}] by {}", jobOffer, authentication.getName());
        return ResponseEntity.ok(jobOfferService.updateJobOffer(jobOffer, authentication.getName()));
    }

    @DeleteMapping("/{jobOfferId}")
    public ResponseEntity<Void> cancelJobOffer(@PathVariable UUID jobOfferId, Authentication authentication) {
        logger.info("Delete job offer [id={}] by {}", jobOfferId, authentication.getName());
        jobOfferService.cancelJobOffer(jobOfferId, authentication.getName());
        return ResponseEntity.ok().build();
    }
}
