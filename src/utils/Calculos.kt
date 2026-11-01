package utils

import java.util.Locale

fun calcularSubtotal(precio: Double, cantidad: Int): Double {
    return precio * cantidad
}

fun calcularPorcentajeDescuento(cantidad: Int): Double {
    return if (cantidad > 3) 0.10 else 0.0
}

fun calcularDescuento(subtotal: Double, porcentaje: Double): Double {
    return subtotal * porcentaje
}

fun calcularTotal(subtotal: Double, descuento: Double): Double {
    return subtotal - descuento
}

fun formatearMonto(monto: Double): String {
    return String.format(Locale.forLanguageTag("es-CL"), "%,.0f", monto)
}
