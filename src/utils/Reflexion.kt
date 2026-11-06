package utils

object Reflexion {
    fun listarMiembros(clase: Class<*>) {
        println("Clase: ${clase.simpleName}")

        println("Métodos:")
        clase.methods
            .map { it.name }
            .distinct()
            .sorted()
            .forEach { println("  - $it") }

        val campos: MutableList<String> = mutableListOf()
        var actual: Class<*>? = clase
        while (actual != null && actual != Any::class.java) {
            actual.declaredFields.forEach { campos.add(it.name) }
            actual = actual.superclass
        }

        println("Propiedades/campos:")
        campos.distinct().sorted().forEach { println("  - $it") }
    }
}
