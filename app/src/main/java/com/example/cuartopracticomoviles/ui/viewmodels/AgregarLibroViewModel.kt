package com.example.cuartopracticomoviles.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.repositories.BookRepository

class AgregarLibroViewModel: ViewModel(){
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: MutableLiveData<Boolean> get() = _closeActivity

    fun guardarLibro(libroGuardar: Libro) {
        BookRepository.insertBook(libroGuardar,
            success = {
                      _closeActivity.value = true

            },
            failure = {
                it.printStackTrace()
            })



    }
}