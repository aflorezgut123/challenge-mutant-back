package com.challenge.mutant.modules.adn.application.service.impl;

import com.challenge.mutant.modules.adn.application.service.MutantService;
import com.challenge.mutant.modules.stats.application.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.challenge.mutant.core.utils.DnaUtils.toJson;

@Service
@RequiredArgsConstructor
@Log4j2
public class MutantServiceImpl implements MutantService {

    private static final Set<Character> VALID_NUCLEOTIDES = Set.of('A', 'T', 'C', 'G');
    private final StatsService statsService;

    public boolean isMutant(String[] dna) {
        int rows = dna.length;
        if (rows == 0) return false;

        int cols = dna[0].length();

        // Validar que todas las filas tengan la misma longitud
        for (int i = 0; i < dna.length; i++) {
            String row = dna[i];
            if (row.length() != cols) {
                throw new IllegalArgumentException("Fila " + i + " tiene longitud inválida: se esperaban " + cols + " columnas");
            }
            for (int j = 0; j < cols; j++) {
                char c = row.charAt(j);
                if (!VALID_NUCLEOTIDES.contains(c)) {
                    throw new IllegalArgumentException("Caracter no válido en fila " + i + ", columna " + j + ": " + c);
                }
            }
        }

        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        int sequencesFound = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char current = matrix[i][j];

                // Horizontal
                if (j + 3 < cols &&
                        current == matrix[i][j+1] &&
                        current == matrix[i][j+2] &&
                        current == matrix[i][j+3]) {
                    sequencesFound++;
                }

                // Vertical
                if (i + 3 < rows &&
                        current == matrix[i+1][j] &&
                        current == matrix[i+2][j] &&
                        current == matrix[i+3][j]) {
                    sequencesFound++;
                }

                // Diagonal derecha
                if (i + 3 < rows && j + 3 < cols &&
                        current == matrix[i+1][j+1] &&
                        current == matrix[i+2][j+2] &&
                        current == matrix[i+3][j+3]) {
                    sequencesFound++;
                }

                // Diagonal izquierda
                if (i + 3 < rows && j - 3 >= 0 &&
                        current == matrix[i+1][j-1] &&
                        current == matrix[i+2][j-2] &&
                        current == matrix[i+3][j-3]) {
                    sequencesFound++;
                }

                if (sequencesFound >= 2) {
                    statsService.saveStats(toJson(dna), true);
                    return true;
                }
            }
        }
        statsService.saveStats(toJson(dna), false);
        return false;
    }
}
