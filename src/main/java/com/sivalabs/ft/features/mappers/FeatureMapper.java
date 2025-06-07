package com.sivalabs.ft.features.mappers;

import com.sivalabs.ft.features.api.dtos.FeatureDto;
import com.sivalabs.ft.features.domain.Feature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeatureMapper {
    @Mapping(target = "releaseCode", source = "release.code", defaultExpression = "java( null )")
    FeatureDto toDto(Feature feature);
}
