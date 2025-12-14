package com.imt.services.joboffer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imt.services.joboffer.entity.Company;
import com.imt.services.joboffer.entity.JobOffer;
import com.imt.services.joboffer.repository.CompanyRepository;
import com.imt.services.joboffer.repository.JobOfferRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class JobOfferControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @BeforeAll
    static void setUp(@Autowired JobOfferRepository jobOfferRepository, @Autowired CompanyRepository companyRepository) {
        jobOfferRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "user")
    void shouldPublishNewOffer() throws Exception {
        HashMap<String, Object> company = new HashMap<>();
        company.put("name", "Company 1");
        company.put("address", "10 rue de Java");

        HashMap<String, Object> newOffer = new HashMap<>();
        newOffer.put("title", "New offer");
        newOffer.put("description", "This is a new offer");
        newOffer.put("startDayTime", "08:00:00");
        newOffer.put("endDayTime", "16:00:00");
        newOffer.put("startTime", "2025-06-01");
        newOffer.put("endTime", "2025-01-30");
        newOffer.put("creationTimestamp", "2025-03-01T12:00:00Z");
        newOffer.put("expirationDays", 90);

        newOffer.put("owner", company);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(newOffer);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/service/job-offer")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(newOffer.get("title")));

        Page<JobOffer> savedEntities = this.jobOfferRepository.searchAllByTitleLikeIgnoreCase(newOffer.get("title").toString(), Pageable.ofSize(10));
        assertFalse(savedEntities.isEmpty());
        Optional<Company> savedCompany = companyRepository.findById(company.get("name").toString());
        assertTrue(savedCompany.isPresent());
    }

    @Test
    @WithMockUser(roles = "user")
    void shouldFindOffer() throws Exception {
        shouldPublishNewOffer();
        mockMvc.perform(MockMvcRequestBuilders.get("/service/job-offer").param("title", "New offer"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "user")
    void shouldUpdateOffer() throws Exception {
        shouldPublishNewOffer();
        UUID previousId = this.jobOfferRepository.searchAllByTitleLikeIgnoreCase("New offer", Pageable.ofSize(10)).getContent()
                .stream().map(JobOffer::getId)
                .findFirst().get();
        HashMap<String, Object> company = new HashMap<>();
        company.put("name", "Company 1");
        company.put("address", "10 rue de Java");

        HashMap<String, Object> jobOffer = new HashMap<>();
        jobOffer.put("id", previousId);
        jobOffer.put("title", "This is a real offer");
        jobOffer.put("description", "This is a new offer");
        jobOffer.put("startDayTime", "08:00:00");
        jobOffer.put("endDayTime", "16:00:00");
        jobOffer.put("startTime", "2025-06-01");
        jobOffer.put("endTime", "2025-01-30");
        jobOffer.put("creationTimestamp", "2025-03-01T12:00:00Z");
        jobOffer.put("expirationDays", 90);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/service/job-offer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(jobOffer))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(previousId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(jobOffer.get("title")));
    }

    @Test
    @WithMockUser(roles = {"user", "admin"})
    void shouldDeleteOffer() throws Exception {
        shouldPublishNewOffer();
        JobOffer jobOffer = this.jobOfferRepository.searchAllByTitleLikeIgnoreCase("%", Pageable.ofSize(10)).getContent().getFirst();
        jobOffer.setTitle("This is a real offer");

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/service/job-offer/"+jobOffer.getId())
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Optional<JobOffer> deletedEntity = this.jobOfferRepository.findById(jobOffer.getId());
        assertFalse(deletedEntity.isPresent());
    }

}