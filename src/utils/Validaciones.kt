package utils

private val FORMATO_TELEFONO: Regex = Regex("""^\+?\d+([ -]\d+)*$""")
private val FORMATO_CORREO: Regex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$""")

fun leerTexto(mensaje: String, valorPorDefecto: String): String {
    print(mensaje)
    val entrada: String = readlnOrNull()?.trim().orEmpty()
    return if (entrada.isEmpty()) valorPorDefecto else entrada
}

fun leerTelefono(): String {
    print("Teléfono: ")
    val telefono: String = readlnOrNull()?.trim().orEmpty()

    if (FORMATO_TELEFONO.matches(telefono)) {
        return telefono
    }

    println("Teléfono inválido. Se usará (000) 000-0000.")
    return "(000) 000-0000"
}

fun leerCorreo(): String? {
    print("Correo: ")
    val correo: String = readlnOrNull()?.trim().orEmpty()

    if (FORMATO_CORREO.matches(correo)) {
        return correo
    }

    println("Correo inválido. Se mostrará correo@invalido.com.")
    return null
}

fun leerNotas(): String? {
    print("Notas del pedido (opcional): ")
    return readlnOrNull()?.trim()?.takeIf { it.isNotEmpty() }
}

fun leerCantidad(mensaje: String): Int {
    while (true) {
        print(mensaje)
        val entrada: String? = readlnOrNull()

        if (entrada == null) {
            println("No se recibió una cantidad. Se usará 1 por defecto.")
            return 1
        }

        val cantidad: Int = try {
            entrada.trim().toInt()
        } catch (_: NumberFormatException) {
            println("Error: ingrese un número entero.")
            continue
        }

        if (cantidad > 0) {
            return cantidad
        }

        println("Error: la cantidad debe ser mayor que cero.")
    }
}

fun leerOpcion(minimo: Int, maximo: Int, mensaje: String): Int? {
    while (true) {
        print(mensaje)
        val entrada: String = readlnOrNull()?.trim() ?: return null

        val opcion: Int? = entrada.toIntOrNull()
        if (opcion != null && opcion in minimo..maximo) {
            return opcion
        }

        println("Opción inválida. Ingrese un número entre $minimo y $maximo.")
    }
}

fun leerSiNo(mensaje: String): Boolean {
    while (true) {
        print(mensaje)
        val entrada: String = readlnOrNull()?.trim()?.lowercase() ?: return false

        when (entrada) {
            "s", "si", "sí" -> return true
            "n", "no" -> return false
            else -> println("Ingrese s o n.")
        }
    }
}
