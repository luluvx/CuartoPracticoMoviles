package com.example.cuartopracticomoviles.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.repositories.GenreRepository

class AgregarGeneroViewModel: ViewModel(){
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: MutableLiveData<Boolean> get() = _closeActivity
    fun agregarGenero(genero: Genero) {
        GenreRepository.insertGenre(genero,
            success = {
                _closeActivity.value = true
            },
            failure = {
                it.printStackTrace()
            })


    }
}