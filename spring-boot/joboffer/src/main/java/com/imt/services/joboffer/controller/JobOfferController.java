package com.imt.services.joboffer.controller;

import com.imt.services.joboffer.dto.JobOfferDto;
import com.imt.services.joboffer.dto.wrapper.PaginatedResponseDto;
import com.imt.services.joboffer.service.JobOfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/service/job-offer")
public class JobOfferController {

    private static final Logger logger = LoggerFactory.getLogger(JobOfferController.class);

    @Autowired
    private JobOfferService jobOfferService;

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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobOfferService.publishNewJobOffer(jobOffer, authentication.getName()));
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
