package com.example.cuartopracticomoviles.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.repositories.BookRepository

class EditarLibroViewModel: ViewModel(){
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: MutableLiveData<Boolean> get() = _closeActivity
    private val _libro = MutableLiveData<Libro?>()
    val libro: LiveData<Libro?> get() = _libro

    fun loadLibro(idLibro: Int) {
        BookRepository.getBookById(idLibro,
            success = {
                _libro.value = it
            },
            failure = {
                it.printStackTrace()
            }
        )

    }

    fun guardarLibroEditado(
        idLibro: Int,
        nombreLibro: String,
        nombreAutor: String,
        editorialLibro: String,
        imagenLibro: String,
        sinopsisLibro: String,
        isbnLibro: String,
        calificacionLibro: Int
    ) {
        val libroEditado = Libro(
            nombre = nombreLibro,
            autor = nombreAutor,
            editorial = editorialLibro,
            imagen = imagenLibro,
            sinopsis = sinopsisLibro,
            isbn = isbnLibro,
            calificacion = calificacionLibro
        )
        if (idLibro != 0) {
            libroEditado.id = idLibro
            BookRepository.updateBook(libroEditado,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        } else {
            BookRepository.insertBook(libroEditado,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }
}