package com.imt.services.joboffer.mapper;

import com.imt.services.joboffer.dto.CompanyDto;
import com.imt.services.joboffer.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDto toDto(Company entity);
    Company toEntity(CompanyDto dto);
}
