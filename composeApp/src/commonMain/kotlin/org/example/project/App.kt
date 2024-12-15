package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ListaTareas()
}

@Composable
fun ListaTareas() {

    var nuevoItem by remember { mutableStateOf("") }
    val tasques = remember { Tasques() }
    val listaTareas = tasques.obtenirLista()

    val tareasPendientes = listaTareas.filter { !it.completada }
    val tareasCompletadas = listaTareas.filter { it.completada }

    Column(Modifier.padding(16.dp)) {
        // TITULO DE LA APLICACION
        Text(
            text = "Lista de tareas",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // INPUT PARA ESCRIBIR LAS TAREAS
        OutlinedTextField(
            value = nuevoItem,
            onValueChange = { nuevoItem = it },
            label = { Text("Nueva tarea") },
            modifier = Modifier.fillMaxWidth()
        )

        // BOTON AGREGAR TAREAS
        Button(
            onClick = {
                if (nuevoItem.isNotBlank()) {
                    tasques.afegirTasques(nuevoItem)
                    nuevoItem = ""
                }
            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Text("Agregar tarea")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CONTENEDOR DONDE ESTAN LAS TAREAS PENDIENTES
        Text(
            text = "Tareas pendientes:",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        tareasPendientes.forEach { tarea ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = tarea.titol,
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = { tasques.canviarEstat(listaTareas.indexOf(tarea)) }) {
                    Text("COMPLETAR")
                }
                Button(onClick = { tasques.eliminarTasca(listaTareas.indexOf(tarea)) }) {
                    Text("BORRAR")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) //sirve para serparar

        Text(
            text = "Tareas completadas: ",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        tareasCompletadas.forEach { tarea ->
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = tarea.titol,
                    style = MaterialTheme.typography.body1.copy(
                        textDecoration = TextDecoration.LineThrough, // con esto tachamos el texto
                    ),
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = { tasques.canviarEstat(listaTareas.indexOf(tarea)) }) {
                    Text("DESMARCAR")
                }
                Button(onClick = { tasques.eliminarTasca(listaTareas.indexOf(tarea)) }) {
                    Text("BORRAR")
                }
            }
        }
    }
}
