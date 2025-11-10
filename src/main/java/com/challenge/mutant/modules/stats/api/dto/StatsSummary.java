package com.challenge.mutant.modules.stats.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatsSummary {
    private long count_mutant_dna;
    private long count_human_dna;
    private double ratio;
}