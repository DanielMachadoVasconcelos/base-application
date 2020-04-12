package com.ead.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Summary {

    @Id
    private String id;

    private String countryCode;

    private String status;

    private Long casesNumber;

    private OffsetDateTime createdAt;
}
