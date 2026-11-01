import modelo.Cliente
import modelo.ESTADO_PREPARACION
import modelo.ESTADO_SIN_STOCK
import modelo.Pedido
import modelo.Pizza
import utils.calcularDescuento
import utils.calcularPorcentajeDescuento
import utils.calcularSubtotal
import utils.calcularTotal
import utils.formatearMonto
import utils.leerCantidad
import utils.leerOpcion
import utils.leerTelefono
import utils.leerTexto

fun main() {
    println("=== PIZZERÍA ===")

    val pizzas: List<Pizza> = listOf(
        Pizza("Pepperoni Familiar", 12990.0, 20),
        Pizza("Margarita Familiar", 10990.0, 15),
        Pizza("Cuatro Quesos", 14990.0, 10)
    )

    val cliente: Cliente = Cliente(
        nombre = leerTexto("Nombre: ", "Cliente sin nombre"),
        direccion = leerTexto("Dirección: ", "Sin dirección"),
        telefono = leerTelefono()
    )

    println()
    println("Datos registrados:")
    println("Nombre: ${cliente.nombre}")
    println("Dirección: ${cliente.direccion}")
    println("Teléfono: ${cliente.telefono}")

    mostrarMenu(pizzas)

    val opcion: Int = leerOpcion(pizzas.size)
    val pizza: Pizza = pizzas[opcion - 1]
    val cantidad: Int = leerCantidad()

    val subtotal: Double = calcularSubtotal(pizza.precio, cantidad)
    val porcentajeDescuento: Double = calcularPorcentajeDescuento(cantidad)
    val descuento: Double = calcularDescuento(subtotal, porcentajeDescuento)
    val total: Double = calcularTotal(subtotal, descuento)

    val hayStock: Boolean = pizza.stock >= cantidad
    val estado: String
    val repartidor: String

    if (hayStock) {
        pizza.stock -= cantidad
        estado = ESTADO_PREPARACION
        repartidor = "Camila Rojas"
    } else {
        estado = ESTADO_SIN_STOCK
        repartidor = "No asignado"
    }

    val pedido: Pedido = Pedido(
        id = 1001,
        cliente = cliente,
        pizza = pizza,
        cantidad = cantidad,
        subtotal = subtotal,
        porcentajeDescuento = porcentajeDescuento,
        descuento = descuento,
        total = total,
        estado = estado,
        repartidor = repartidor
    )

    mostrarResumen(pedido)

    if (pedido.estado == ESTADO_SIN_STOCK) {
        println("El pedido no fue confirmado por falta de stock. No se cobrará ni se asignará repartidor.")
    }
}

fun mostrarMenu(pizzas: List<Pizza>) {
    println()
    println("=== MENÚ DE PIZZAS ===")
    for ((indice, pizza) in pizzas.withIndex()) {
        println("${indice + 1}. ${pizza.nombre} - \$${formatearMonto(pizza.precio)} (stock: ${pizza.stock})")
    }
}

fun mostrarResumen(pedido: Pedido) {
    val etiquetaTotal: String = if (pedido.estado == ESTADO_PREPARACION) {
        "Total a pagar"
    } else {
        "Total calculado (no cobrado)"
    }

    println()
    println("============================================================")
    println("                 RESUMEN DEL PEDIDO")
    println("============================================================")

    println("--- Datos del cliente ---")
    println("Nombre: ${pedido.cliente.nombre}")
    println("Dirección: ${pedido.cliente.direccion}")
    println("Teléfono: ${pedido.cliente.telefono}")

    println("--- Datos de la pizza ---")
    println("Pizza: ${pedido.pizza.nombre}")
    println("Precio unitario: \$${formatearMonto(pedido.pizza.precio)}")
    println("Stock restante: ${pedido.pizza.stock}")

    println("--- Datos del pedido ---")
    println("ID del pedido: ${pedido.id}")
    println("Cantidad de pizzas: ${pedido.cantidad}")
    println("Subtotal: \$${formatearMonto(pedido.subtotal)}")
    println("Porcentaje de descuento: ${(pedido.porcentajeDescuento * 100).toInt()}%")
    println("Monto del descuento: \$${formatearMonto(pedido.descuento)}")
    println("$etiquetaTotal: \$${formatearMonto(pedido.total)}")
    println("Estado del pedido: ${pedido.estado}")
    println("Repartidor: ${pedido.repartidor}")
    println("============================================================")
}
