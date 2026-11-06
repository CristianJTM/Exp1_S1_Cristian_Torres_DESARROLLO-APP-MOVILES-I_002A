package modelo

import utils.CORREO_POR_DEFECTO

class Cliente(
    nombre: String,
    contacto: String,
    val correo: String?,
    val zona: String
) : Usuario(nombre, contacto) {

    val correoMostrado: String
        get() = correo ?: CORREO_POR_DEFECTO

    override fun describir(): String {
        return "Cliente: $nombre - Teléfono: $contacto - Correo: $correoMostrado - Zona: $zona"
    }
}
