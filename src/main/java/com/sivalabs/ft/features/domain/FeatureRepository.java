package com.sivalabs.ft.features.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Optional<Feature> findByCode(String code);

    List<FeatureDto> findByReleaseCode(String releaseCode);

    Optional<FeatureDto> findFeatureByCode(String code);

    @Modifying
    void deleteByCode(String code);

    @Modifying
    @Query("delete from Feature f where f.release.code = :code")
    void deleteByReleaseCode(String code);
}
