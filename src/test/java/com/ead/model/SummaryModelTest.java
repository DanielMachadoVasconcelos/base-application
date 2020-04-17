package com.ead.model;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SummaryModelTest {

    @Test
    void whenNewInstanceUsingBuilderThenAllDataShouldAssert(){
        String expectedCountryCode = "br";
        String expectedStatus = "confirmed";
        String expectedId = UUID.randomUUID().toString();
        Long expectedCasesNumber = new Random().nextLong();
        OffsetDateTime expectedCreatedAt = OffsetDateTime.now();

        Summary subject = Summary.builder()
                .id(expectedId)
                .status(expectedStatus)
                .createdAt(expectedCreatedAt)
                .casesNumber(expectedCasesNumber)
                .countryCode(expectedCountryCode)
                .build();

        assertEquals(subject.getId(), expectedId);
        assertEquals(subject.getStatus(), expectedStatus);
        assertEquals(subject.getCreatedAt(), expectedCreatedAt);
        assertEquals(subject.getCasesNumber(), expectedCasesNumber);
        assertEquals(subject.getCountryCode(), expectedCountryCode);
    }
}