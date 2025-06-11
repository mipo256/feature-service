package com.sivalabs.ft.features.domain;

public record UpdateProductCommand(
        String code, String prefix, String name, String description, String imageUrl, String updatedBy) {}
