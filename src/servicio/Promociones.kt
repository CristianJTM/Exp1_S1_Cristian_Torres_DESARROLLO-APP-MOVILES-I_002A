package servicio

import modelo.Producto

object Promociones {
    fun listarPromocionables(productos: List<Producto>): List<Producto> {
        return productos.filter { it.esPromocionable() }
    }

    fun descripcion(producto: Producto): String {
        return producto.descripcionPromocion() ?: "Sin promoción"
    }
}
