package org.example.project
import androidx.compose.runtime.mutableStateListOf
data class Tarea(val titol: String, var completada: Boolean)

class Tasques {
    private val lista = mutableStateListOf<Tarea>()

    fun afegirTasques(titol: String) {
        if (titol.isNotEmpty()) {
            lista.add(Tarea(titol, false))
        }
    }

    fun canviarEstat(i: Int) {
        if (i in lista.indices) {
            val tasca = lista[i]
            tasca.completada = !tasca.completada
        }
    }

    fun eliminarTasca(i: Int) {
        if (i in lista.indices) {
            lista.removeAt(i)
        }
    }

    fun obtenirLista(): List<Tarea> = lista
}
