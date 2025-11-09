package com.challenge.mutant.modules.adn.api.controller;

import com.challenge.mutant.modules.adn.api.dto.DnaRequest;
import com.challenge.mutant.modules.adn.application.service.MutantService;
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
public class MutantController {
    private final MutantService mutantService;

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest request){
        boolean result = mutantService.isMutant(request.getDna().toArray(new String[0]));
        return result ? ResponseEntity.ok("Mutante detectado")
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
    }
}
