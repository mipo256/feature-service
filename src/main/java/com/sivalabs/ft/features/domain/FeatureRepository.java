package com.sivalabs.ft.features.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

interface FeatureRepository extends ListCrudRepository<Feature, Long> {
    Optional<Feature> findByCode(String code);

    List<Feature> findByReleaseCode(String releaseCode);

    @Modifying
    void deleteByCode(String code);

    @Modifying
    @Query("delete from Feature f where f.release.code = :code")
    void deleteByReleaseCode(String code);
}
