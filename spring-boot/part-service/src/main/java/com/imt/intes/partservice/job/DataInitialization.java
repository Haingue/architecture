package com.imt.intes.partservice.job;

import com.imt.intes.partservice.dto.PartDto;
import com.imt.intes.partservice.mapper.PartMapper;
import com.imt.intes.partservice.repository.PartRepository;
import com.imt.intes.partservice.service.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataInitialization implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PartRepository partRepository;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("[RUNNER] Data initialization");
        partRepository.findById(1L)
                .ifPresentOrElse(entity -> {}, () -> {
                    PartDto rightDoorPart = new PartDto(1L, "Right-Door", "10780", "Panel for the right door");
                    partRepository.save(PartMapper.dtoToEntity(rightDoorPart));
                });

        partRepository.findById(2L)
                .ifPresentOrElse(entity -> {}, () -> {
                    PartDto leftDoorPart = new PartDto(2L, "Left-Door", "10780", "Panel for the left door");
                    partRepository.save(PartMapper.dtoToEntity(leftDoorPart));
                });
        LOGGER.info("Data initialized");
    }

    @Scheduled(cron = "0 5 * * * *")
    public void purgeData () {
        LOGGER.info("[JOB] Daily purge");
    }
}
