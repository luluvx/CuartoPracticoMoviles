package com.example.cuartopracticomoviles.models

import java.io.Serializable

typealias Generos = ArrayList<Genero>

data class Genero (
    var nombre: String,
):Serializable{
    var id: Int? = null
    var pivot: Pivot? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}