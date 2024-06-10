package com.example.cuartopracticomoviles.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.models.Libros
import com.example.cuartopracticomoviles.repositories.BookRepository

class LibrosPorGeneroViewModel: ViewModel(){
    private val _librosPorGenero: MutableLiveData<Libros> by lazy {
        MutableLiveData<Libros>(Libros())
    }
    val librosPorGenero: MutableLiveData<Libros> get() = _librosPorGenero


    fun fetchLibrosPorGenero(idGenero: Int) {
        BookRepository.getBooksList(
            success = { libros ->
                libros?.let {
                   /* it.forEach { libro ->
                        Log.d("LibrosPorGeneroViewModel", "Géneros del libro ${libro.nombre}: ${libro.generos?.joinToString { it.nombre }}")
                    }*/
                    _librosPorGenero.value = ArrayList(it.filter { libro -> libro.generos?.any { genero -> genero.id == idGenero } == true })
                    Log.d("LibrosPorGeneroViewModel", "Libros filtrados: ${_librosPorGenero.value}")
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}