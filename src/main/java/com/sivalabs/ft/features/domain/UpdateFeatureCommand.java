package com.sivalabs.ft.features.domain;

public record UpdateFeatureCommand(
        String code,
        String title,
        String description,
        FeatureStatus status,
        String releaseCode,
        String assignedTo,
        String updatedBy) {}
