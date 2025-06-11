package com.sivalabs.ft.features.domain;

public record CreateProductCommand(
        String code, String prefix, String name, String description, String imageUrl, String createdBy) {}
