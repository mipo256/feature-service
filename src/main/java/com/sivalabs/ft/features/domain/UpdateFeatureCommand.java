package com.sivalabs.ft.features.domain;

public record UpdateFeatureCommand(
        String code, String title, String description, String status, String assignedTo, String updatedBy) {}
