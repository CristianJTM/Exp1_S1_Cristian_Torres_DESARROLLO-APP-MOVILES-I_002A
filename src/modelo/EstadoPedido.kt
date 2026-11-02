package modelo

enum class EstadoPedido(val descripcion: String) {
    EN_PREPARACION("En preparación"),
    LISTO("Listo"),
    EN_CAMINO("En camino")
}
