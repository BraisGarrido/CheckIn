package com.proyecto.checkin.verificador;

import java.util.Scanner;

public class VerificarDni {
    public static boolean validarDNI(String dni) {
        dni = dni.toUpperCase(); // Convertir a mayúsculas

        // Verificar formato del DNI
        if (!dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            return false;
        }

        // Calcular letra del DNI
        char letraCalculada = calcularLetraDNI(dni.substring(0, 8));

        // Comparar letra calculada con la letra del DNI
        return letraCalculada == dni.charAt(8);
    }

    public static char calcularLetraDNI(String numerosDNI) {
        int dniNumeros = Integer.parseInt(numerosDNI);
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int indice = dniNumeros % 23;
        return letras.charAt(indice);
    }
}
