// Actividad formativa: Mi primera app en Kotlin

fun main() {
    // 1. DECLARACIÓN DE VARIABLES
    println("=== PIZZERÍA ===")

    val nombrePizza: String = "Pizza Pepperoni Familiar"
    val precioUnitario: Double = 12990.0

    var ingredientesDisponibles: Int = 20

    val idPedido: Int = 1001
    val estadoPreparacion: String = "Preparación"
    val estadoSinStock: String = "Sin stock"

    var cantidadPizzas: Int = 0
    var totalPedido: Double = 0.0
    var estadoPedido: String = "Pendiente"

    var nombre: String = ""
    var direccion: String = ""
    var telefono: String = ""

    println("Pizza: $nombrePizza")
    println("Precio unitario: \$$precioUnitario")

    // 2. CAPTURA DE DATOS DEL CLIENTE
    println()
    println("=== DATOS DEL CLIENTE ===")

    print("Nombre: ")
    val entradaNombre: String? = readLine()
    nombre = if (entradaNombre.isNullOrBlank()) "Cliente sin nombre" else entradaNombre.trim()

    print("Dirección: ")
    val entradaDireccion: String? = readLine()
    direccion = if (entradaDireccion.isNullOrBlank()) "Sin dirección" else entradaDireccion.trim()

    print("Teléfono: ")
    val telefonoIngresado: String = readLine()?.trim() ?: ""

    // + opcional al inicio, dígitos, y grupos separados por un solo guion o espacio
    val formatoTelefono: Regex = Regex("""^\+?\d+([ -]\d+)*$""")
    val telefonoValido: Boolean = formatoTelefono.matches(telefonoIngresado)
    telefono = if (telefonoValido) telefonoIngresado else "Sin teléfono"

    if (!telefonoValido && telefonoIngresado.isNotEmpty()) {
        println("Advertencia: teléfono inválido. Se usará \"Sin teléfono\".")
    }

    println()
    println("Datos registrados:")
    println("Nombre: $nombre")
    println("Dirección: $direccion")
    println("Teléfono: $telefono")

    // 3. CANTIDAD DE PIZZAS
    println()
    println("=== CANTIDAD DE PIZZAS ===")
    print("Ingrese la cantidad de pizzas: ")
    val cantidadLeida: Int = try {
        readLine()?.trim()?.toInt() ?: 0
    } catch (_: NumberFormatException) {
        0
    }

    if (cantidadLeida <= 0) {
        println("Advertencia: cantidad inválida. Se usará 1 pizza por defecto.")
    }
    cantidadPizzas = if (cantidadLeida > 0) cantidadLeida else 1

    // 4. CÁLCULO DEL TOTAL
    val subtotal: Double = precioUnitario * cantidadPizzas
    val porcentajeDescuento: Double = if (cantidadPizzas > 3) 0.10 else 0.0
    val montoDescuento: Double = subtotal * porcentajeDescuento
    totalPedido = subtotal - montoDescuento

    // 5. VERIFICACIÓN DE STOCK
    val stockInicial: Int = ingredientesDisponibles
    val hayStock: Boolean = ingredientesDisponibles >= cantidadPizzas

    if (hayStock) {
        ingredientesDisponibles -= cantidadPizzas
        estadoPedido = estadoPreparacion
    } else {
        estadoPedido = estadoSinStock
    }

    val mensajeStock: String = if (hayStock) {
        "Pedido confirmado. Stock restante: $ingredientesDisponibles"
    } else {
        "Sin stock. Disponible: $ingredientesDisponibles, solicitado: $cantidadPizzas"
    }
    println()
    println(mensajeStock)

    // 6. REPARTIDOR
    val repartidor: String = "Camila Rojas"
    val repartidorAsignado: String = if (estadoPedido == estadoPreparacion) repartidor else "No asignado"

    // 7. RESUMEN FINAL
    val etiquetaTotal: String = if (estadoPedido == estadoPreparacion) "Total a pagar" else "Total calculado (no cobrado)"

    println()
    println("============================================================")
    println("                 RESUMEN DEL PEDIDO")
    println("============================================================")

    println("--- Datos del cliente ---")
    println("Nombre: $nombre")
    println("Dirección: $direccion")
    println("Teléfono: $telefono")

    println("--- Datos de la pizza ---")
    println("Pizza: $nombrePizza")
    println("Precio unitario: \$$precioUnitario")
    println("Stock inicial: $stockInicial")
    println("Stock restante: $ingredientesDisponibles")

    println("--- Datos del pedido ---")
    println("ID del pedido: $idPedido")
    println("Cantidad de pizzas: $cantidadPizzas")
    println("Subtotal: \$$subtotal")
    println("Porcentaje de descuento: ${porcentajeDescuento * 100}%")
    println("Monto del descuento: \$$montoDescuento")
    println("$etiquetaTotal: \$$totalPedido")
    println("Estado del pedido: $estadoPedido")
    println("Repartidor: $repartidorAsignado")
    println("============================================================")

    if (estadoPedido == estadoSinStock) {
        println("El pedido no fue confirmado por falta de stock. No se cobrará ni se asignará repartidor.")
    }
}
