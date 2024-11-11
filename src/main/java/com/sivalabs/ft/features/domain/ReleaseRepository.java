package com.sivalabs.ft.features.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface ReleaseRepository extends JpaRepository<Release, Long> {
    Optional<Release> findByCode(String code);

    Optional<ReleaseDto> findReleaseByCode(String code);

    List<ReleaseDto> findByProductCode(String productCode);

    @Modifying
    void deleteByCode(String code);
}
