package com.example.cuartopracticomoviles.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.repositories.BookRepository

class DetalleLibroViewModel : ViewModel(){


    private val _libroObtenido: MutableLiveData<Libro?> by lazy {
        MutableLiveData<Libro?>(null)
    }
    val libroObtenido: LiveData<Libro?> get() = _libroObtenido


    fun loadLibro(idLibro: Int) {
        BookRepository.getBookById(idLibro,
            success = {
                _libroObtenido.value = it
            },
            failure = {
                it.printStackTrace()
            }
        )

    }


}