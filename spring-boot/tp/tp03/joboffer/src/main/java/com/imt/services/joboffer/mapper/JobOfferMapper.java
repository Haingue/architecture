package com.imt.services.joboffer.mapper;

import com.imt.services.joboffer.dto.JobOfferDto;
import com.imt.services.joboffer.dto.wrapper.PaginatedResponseDto;
import com.imt.services.joboffer.entity.JobOffer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface JobOfferMapper {
    JobOfferMapper INSTANCE = Mappers.getMapper(JobOfferMapper.class);

    JobOfferDto toDto(JobOffer entity);
    JobOffer toEntity(JobOfferDto dto);

    default PaginatedResponseDto<JobOfferDto> toPaginatedDto(Page<JobOffer> page) {
        List<JobOfferDto> content = page.getContent().stream()
                .map(this::toDto)
                .toList();
        return new PaginatedResponseDto<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
