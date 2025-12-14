package com.imt.intes.partservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imt.intes.partservice.dto.PartDto;
import com.imt.intes.partservice.entity.PartEntity;
import com.imt.intes.partservice.mapper.PartMapper;
import com.imt.intes.partservice.repository.PartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PartRestControllerTest {

    private static final PartDto DEFAULT_PART = new PartDto(1L, "test", "00000", "description of the part");

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PartRepository partRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void getPart() throws Exception {
        mvc.perform(get("/service/part"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void getExistingPart() throws Exception {
        partRepository.save(PartMapper.dtoToEntity(DEFAULT_PART));

        MvcResult result = mvc.perform(get("/service/part"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        List<PartDto> partList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<PartDto>>(){});
        Assertions.assertNotNull(partList, "The result is null");
        Assertions.assertFalse(partList.isEmpty(), "The result list is empty");
    }

    @Test
    void postPart() throws Exception {
        partRepository.deleteById(DEFAULT_PART.id());

        mvc.perform(post("/service/part")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(DEFAULT_PART)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Assertions.assertTrue(partRepository.findById(DEFAULT_PART.id()).isPresent(), "The part is not created");
    }

    @Test
    void putPart() throws Exception {
        PartEntity originalPart = partRepository.save(PartMapper.dtoToEntity(DEFAULT_PART));

        originalPart.setName("updated name");
        originalPart.setDescription("updated description");
        MvcResult result = mvc.perform(put("/service/part")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(originalPart)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        PartDto resultDto = mapper.readValue(result.getResponse().getContentAsString(), PartDto.class);
        Assertions.assertEquals(resultDto, PartMapper.entityToDto(originalPart));
    }

    @Test
    void deletePart() throws Exception {
        PartEntity originalPart = partRepository.save(PartMapper.dtoToEntity(DEFAULT_PART));

        mvc.perform(delete("/service/part/"+1L)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                        .content(mapper.writeValueAsString(originalPart.getId())))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}