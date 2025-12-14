package com.imt.intes.partservice.service;

import com.imt.intes.partservice.dto.PartDto;
import com.imt.intes.partservice.entity.PartEntity;
import com.imt.intes.partservice.exception.IdUsedException;
import com.imt.intes.partservice.exception.NoContentException;
import com.imt.intes.partservice.exception.PartNotFoundException;
import com.imt.intes.partservice.exception.PartNotValidException;
import com.imt.intes.partservice.mapper.PartMapper;
import com.imt.intes.partservice.repository.PartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PartRepository partRepository;

    public PartDto createPart (PartDto dto) {
        if (partRepository.findById(dto.id()).isPresent()) throw new IdUsedException();
        if (dto.supplierCode() == null || dto.supplierCode().isBlank()) throw new PartNotValidException();

        PartEntity savedPart = partRepository.save(PartMapper.dtoToEntity(dto));
        return PartMapper.entityToDto(savedPart);
    }
    public PartDto updatePart (PartDto dto) {
        if (dto.supplierCode() == null || dto.supplierCode().isBlank()) throw new PartNotValidException();
        PartEntity existingPart = partRepository.findById(dto.id())
                .orElseThrow(PartNotFoundException::new);
        existingPart.setName(dto.name());
        existingPart.setSupplierCode(dto.supplierCode());
        existingPart.setDescription(dto.description());
        existingPart = partRepository.save(existingPart);
        return PartMapper.entityToDto(existingPart);
    }
    public void deletePart (Long id) {
        partRepository.findById(id)
                .orElseThrow(PartNotFoundException::new);
        partRepository.deleteById(id);
    }

    public List<PartDto> loadAllParts () {
        List<PartDto> result = new ArrayList<>();
        partRepository.findAll().forEach(partEntity -> {
            result.add(PartMapper.entityToDto(partEntity));
        });
        return result;
    }
    public PartDto loadOneParts (Long id) {
        return partRepository.findById(id)
                .map(PartMapper::entityToDto)
                .orElseThrow(PartNotFoundException::new);
    }
    public List<PartDto> loadAllPartsBySupplierCode (String supplierCode) {
        List<PartEntity> result = partRepository.findAllBySupplierCodeOrderById(supplierCode);
        if (result.isEmpty()) throw new NoContentException();
        return result.stream().map(PartMapper::entityToDto).toList();
    }
}
