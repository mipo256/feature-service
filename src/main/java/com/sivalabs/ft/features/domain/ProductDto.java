package com.sivalabs.ft.features.domain;

public record ProductDto(
        Long id, String code, String name, String description, String imageUrl, Boolean disabled, String createdBy) {}
