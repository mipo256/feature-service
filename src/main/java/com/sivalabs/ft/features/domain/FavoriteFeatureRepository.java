package com.sivalabs.ft.features.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

interface FavoriteFeatureRepository extends JpaRepository<FavoriteFeature, Long> {

    @Modifying
    @Query(
            """
            delete from FavoriteFeature ff where ff.userId = :userId and
            ff.featureId = (select f.id from Feature f where f.code = :featureCode)
            """)
    int deleteByUserIdAndFeatureCode(String userId, String featureCode);

    boolean existsByUserIdAndFeatureId(String userId, long featureId);

    @Modifying
    @Query(
            """
            delete from FavoriteFeature ff
            where ff.featureId = (select f.id from Feature f where f.code = :featureCode)
            """)
    void deleteByFeatureCode(String featureCode);
}
