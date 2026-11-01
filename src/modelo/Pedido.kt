package modelo

const val ESTADO_PREPARACION: String = "Preparación"
const val ESTADO_SIN_STOCK: String = "Sin stock"

data class Pedido(
    val id: Int,
    val cliente: Cliente,
    val pizza: Pizza,
    val cantidad: Int,
    val subtotal: Double,
    val porcentajeDescuento: Double,
    val descuento: Double,
    val total: Double,
    val estado: String,
    val repartidor: String
)
