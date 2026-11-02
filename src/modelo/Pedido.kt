package modelo

import utils.calcularDescuento as calcularDescuentoUtil
import utils.calcularImpuesto as calcularImpuestoUtil
import utils.calcularSubtotal as calcularSubtotalUtil
import utils.calcularTotal as calcularTotalUtil
import utils.formatearMonto

class Pedido(
    val id: Int,
    val cliente: Cliente,
    val items: MutableList<ItemPedido>,
    val notas: String? = null,
    var estado: EstadoPedido = EstadoPedido.EN_PREPARACION,
    var repartidor: Repartidor? = null
) {
    val productos: List<Producto>
        get() = items.map { it.producto }

    val cantidadPizzas: Int
        get() = items.sumOf { it.cantidad }

    fun agregarProducto(producto: Producto, cantidad: Int) {
        items.add(ItemPedido(producto, cantidad))
    }

    fun calcularSubtotal(): Double = calcularSubtotalUtil(items)

    fun calcularDescuento(): Double = calcularDescuentoUtil(calcularSubtotal(), cantidadPizzas)

    fun calcularImpuesto(): Double = calcularImpuestoUtil(calcularSubtotal() - calcularDescuento())

    fun calcularTotal(): Double = calcularTotalUtil(calcularSubtotal(), calcularDescuento(), calcularImpuesto())

    fun cambiarEstado(nuevoEstado: EstadoPedido) {
        estado = nuevoEstado
    }

    fun asignarRepartidor(nuevoRepartidor: Repartidor) {
        repartidor = nuevoRepartidor
    }

    fun notificarCliente() {
        cliente.correo?.let { correo ->
            println("Notificación enviada a ${cliente.nombre} <$correo>.")
        }
    }

    fun generarResumen(): String = buildString {
        val subtotal: Double = calcularSubtotal()
        val descuento: Double = calcularDescuento()
        val impuesto: Double = calcularImpuesto()
        val total: Double = calcularTotal()
        val porcentajeDescuento: String = if (cantidadPizzas > 3) "10%" else "0%"

        appendLine("============================================================")
        appendLine("                 RESUMEN DEL PEDIDO #$id")
        appendLine("============================================================")
        appendLine(cliente.describir())
        appendLine("Notas: ${notas ?: "Sin notas"}")
        appendLine("------------------------------------------------------------")
        appendLine("Productos:")
        for (item in items) {
            appendLine(
                "  - ${item.producto.nombre} | Precio: \$${formatearMonto(item.producto.precio)} | " +
                    "Cantidad: ${item.cantidad} | Subtotal: \$${formatearMonto(item.subtotal)}"
            )
        }
        appendLine("------------------------------------------------------------")
        appendLine("Subtotal: \$${formatearMonto(subtotal)}")
        appendLine("Descuento ($porcentajeDescuento): \$${formatearMonto(descuento)}")
        appendLine("Impuesto (19%): \$${formatearMonto(impuesto)}")
        appendLine("Total final: \$${formatearMonto(total)}")
        appendLine("Estado: ${estado.descripcion}")
        appendLine("Repartidor: ${repartidor?.nombre ?: "Sin asignar"}")
        appendLine("============================================================")
    }
}
