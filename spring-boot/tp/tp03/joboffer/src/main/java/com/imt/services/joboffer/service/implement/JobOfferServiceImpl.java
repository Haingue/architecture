package com.imt.services.joboffer.service.implement;

import com.imt.services.joboffer.dto.JobOfferDto;
import com.imt.services.joboffer.dto.wrapper.PaginatedResponseDto;
import com.imt.services.joboffer.entity.JobOffer;
import com.imt.services.joboffer.exceptions.BadRequest;
import com.imt.services.joboffer.exceptions.NotFound;
import com.imt.services.joboffer.mapper.CompanyMapper;
import com.imt.services.joboffer.mapper.JobOfferMapper;
import com.imt.services.joboffer.repository.CompanyRepository;
import com.imt.services.joboffer.repository.JobOfferRepository;
import com.imt.services.joboffer.service.JobOfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobOfferServiceImpl implements JobOfferService {
    private static final Logger logger = LoggerFactory.getLogger(JobOfferServiceImpl.class);
    private final JobOfferMapper mapper = JobOfferMapper.INSTANCE;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;
    private final JobOfferRepository jobOfferRepository;
    private final CompanyRepository companyRepository;

    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository, CompanyRepository companyRepository) {
        this.jobOfferRepository = jobOfferRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    @Tool(description = "Find one job offer by id")
    public JobOfferDto findOne(UUID id) {
        JobOffer result = jobOfferRepository.findById(id)
                .orElseThrow(NotFound::new);
        return mapper.toDto(result);
    }

    @Override
    @Tool(description = "Search all job offer by title (place SQL wildcards in the title), the first page result start at 0")
    public PaginatedResponseDto<JobOfferDto> search(String title, int page, int size) {
        Page<JobOffer> results = jobOfferRepository.searchAllByTitleLikeIgnoreCase(title, PageRequest.of(page, size));
        return mapper.toPaginatedDto(results);
    }

    @Override
    @Tool(description = "Publish a new job offer")
    public JobOfferDto publishNewJobOffer(JobOfferDto newJobOffer, String requestor) {
        // TODO check requestor
        if (newJobOffer.owner() == null) {
            throw new BadRequest("Owner is missing");
        } else if (!companyRepository.existsById(newJobOffer.owner().name())) {
            companyRepository.save(companyMapper.toEntity(newJobOffer.owner()));
        }
        JobOffer jobOffer = mapper.toEntity(newJobOffer);
        jobOffer.setId(UUID.randomUUID());
        JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
        return mapper.toDto(savedJobOffer);
    }

    @Override
    @Tool(description = "Update an existing job offer")
    public JobOfferDto updateJobOffer(JobOfferDto jobOfferDto, String requestor) {
        // TODO check requestor
        JobOffer existingJobOffer = jobOfferRepository.findById(jobOfferDto.id())
                .orElseThrow(NotFound::new);
        existingJobOffer.setTitle(jobOfferDto.title());
        existingJobOffer.setDescription(jobOfferDto.description());
        existingJobOffer.setStartDate(jobOfferDto.startDate());
        existingJobOffer.setStartDayTime(jobOfferDto.startDayTime());
        existingJobOffer.setEndDayTime(jobOfferDto.endDayTime());
        existingJobOffer.setEndDate(jobOfferDto.endDate());
        existingJobOffer.setCreationTimestamp(jobOfferDto.creationTimestamp());
        existingJobOffer.setExpirationDays(jobOfferDto.expirationDays());
        existingJobOffer.setStudentId(jobOfferDto.studentId());

        existingJobOffer = jobOfferRepository.save(existingJobOffer);
        return mapper.toDto(existingJobOffer);
    }

    @Override
    @Tool(description = "Cancel an existing job offer")
    public void cancelJobOffer(UUID jobOfferId, String requestor) {
        // TODO check requestor
        jobOfferRepository.deleteById(jobOfferId);
    }
}
