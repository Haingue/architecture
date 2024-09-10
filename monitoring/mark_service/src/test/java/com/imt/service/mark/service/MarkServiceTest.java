package com.imt.service.mark.service;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.imt.service.mark.constant.MarkExemples;
import com.imt.service.mark.dto.MarkDto;
import com.imt.service.mark.repository.MarkRepository;

@SpringBootTest
public class MarkServiceTest {

    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private MarkService markService;

    @BeforeEach
    void prepareTest () {
        markRepository.deleteAll();
    }

    @Test
    public void shouldBeCreated () {
        MarkDto originalDto = MarkExemples.Mark_1.toDto();
        MarkDto newDto = markService.evaluateEvent(originalDto);

        Assertions.assertNotNull(newDto);
        Assertions.assertNotNull(newDto.id());
        Assertions.assertNotNull(newDto.creationDatetime());

        Assertions.assertEquals(originalDto.markValue(), newDto.markValue());
        Assertions.assertEquals(originalDto.participant(), newDto.participant());
        Assertions.assertEquals(originalDto.event(), newDto.event());
    }
}
