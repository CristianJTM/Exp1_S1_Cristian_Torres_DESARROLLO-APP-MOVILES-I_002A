package modelo

open class Usuario(
    val nombre: String,
    val contacto: String
) {
    open fun describir(): String {
        return "$nombre - Contacto: $contacto"
    }
}
