package com.imt.services.joboffer.repository;

import com.imt.services.joboffer.entity.JobOffer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobOfferRepositoryTest {

    private final JobOffer defaultJobOffer;
    private final JobOfferRepository jobOfferRepository;

    @Autowired
    public JobOfferRepositoryTest(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(UUID.randomUUID());
        jobOffer.setTitle("Job Offer test");
        jobOffer.setStartDate(LocalDate.MIN);
        jobOffer.setEndDate(LocalDate.MAX);
        jobOffer.setStartDayTime(LocalTime.MIN);
        jobOffer.setEndDayTime(LocalTime.MAX);
        jobOffer.setExpirationDays(10);
        jobOffer.setCreationTimestamp(Instant.MIN);

        this.defaultJobOffer = jobOffer;
    }

    @BeforeAll
    static void setUp(@Autowired JobOfferRepository jobOfferRepository) {
        jobOfferRepository.deleteAll();
    }

    @Test
    void shouldInsertJobOffer() {
        JobOffer savedEntity = this.jobOfferRepository.save(defaultJobOffer);
        assertNotNull(savedEntity);
        assertEquals(defaultJobOffer, savedEntity);
    }

    @Test
    void shouldFindJobOfferByTitle() {
        // Prepare data
        this.jobOfferRepository.deleteAll();
        this.jobOfferRepository.save(defaultJobOffer);

        List<JobOffer> results = this.jobOfferRepository.findAllByTitleLikeOrderByCreationTimestampDesc("Job Offer %");
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(defaultJobOffer.getTitle(), results.get(0).getTitle());
    }

    @Test
    void shouldUpdateJobOffer() {
        // Prepare data
        this.jobOfferRepository.deleteAll();
        JobOffer modifiedJobOffer = this.jobOfferRepository.save(defaultJobOffer);
        modifiedJobOffer.setTitle("Job Offer test (bis)");
        modifiedJobOffer.setStartDate(modifiedJobOffer.getStartDate().plusDays(1));
        modifiedJobOffer.setEndDate(modifiedJobOffer.getEndDate().minusDays(1));
        modifiedJobOffer = this.jobOfferRepository.save(modifiedJobOffer);

        Optional<JobOffer> savedJobOffer = this.jobOfferRepository.findById(defaultJobOffer.getId());
        assertTrue(savedJobOffer.isPresent());
        assertEquals(modifiedJobOffer.getTitle(), savedJobOffer.get().getTitle());
        assertNotEquals(defaultJobOffer.getTitle(), savedJobOffer.get().getTitle());
    }

    @Test
    void shoudDeleteJobOffer() {
        // Prepare data
        this.jobOfferRepository.deleteAll();
        this.jobOfferRepository.save(defaultJobOffer);

        this.jobOfferRepository.deleteById(defaultJobOffer.getId());
        List<JobOffer> all = this.jobOfferRepository.findAll();
        assertTrue(all.isEmpty());

    }
}