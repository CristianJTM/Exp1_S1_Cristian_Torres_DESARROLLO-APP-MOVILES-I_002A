package utils

private val FORMATO_TELEFONO: Regex = Regex("""^\+?\d+([ -]\d+)*$""")

fun leerTexto(mensaje: String, valorPorDefecto: String): String {
    print(mensaje)
    val entrada: String = readlnOrNull()?.trim().orEmpty()
    return if (entrada.isEmpty()) valorPorDefecto else entrada
}

fun validarTelefono(telefono: String): Boolean {
    return FORMATO_TELEFONO.matches(telefono)
}

fun leerTelefono(): String {
    while (true) {
        print("Teléfono (opcional): ")
        val entrada: String? = readlnOrNull()

        if (entrada == null) {
            return "Sin teléfono"
        }

        val telefono: String = entrada.trim()
        if (telefono.isEmpty()) {
            return "Sin teléfono"
        }

        if (validarTelefono(telefono)) {
            return telefono
        }

        println("Teléfono inválido. Use dígitos, un + opcional al inicio y separadores simples con espacio o guion.")
    }
}

fun leerCantidad(): Int {
    while (true) {
        print("Ingrese la cantidad de pizzas: ")
        val entrada: String? = readlnOrNull()

        if (entrada == null) {
            println("\nNo se recibió una cantidad. Se usará 1 pizza por defecto.")
            return 1
        }

        val cantidad: Int? = entrada.trim().toIntOrNull()
        if (cantidad != null && cantidad > 0) {
            return cantidad
        }

        println("Ingrese una cantidad válida mayor que cero.")
    }
}

fun leerOpcion(cantidadOpciones: Int): Int {
    while (true) {
        print("Seleccione una pizza (1-$cantidadOpciones): ")
        val entrada: String? = readlnOrNull()

        if (entrada == null) {
            println("\nNo se recibió una opción. Se usará la primera pizza.")
            return 1
        }

        val opcion: Int? = entrada.trim().toIntOrNull()
        if (opcion != null && opcion in 1..cantidadOpciones) {
            return opcion
        }

        println("Opción inválida. Ingrese un número entre 1 y $cantidadOpciones.")
    }
}
