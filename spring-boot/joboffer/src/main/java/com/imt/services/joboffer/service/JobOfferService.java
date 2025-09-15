package com.imt.services.joboffer.service;

import com.imt.services.joboffer.dto.JobOfferDto;
import com.imt.services.joboffer.dto.wrapper.PaginatedResponseDto;
import org.springframework.ai.tool.annotation.Tool;

import java.util.UUID;

public interface JobOfferService {

    JobOfferDto findOne(UUID id);
    PaginatedResponseDto<JobOfferDto> search (String title, int page, int size);

    JobOfferDto publishNewJobOffer(JobOfferDto newJobOffer, String requestor);
    JobOfferDto updateJobOffer(JobOfferDto jobOffer, String requestor);
    void cancelJobOffer(UUID jobOfferId, String requestor);

}
