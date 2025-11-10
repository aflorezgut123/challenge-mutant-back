package com.challenge.mutant.modules.stats.api.controller;

import com.challenge.mutant.modules.stats.api.dto.StatsSummary;
import com.challenge.mutant.modules.stats.application.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@Tag(name = "Estadísticas", description = "Consulta estadísticas de ADN analizado")
public class StatsController {
    private final StatsService statsService;

    @Operation(summary = "Obtener estadísticas de ADN", description = "Devuelve el número de mutantes, humanos y el ratio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas correctamente")
    })
    @GetMapping()
    public ResponseEntity<StatsSummary> getStatsSummary() {
        StatsSummary summary = statsService.getStatsSummary();
        return ResponseEntity.ok(summary);
    }

}
