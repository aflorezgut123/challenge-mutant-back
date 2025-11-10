package com.challenge.mutant.modules.adn.api.controller;

import com.challenge.mutant.modules.adn.api.dto.DnaRequest;
import com.challenge.mutant.modules.adn.application.service.MutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Endpoint detección mutante", description = "Detecta si una secuencia de ADN pertenece a un mutante")
public class MutantController {
    private final MutantService mutantService;

    @Operation(summary = "Detectar mutante", description = "Recibe una secuencia de ADN y determina si es mutante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mutante detectado"),
            @ApiResponse(responseCode = "403", description = "No es mutante"),
            @ApiResponse(responseCode = "500", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest request){
        boolean result = mutantService.isMutant(request.getDna().toArray(new String[0]));
        return result ? ResponseEntity.ok("Mutante detectado")
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
    }
}
