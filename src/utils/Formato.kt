package utils

import java.util.Locale

fun formatearMonto(monto: Double): String {
    return String.format(Locale.forLanguageTag("es-CL"), "%,.0f", monto)
}
