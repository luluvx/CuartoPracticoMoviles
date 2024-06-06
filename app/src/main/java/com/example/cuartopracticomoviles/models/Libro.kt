package com.example.cuartopracticomoviles.models

import java.io.Serializable

typealias Libros = ArrayList<Libro>

data class Libro (
    var nombre: String,
    var autor: String,
    var editorial: String,
    var imagen: String,
    var sinopsis: String,
    var isbn: String,
    var calificacion: Int,

):Serializable{
    var id: Int? = null
    var generos: List<Genero> = emptyList()
    var createdAt: String? = null
    var updatedAt: String? = null

}
