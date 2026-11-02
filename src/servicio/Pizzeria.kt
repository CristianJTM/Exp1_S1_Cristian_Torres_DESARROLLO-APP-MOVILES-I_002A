package servicio

import modelo.Cliente
import modelo.EstadoPedido
import modelo.ItemPedido
import modelo.Pedido
import modelo.Producto
import modelo.Repartidor
import utils.formatearMonto

class Pizzeria(
    val productos: List<Producto>,
    val inventario: Inventario,
    val repartidores: List<Repartidor>
) {
    private val pedidos: MutableList<Pedido> = mutableListOf()
    private var siguienteId: Int = 1001

    fun mostrarProductos() {
        println()
        println("=== CATÁLOGO DE PIZZAS ===")
        for ((indice, producto) in productos.withIndex()) {
            println("${indice + 1}. ${producto.detallarProducto()}")
        }
    }

    fun registrarPedido(cliente: Cliente, items: List<ItemPedido>, notas: String?): Pedido? {
        if (items.isEmpty()) {
            println("El pedido debe tener al menos un producto.")
            return null
        }

        val faltantes: Map<String, Int> = inventario.ingredientesFaltantes(items)

        return when {
            faltantes.isNotEmpty() -> {
                println()
                println("No hay ingredientes suficientes para preparar el pedido:")
                for ((ingrediente, cantidad) in faltantes) {
                    println("- $ingrediente: faltan $cantidad unidades.")
                }
                println("Pasos para reabastecer:")
                println("1. Revisar el ingrediente faltante.")
                println("2. Registrar la compra o entrada al inventario.")
                println("3. Reponerlo desde la opción de inventario.")
                println("4. Verificar nuevamente la disponibilidad.")
                null
            }
            else -> {
                inventario.actualizarInventario(items)
                val pedido: Pedido = Pedido(
                    id = siguienteId,
                    cliente = cliente,
                    items = items.toMutableList(),
                    notas = notas
                )
                siguienteId += 1
                pedidos.add(pedido)
                println("Pedido #${pedido.id} registrado. Inventario actualizado.")
                pedido
            }
        }
    }

    fun asignarRepartidor(pedido: Pedido): Repartidor? {
        if (pedido.estado == EstadoPedido.EN_CAMINO) {
            println("El pedido #${pedido.id} ya está en camino.")
            return null
        }

        val repartidor: Repartidor? = repartidores
            .sortedBy { it.distanciaKm }
            .find { it.zona == pedido.cliente.zona && it.disponible }

        repartidor?.let {
            it.entregarPedido(pedido)
            println("Repartidor asignado: ${it.describir()}")
        } ?: println("No hay repartidores disponibles en la zona ${pedido.cliente.zona}.")

        return repartidor
    }

    fun listarPedidos(): List<Pedido> = pedidos.toList()

    fun obtenerPedido(indice: Int): Pedido? = pedidos.getOrNull(indice)

    fun generarReporte() {
        val pedidosDelDia: Array<Pedido> = pedidos.toTypedArray()

        if (pedidosDelDia.isEmpty()) {
            println("No hay pedidos registrados hoy.")
            return
        }

        println()
        println("============================================================")
        println("                 REPORTE DEL DÍA")
        println("============================================================")

        for (pedido in pedidosDelDia) {
            println(
                "Pedido #${pedido.id} | Cliente: ${pedido.cliente.nombre} | " +
                    "Pizzas: ${pedido.cantidadPizzas} | " +
                    "Total: \$${formatearMonto(pedido.calcularTotal())} | " +
                    "Estado: ${pedido.estado.descripcion}"
            )
        }

        val completados: List<Pedido> = pedidosDelDia.filter {
            it.estado == EstadoPedido.LISTO || it.estado == EstadoPedido.EN_CAMINO
        }
        val pendientes: List<Pedido> = pedidosDelDia.filter {
            it.estado == EstadoPedido.EN_PREPARACION
        }

        println("------------------------------------------------------------")
        println("Pedidos listos o en camino: ${completados.size}")
        for (pedido in completados) {
            println("  #${pedido.id} - ${pedido.cliente.nombre} - ${pedido.estado.descripcion}")
        }

        println("Pedidos pendientes: ${pendientes.size}")
        for (pedido in pendientes) {
            println("  #${pedido.id} - ${pedido.cliente.nombre} - ${pedido.estado.descripcion}")
        }
        println("============================================================")
    }
}
