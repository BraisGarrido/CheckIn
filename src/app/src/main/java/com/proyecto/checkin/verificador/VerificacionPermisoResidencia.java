package com.proyecto.checkin.verificador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificacionPermisoResidencia {

    //Se valida que el permiso de residencia español tenga el formato correcto
    public static boolean validarNumeroResidencia(String numeroResidencia) {
        String patron = "^[XYZ]\\d{7}[A-HJ-NP-TV-Z]$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(numeroResidencia);

        return matcher.matches();
    }
}
