package com.ead.repository;

import com.ead.model.Summary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SummaryRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("When valid POST request hit endpoint then a new record should exist in database")
    void whenPostASummaryThenTheNewObjectIsCreatedInDataBase() throws Exception {

        final String expectedId = UUID.randomUUID().toString();
        final String expectedCountryCode = "br";
        final String expectedStatus = "confirmed";
        final Long expectedCasesNumber = new Random().nextLong();
        final OffsetDateTime expectedCreatedAt = OffsetDateTime.now(UTC);

        Summary newSummary = Summary.builder()
                .id(expectedId)
                .countryCode(expectedCountryCode)
                .status(expectedStatus)
                .casesNumber(expectedCasesNumber)
                .createdAt(expectedCreatedAt)
                .build();

        mockMvc.perform(post("/summary")
                .content(objectMapper.writeValueAsString(newSummary))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.country_code").exists())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.cases_number").exists())
                .andExpect(jsonPath("$.created_at").exists());

        Summary summary = summaryRepository.findById(expectedId).orElseThrow();

        assertNotNull(summary);
        assertEquals(summary.getId(), expectedId);
        assertEquals(summary.getStatus(), expectedStatus);
        assertEquals(summary.getCreatedAt().format(ISO_LOCAL_DATE), expectedCreatedAt.format(ISO_LOCAL_DATE));
        assertEquals(summary.getCasesNumber(), expectedCasesNumber);

    }
}