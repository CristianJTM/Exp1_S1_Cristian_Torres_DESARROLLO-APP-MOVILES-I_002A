package servicio

import modelo.ItemPedido

class Inventario(disponibilidadInicial: Map<String, Int>) {
    private val disponibilidad: MutableMap<String, Int> = disponibilidadInicial.toMutableMap()

    fun consultar(ingrediente: String): Int = disponibilidad[ingrediente] ?: 0

    fun calcularRequerimientos(ingredientes: List<String>, items: List<ItemPedido>): Map<String, Int> {
        return ingredientes.distinct().associateWith { ingrediente ->
            items.sumOf { item ->
                (item.producto.ingredientes[ingrediente] ?: 0) * item.cantidad
            }
        }
    }

    fun calcularRequerimientos(items: List<ItemPedido>): Map<String, Int> {
        val ingredientes: List<String> = items
            .flatMap { it.producto.ingredientes.keys }
            .distinct()

        return calcularRequerimientos(ingredientes, items)
    }

    fun ingredientesFaltantes(items: List<ItemPedido>): Map<String, Int> {
        return calcularRequerimientos(items)
            .filter { (ingrediente, requerido) -> consultar(ingrediente) < requerido }
            .mapValues { (ingrediente, requerido) -> requerido - consultar(ingrediente) }
    }

    fun actualizarInventario(items: List<ItemPedido>) {
        val requerimientos: Map<String, Int> = calcularRequerimientos(items)

        for ((ingrediente, cantidad) in requerimientos) {
            disponibilidad[ingrediente] = consultar(ingrediente) - cantidad
        }
    }

    fun reponer(ingrediente: String, cantidad: Int) {
        try {
            require(cantidad > 0) { "La cantidad a reponer debe ser mayor que cero." }
            disponibilidad[ingrediente] = consultar(ingrediente) + cantidad
            println("Inventario actualizado: $ingrediente ahora tiene ${consultar(ingrediente)} unidades.")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }
    }

    fun ingredientesDisponibles(): Set<String> = disponibilidad.keys

    fun mostrar() {
        println()
        println("=== INVENTARIO ===")
        for ((ingrediente, cantidad) in disponibilidad) {
            println("- $ingrediente: $cantidad")
        }
    }
}
