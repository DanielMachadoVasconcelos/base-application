package com.ead;

import com.ead.repository.SummaryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BaseApplicationTests {

    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("When application loads then all beans should be available")
    void contextLoads() {

        assertNotNull(objectMapper);
        assertNotNull(summaryRepository);

    }

}
