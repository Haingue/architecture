package com.imt.intes.partservice.controller;

import com.imt.intes.partservice.dto.PartDto;
import com.imt.intes.partservice.exception.PartServiceException;
import com.imt.intes.partservice.service.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/part")
public class PartRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PartService partService;

    @GetMapping
    public ResponseEntity<?> getParts () {
        LOGGER.info("Retrieve parts");
        try {
            return ResponseEntity.ok(partService.loadAllParts());
        } catch (PartServiceException error) {
            return ResponseEntity.badRequest().body(error);
        } catch (Exception systemError) {
            return ResponseEntity.internalServerError().body(systemError);
        }
    }

    @PostMapping
    public ResponseEntity<?> postPart (@RequestBody PartDto dto) {
        LOGGER.info("create part: {}", dto);
        if (dto == null) return ResponseEntity.badRequest().build();

        try {
            dto = partService.createPart(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (PartServiceException error) {
            return ResponseEntity.badRequest().body(error);
        } catch (Exception systemError) {
            return ResponseEntity.internalServerError().body(systemError);
        }
    }

    @PutMapping
    public ResponseEntity<?> putPart (@RequestBody PartDto dto) {
        LOGGER.info("update part: {}", dto);
        if (dto == null) return ResponseEntity.badRequest().build();

        try {
            dto = partService.updatePart(dto);
            return ResponseEntity.ok(dto);
        } catch (PartServiceException error) {
            return ResponseEntity.badRequest().body(error);
        } catch (Exception systemError) {
            return ResponseEntity.internalServerError().body(systemError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePart (@PathVariable Long id) {
        LOGGER.info("Remove part: {}", id);
        if (id == null) return ResponseEntity.badRequest().body("Id must not be null");

        try {
            partService.deletePart(id);
            return ResponseEntity.ok().build();
        } catch (PartServiceException error) {
            return ResponseEntity.badRequest().body(error);
        } catch (Exception systemError) {
            return ResponseEntity.internalServerError().body(systemError);
        }
    }

}
