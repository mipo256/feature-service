package com.sivalabs.ft.features.api.dtos;

public record ProductDto(
        Long id, String code, String name, String description, String imageUrl, Boolean disabled, String createdBy) {}
