package com.sivalabs.ft.features.domain;

public record CreateReleaseCommand(String productCode, String code, String description, String createdBy) {}
