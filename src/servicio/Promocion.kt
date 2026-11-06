package servicio

import utils.DIAS_PROMOCION
import java.time.LocalDate

object Promocion {
    private val hoy: LocalDate = LocalDate.now()
    val inicio: LocalDate = hoy.minusDays(DIAS_PROMOCION.toLong())
    val fin: LocalDate = hoy.plusDays(DIAS_PROMOCION.toLong())

    fun estaActiva(fecha: LocalDate = LocalDate.now()): Boolean {
        return fecha.toEpochDay() in inicio.toEpochDay()..fin.toEpochDay()
    }
}
