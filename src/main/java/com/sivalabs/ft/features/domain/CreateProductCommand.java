package com.sivalabs.ft.features.domain;

public record CreateProductCommand(String code, String name, String description, String imageUrl, String createdBy) {}
