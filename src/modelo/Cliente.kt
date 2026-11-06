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

    operator fun component1(): String = nombre

    operator fun component2(): String = correoMostrado

    operator fun component3(): String = contacto

    override fun describir(): String {
        return "Cliente: $nombre - Teléfono: $contacto - Correo: $correoMostrado - Zona: $zona"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Cliente) {
            return false
        }
        return nombre == other.nombre && correo == other.correo
    }

    override fun hashCode(): Int {
        return 31 * nombre.hashCode() + (correo?.hashCode() ?: 0)
    }
}
