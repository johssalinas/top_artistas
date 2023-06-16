package com.johs.top_artistas.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    public static String formatNumberWithThousands(String numberString) {
        try {
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
            decimalFormat.applyPattern("#,###");
            return decimalFormat.format(Double.parseDouble(numberString));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return numberString; // Devuelve la cadena original si no se puede formatear
    }
}
