package utils

import modelo.Producto
import java.util.Locale

fun formatearMonto(monto: Double): String {
    return String.format(Locale.forLanguageTag("es-CL"), "%,.0f", monto)
}

fun formatearPorcentaje(tasa: Double): String {
    return "${(tasa * 100).toInt()}%"
}

fun formatearTelefono(telefono: String): String? {
    var digitos: String = telefono.filter { it.isDigit() }

    if (digitos.length == 9) {
        digitos = "56$digitos"
    }

    return when (digitos.length) {
        11 -> "+${digitos.substring(0, 2)} (${digitos.substring(2, 3)}) ${digitos.substring(3, 7)}-${digitos.substring(7, 11)}"
        12 -> "+${digitos.substring(0, 2)} (${digitos.substring(2, 5)}) ${digitos.substring(5, 8)}-${digitos.substring(8, 12)}"
        else -> null
    }
}

fun Producto.detallarProducto(): String {
    val detalleIngredientes: String = if (ingredientes.isEmpty()) {
        "Sin ingredientes"
    } else {
        ingredientes.entries.joinToString { "${it.key} (${it.value})" }
    }

    return "$nombre - \$${formatearMonto(precio)} | " +
        "Categoría: ${categoria.name.lowercase()} | Ingredientes: $detalleIngredientes"
}
