package com.sivalabs.ft.features.domain;

import java.io.Serializable;
import java.time.Instant;

public record FeatureDto(
        Long id,
        String code,
        String title,
        String description,
        String status,
        String assignedTo,
        String createdBy,
        Instant createdAt,
        String updatedBy,
        Instant updatedAt)
        implements Serializable {}
