package com.example.cuartopracticomoviles.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Libros
import com.example.cuartopracticomoviles.repositories.BookRepository

class MainViewModel: ViewModel(){
    private val _librosList: MutableLiveData<Libros> by lazy {
        MutableLiveData<Libros>(Libros())
    }
    val librosList: MutableLiveData<Libros> get() = _librosList

    fun fetchBooks(){
        BookRepository.getBooksList(
            success = { libros ->
                libros?.let {
                    val librosOrdenados = it.sortedByDescending { libro -> libro.calificacion }
                    _librosList.value = Libros(librosOrdenados)
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}