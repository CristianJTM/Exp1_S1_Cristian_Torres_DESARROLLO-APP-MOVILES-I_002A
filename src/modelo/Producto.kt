package modelo

open class Producto(
    val nombre: String,
    val precio: Double,
    val ingredientes: Map<String, Int>,
    val categoria: CategoriaProducto
) {
    init {
        require(precio > 0) { "El precio debe ser mayor que cero." }

        if (categoria == CategoriaProducto.PIZZA || categoria == CategoriaProducto.COMBO) {
            require(ingredientes.isNotEmpty()) { "Las pizzas y combos deben tener ingredientes." }
        }
    }

    fun esPromocionable(): Boolean {
        return javaClass.isAnnotationPresent(Promocionable::class.java)
    }

    fun descripcionPromocion(): String? {
        return javaClass.getAnnotation(Promocionable::class.java)?.descripcion
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Producto) {
            return false
        }
        return nombre == other.nombre && categoria == other.categoria
    }

    override fun hashCode(): Int {
        return 31 * nombre.hashCode() + categoria.hashCode()
    }

    override fun toString(): String {
        return "$nombre (${categoria.name.lowercase()})"
    }
}
