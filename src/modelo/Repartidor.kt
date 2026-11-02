package modelo

class Repartidor(
    nombre: String,
    contacto: String,
    val zona: String,
    val distanciaKm: Double,
    var disponible: Boolean = true
) : Usuario(nombre, contacto) {

    fun entregarPedido(pedido: Pedido) {
        pedido.cambiarEstado(EstadoPedido.EN_CAMINO)
        pedido.asignarRepartidor(this)
        disponible = false
        println("$nombre está entregando el pedido #${pedido.id}.")
    }

    override fun describir(): String {
        return "Repartidor: $nombre - Teléfono: $contacto - Zona: $zona - Distancia: $distanciaKm km"
    }
}
