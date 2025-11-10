package com.challenge.mutant.core.utils;

import java.util.Arrays;

public class DnaUtils {

    /**
     * Convierte un arreglo de cadenas ADN en un JSON válido tipo array.
     * Ejemplo: ["ATGCGA", "CAGTGC"] → "[\"ATGCGA\",\"CAGTGC\"]"
     */
    public static String toJson(String[] dna) {
        return "[" + String.join(",", Arrays.stream(dna)
                .map(s -> "\"" + s + "\"")
                .toArray(String[]::new)) + "]";
    }
}
