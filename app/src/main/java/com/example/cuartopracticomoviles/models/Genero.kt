package com.example.cuartopracticomoviles.models

typealias Generos = ArrayList<Genero>

data class Genero (
    var nombre: String,
    var pivot: Pivot
){
    var id: Int? = null
    var createdAt: String? = null
    var updatedAt: String? = null
}