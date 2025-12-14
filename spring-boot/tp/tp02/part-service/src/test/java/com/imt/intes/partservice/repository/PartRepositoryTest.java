package com.imt.intes.partservice.repository;

import com.imt.intes.partservice.dto.PartDto;
import com.imt.intes.partservice.entity.PartEntity;
import com.imt.intes.partservice.mapper.PartMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartRepositoryTest {

    private static final PartDto DEFAULT_PART = new PartDto(1L, "test", "00000", "description of the part");

    @Autowired
    private PartRepository partRepository;

    @Test
    void insertEntity () {
        PartEntity originalEntity = PartMapper.dtoToEntity(DEFAULT_PART);

        PartEntity savedEntity = partRepository.save(originalEntity);
        Assertions.assertEquals(originalEntity, savedEntity, "The object result from save method is different than the original part");
    }

    @Test
    void selectEntity () {
        PartEntity originalEntity = PartMapper.dtoToEntity(DEFAULT_PART);
        originalEntity = partRepository.save(originalEntity);

        Optional<PartEntity> savedEntity = partRepository.findById(originalEntity.getId());
        Assertions.assertTrue(savedEntity.isPresent());
        Assertions.assertEquals(originalEntity, savedEntity.get(), "The part found is not same than the original part");
    }

    @Test
    void updateEntity () {
        PartEntity originalEntity = PartMapper.dtoToEntity(DEFAULT_PART);
        originalEntity = partRepository.save(originalEntity);

        originalEntity.setName("updated name");
        originalEntity.setDescription("updated description");
        PartEntity updatedEntity = partRepository.save(originalEntity);
        Assertions.assertNotNull(updatedEntity);
        Assertions.assertEquals(originalEntity, updatedEntity, "The part found is not same than the updated part");
    }

    @Test
    void deleteEntity () {
        PartEntity originalEntity = PartMapper.dtoToEntity(DEFAULT_PART);
        originalEntity = partRepository.save(originalEntity);

        partRepository.deleteById(originalEntity.getId());
        Assertions.assertTrue(partRepository.findById(originalEntity.getId()).isEmpty(), "The deleted part still found");
    }

}