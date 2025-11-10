package com.challenge.mutant.modules.stats.application.service;

import com.challenge.mutant.modules.stats.api.dto.StatsSummary;

public interface StatsService {
    void saveStats(String dnaJson, boolean isMutant);
    StatsSummary getStatsSummary();
}
