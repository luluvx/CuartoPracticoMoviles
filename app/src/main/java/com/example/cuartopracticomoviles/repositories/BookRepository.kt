package com.example.cuartopracticomoviles.repositories

import com.example.cuartopracticomoviles.api.APILibraryService
import com.example.cuartopracticomoviles.models.Generos
import com.example.cuartopracticomoviles.models.Libro
import com.example.cuartopracticomoviles.models.Libros
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object BookRepository {

    fun getBooksList(success: (Libros?) -> Unit, failure:(Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.getBooks().enqueue(object : Callback<Libros> {
            override fun onResponse(res: Call<Libros>, response: Response<Libros>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Libros>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun insertBook(
        libro: Libro,
        success: (Libro) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.insertBook(libro).enqueue(object : Callback<Libro> {
            override fun onResponse(res: Call<Libro>, response: Response<Libro>) {
                val objLibro = response.body()
                success(objLibro!!)
            }

            override fun onFailure(res: Call<Libro>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun getBookById(id: Int, success: (Libro?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.getBookById(id).enqueue(object : Callback<Libro?> {
            override fun onResponse(res: Call<Libro?>, response: Response<Libro?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Libro?>, t: Throwable) {
                failure(t)
            }
        })

    }
    fun updateBook(
        libro: Libro,
        success: (Libro) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        val libroId =libro.id ?: 0
        service.updateBook(libro, libroId).enqueue(object : Callback<Libro> {
            override fun onResponse(res: Call<Libro>, response: Response<Libro>) {
                val objLibro = response.body()
                success(objLibro!!)
            }

            override fun onFailure(res: Call<Libro>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun deleteBook(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.deleteBook(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun getBookGenres(
        libroId: Int,
        success: (Generos) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        getBooksList(
            success = { libros ->
                libros?.let {
                    val libro = it.find { libro -> libro.id == libroId }
                    libro?.let {
                        success(libro.generos ?: Generos())
                    } ?: failure(Throwable("Libro no encontrado"))
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}