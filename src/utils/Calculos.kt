package utils

import modelo.ItemPedido

const val TASA_IMPUESTO: Double = 0.19

fun calcularSubtotal(items: List<ItemPedido>): Double {
    return items.sumOf { it.subtotal }
}

fun calcularDescuento(subtotal: Double, cantidadPizzas: Int): Double {
    return if (cantidadPizzas > 3) subtotal * 0.10 else 0.0
}

fun calcularImpuesto(base: Double, tasa: Double = TASA_IMPUESTO): Double {
    return base * tasa
}

fun calcularTotal(subtotal: Double, descuento: Double, impuesto: Double): Double {
    return subtotal - descuento + impuesto
}
