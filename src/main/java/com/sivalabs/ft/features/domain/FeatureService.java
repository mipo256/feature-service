package com.sivalabs.ft.features.domain;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FeatureService {
    private final ReleaseRepository releaseRepository;
    private final FeatureRepository featureRepository;
    private final ProductRepository productRepository;

    FeatureService(
            ReleaseRepository releaseRepository,
            FeatureRepository featureRepository,
            ProductRepository productRepository) {
        this.releaseRepository = releaseRepository;
        this.featureRepository = featureRepository;
        this.productRepository = productRepository;
    }

    public Optional<Feature> findFeatureByCode(String code) {
        return featureRepository.findByCode(code);
    }

    public List<Feature> findFeatures(String releaseCode) {
        return featureRepository.findByReleaseCode(releaseCode);
    }

    public boolean isFeatureExists(String code) {
        return featureRepository.existsByCode(code);
    }

    @Transactional
    public Long createFeature(CreateFeatureCommand cmd) {
        Release release = releaseRepository.findByCode(cmd.releaseCode()).orElseThrow();
        Product product =
                productRepository.findByCode(release.getProduct().getCode()).orElseThrow();
        var feature = new Feature();
        feature.setProduct(product);
        feature.setRelease(release);
        feature.setCode(cmd.code());
        feature.setTitle(cmd.title());
        feature.setDescription(cmd.description());
        feature.setStatus("NEW");
        feature.setAssignedTo(cmd.assignedTo());
        feature.setCreatedBy(cmd.createdBy());
        feature.setCreatedAt(Instant.now());
        featureRepository.save(feature);
        return feature.getId();
    }

    @Transactional
    public void updateFeature(UpdateFeatureCommand cmd) {
        Feature feature = featureRepository.findByCode(cmd.code()).orElseThrow();
        feature.setTitle(cmd.title());
        feature.setDescription(cmd.description());
        feature.setAssignedTo(cmd.assignedTo());
        feature.setStatus(cmd.status());
        feature.setUpdatedBy(cmd.updatedBy());
        feature.setUpdatedAt(Instant.now());
        featureRepository.save(feature);
    }

    @Transactional
    public void deleteFeature(String code) {
        featureRepository.deleteByCode(code);
    }
}
