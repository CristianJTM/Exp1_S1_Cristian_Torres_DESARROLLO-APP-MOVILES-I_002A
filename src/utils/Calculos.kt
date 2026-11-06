package utils

import modelo.ItemPedido

fun calcularSubtotal(items: List<ItemPedido>): Double {
    return items.sumOf { it.subtotal }
}

fun correspondeDescuento(cantidadPizzas: Int): Boolean {
    return cantidadPizzas > UMBRAL_DESCUENTO_PIZZAS
}

fun calcularPorcentajeDescuento(cantidadPizzas: Int): Double {
    return if (correspondeDescuento(cantidadPizzas)) TASA_DESCUENTO else 0.0
}

fun calcularDescuento(subtotal: Double, cantidadPizzas: Int): Double {
    return subtotal * calcularPorcentajeDescuento(cantidadPizzas)
}

fun calcularDescuentoPromocional(subtotal: Double, enPromocion: Boolean): Double {
    return if (enPromocion) subtotal * TASA_DESCUENTO_PROMOCIONAL else 0.0
}

fun calcularImpuesto(base: Double, tasa: Double = TASA_IMPUESTO): Double {
    return base * tasa
}

fun calcularTotal(subtotal: Double, descuento: Double, impuesto: Double): Double {
    return subtotal - descuento + impuesto
}
