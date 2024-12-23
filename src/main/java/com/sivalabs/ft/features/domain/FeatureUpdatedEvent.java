package com.sivalabs.ft.features.domain;

import java.time.Instant;

public record FeatureUpdatedEvent(
        Long id,
        String code,
        String title,
        String description,
        FeatureStatus status,
        String assignedTo,
        String createdBy,
        Instant createdAt,
        String updatedBy,
        Instant updatedAt) {}
