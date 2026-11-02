package modelo

class Cliente(
    nombre: String,
    contacto: String,
    val correo: String?,
    val zona: String
) : Usuario(nombre, contacto) {

    val correoMostrado: String
        get() = correo ?: "correo@invalido.com"

    override fun describir(): String {
        return "Cliente: $nombre - Teléfono: $contacto - Correo: $correoMostrado - Zona: $zona"
    }
}
