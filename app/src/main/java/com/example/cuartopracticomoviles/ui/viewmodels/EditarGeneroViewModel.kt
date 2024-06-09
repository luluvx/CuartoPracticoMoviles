package com.example.cuartopracticomoviles.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Generos
import com.example.cuartopracticomoviles.repositories.BookRepository
import com.example.cuartopracticomoviles.repositories.GenreRepository

class EditarGeneroViewModel: ViewModel(){
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: MutableLiveData<Boolean> get() = _closeActivity

    private val _genero = MutableLiveData<Genero?>()
    val genero: LiveData<Genero?> get() = _genero

    fun loadGenero(idGenero: Int) {
        GenreRepository.getGenreById(idGenero,
            success = {
                _genero.value = it
            },
            failure = {
                it.printStackTrace()
            }
        )
        Log.d("EditarGeneroViewModel", "Loading genero with id: $idGenero")

    }
    fun guardarGeneroEditado(idGenero: Int, nombreGenero: String) {
        val generoEditado = Genero(
            nombre = nombreGenero
        )
        if (idGenero != 0) {
            generoEditado.id = idGenero
            GenreRepository.updateGenre(generoEditado,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        } else {
            GenreRepository.insertGenre(generoEditado,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }

    }




}