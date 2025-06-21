package com.sivalabs.ft.features.domain;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeatureService {
    public static final String FEATURE_SEPARATOR = "-";
    private final ReleaseRepository releaseRepository;
    private final FeatureRepository featureRepository;
    private final ProductRepository productRepository;
    private final FavoriteFeatureRepository favoriteFeatureRepository;
    private final EventPublisher eventPublisher;

    FeatureService(
            ReleaseRepository releaseRepository,
            FeatureRepository featureRepository,
            ProductRepository productRepository,
            FavoriteFeatureRepository favoriteFeatureRepository,
            EventPublisher eventPublisher) {
        this.releaseRepository = releaseRepository;
        this.featureRepository = featureRepository;
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
        this.favoriteFeatureRepository = favoriteFeatureRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Feature> findFeatureByCode(String code) {
        return featureRepository.findByCode(code);
    }

    @Transactional(readOnly = true)
    public List<Feature> findFeaturesByRelease(String releaseCode) {
        return featureRepository.findByReleaseCode(releaseCode);
    }

    @Transactional(readOnly = true)
    public List<Feature> findFeaturesByProduct(String productCode) {
        return featureRepository.findByProductCode(productCode);
    }

    @Transactional(readOnly = true)
    public boolean isFeatureExists(String code) {
        return featureRepository.existsByCode(code);
    }

    @Transactional
    public String createFeature(CreateFeatureCommand cmd) {
        Product product = productRepository.findByCode(cmd.productCode()).orElseThrow();
        Release release = releaseRepository.findByCode(cmd.releaseCode()).orElse(null);
        String code = product.getPrefix() + FEATURE_SEPARATOR + featureRepository.getNextFeatureId();
        var feature = new Feature();
        feature.setProduct(product);
        feature.setRelease(release);
        feature.setCode(code);
        feature.setTitle(cmd.title());
        feature.setDescription(cmd.description());
        feature.setStatus(FeatureStatus.NEW);
        feature.setAssignedTo(cmd.assignedTo());
        feature.setCreatedBy(cmd.createdBy());
        feature.setCreatedAt(Instant.now());
        featureRepository.save(feature);
        eventPublisher.publishFeatureCreatedEvent(feature);
        return code;
    }

    @Transactional
    public void updateFeature(UpdateFeatureCommand cmd) {
        Feature feature = featureRepository.findByCode(cmd.code()).orElseThrow();
        feature.setTitle(cmd.title());
        feature.setDescription(cmd.description());
        if (cmd.releaseCode() != null) {
            Release release = releaseRepository.findByCode(cmd.releaseCode()).orElse(null);
            feature.setRelease(release);
        } else {
            feature.setRelease(null);
        }
        feature.setAssignedTo(cmd.assignedTo());
        feature.setStatus(cmd.status());
        feature.setUpdatedBy(cmd.updatedBy());
        feature.setUpdatedAt(Instant.now());
        featureRepository.save(feature);
        eventPublisher.publishFeatureUpdatedEvent(feature);
    }

    @Transactional
    public void deleteFeature(DeleteFeatureCommand cmd) {
        Feature feature = featureRepository.findByCode(cmd.code()).orElseThrow();
        favoriteFeatureRepository.deleteByFeatureCode(cmd.code());
        featureRepository.deleteByCode(cmd.code());
        eventPublisher.publishFeatureDeletedEvent(feature, cmd.deletedBy(), Instant.now());
    }
}
