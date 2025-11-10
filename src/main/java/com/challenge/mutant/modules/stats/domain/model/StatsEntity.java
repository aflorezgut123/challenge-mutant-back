package com.challenge.mutant.modules.stats.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_json", columnDefinition = "TEXT", nullable = false)
    private String dnaJson;

    @Column(name = "is_mutant", nullable = false)
    private boolean isMutant;
}
