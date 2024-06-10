package com.example.cuartopracticomoviles.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Generos
import com.example.cuartopracticomoviles.models.Pivot
import com.example.cuartopracticomoviles.repositories.BookGenreRepository
import com.example.cuartopracticomoviles.repositories.BookRepository
import com.example.cuartopracticomoviles.repositories.GenreRepository

class AgregarLibroAGeneroViewModel: ViewModel(){
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: MutableLiveData<Boolean> get() = _closeActivity
    private val _generosNoLibro: MutableLiveData<Generos> by lazy {
        MutableLiveData<Generos>(Generos())
    }
    val generosNoLibro: MutableLiveData<Generos> get() = _generosNoLibro

    fun fetchNoGenerosLibro(idLibro: Int) {
        val allGenres = mutableListOf<Genero>()
        val bookGenres = mutableListOf<Genero>()

        GenreRepository.getGenreList(
            success = { generos ->
                generos?.let {
                    allGenres.addAll(it)
                    BookRepository.getBookGenres(
                        libroId = idLibro,
                        success = { libroGeneros ->
                            libroGeneros?.let {
                                bookGenres.addAll(it)
                                val noBookGenres = allGenres.filter { genero -> genero !in bookGenres }
                                _generosNoLibro.value = Generos(noBookGenres)
                            }
                        },
                        failure = {
                            it.printStackTrace()
                        }
                    )
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }

    fun agregarLibroAGenero(idLibro: Int, idGenero: Int?) {
        if (idGenero != null) {
            BookGenreRepository.insertBookGenre(
                Pivot(idLibro, idGenero),
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                }
            )
        }
    }


}