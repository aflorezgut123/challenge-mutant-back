package com.challenge.mutant.modules.stats.infrastructure.persistence.jpa;

import com.challenge.mutant.modules.stats.domain.model.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity, Long> {
    Optional<StatsEntity> findByDnaJson(String dnaJson);
    long countByIsMutant(boolean isMutant);

}
