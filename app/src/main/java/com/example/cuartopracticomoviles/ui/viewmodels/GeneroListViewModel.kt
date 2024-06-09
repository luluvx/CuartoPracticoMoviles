package com.example.cuartopracticomoviles.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Generos
import com.example.cuartopracticomoviles.repositories.GenreRepository

class GeneroListViewModel: ViewModel(){
    private val _generoList: MutableLiveData<Generos> by lazy {
        MutableLiveData<Generos>(Generos())
    }
    val generoList: MutableLiveData<Generos> get() = _generoList
    fun fetchGeneroList(){
        GenreRepository.getGenreList(
            success = { generos ->
                generos?.let {
                    _generoList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }

    fun deleteGenero(idGenero: Int?) {
        GenreRepository.deleteGenre(idGenero!!,
            success = {
                fetchGeneroList()
            },
            failure = {
                it.printStackTrace()
            }
        )
    }


}