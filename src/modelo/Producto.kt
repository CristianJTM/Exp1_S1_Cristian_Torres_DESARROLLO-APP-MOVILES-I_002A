package modelo

data class Producto(
    val nombre: String,
    val precio: Double,
    val ingredientes: Map<String, Int>
) {
    init {
        require(precio > 0) { "El precio debe ser mayor que cero." }
        require(ingredientes.isNotEmpty()) { "El producto debe tener ingredientes." }
    }
}
