package com.challenge.mutant.modules.stats.application.service.impl;

import com.challenge.mutant.modules.stats.api.dto.StatsSummary;
import com.challenge.mutant.modules.stats.application.service.StatsService;
import com.challenge.mutant.modules.stats.domain.model.StatsEntity;
import com.challenge.mutant.modules.stats.infrastructure.persistence.jpa.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    public void saveStats(String dnaJson, boolean isMutant) {
        boolean exists = statsRepository.findByDnaJson(dnaJson).isPresent();
        if (exists) {
            // Ya existe, no se inserta
            return;
        }
        StatsEntity stats = new StatsEntity();
        stats.setDnaJson(dnaJson);
        stats.setMutant(isMutant);
        statsRepository.save(stats);
    }

    public StatsSummary getStatsSummary() {
        long mutantCount = statsRepository.countByIsMutant(true);
        long humanCount = statsRepository.countByIsMutant(false);
        double ratio = humanCount == 0 ? 0.0 : (double) mutantCount / humanCount;

        return new StatsSummary(mutantCount, humanCount, ratio);
    }
}
