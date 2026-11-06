package utils

import modelo.Producto
import java.util.Locale

fun formatearMonto(monto: Double): String {
    return String.format(Locale.forLanguageTag("es-CL"), "%,.0f", monto)
}

fun formatearPorcentaje(tasa: Double): String {
    return "${(tasa * 100).toInt()}%"
}

fun Producto.detallarProducto(): String {
    val detalleIngredientes: String = ingredientes.entries.joinToString { "${it.key} (${it.value})" }
    return "$nombre - \$${formatearMonto(precio)} | Ingredientes: $detalleIngredientes"
}
