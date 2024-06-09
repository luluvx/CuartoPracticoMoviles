package com.example.cuartopracticomoviles.repositories

import com.example.cuartopracticomoviles.api.APILibraryService
import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Generos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GenreRepository {
    fun getGenreList(success: (Generos?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.getGenres().enqueue(object : Callback<Generos> {
            override fun onResponse(res: Call<Generos>, response: Response<Generos>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Generos>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun insertGenre(
        genero: Genero,
        success: (Genero) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.insertGenre(genero).enqueue(object : Callback<Genero> {
            override fun onResponse(res: Call<Genero>, response: Response<Genero>) {
                val objGenero = response.body()
                success(objGenero!!)
            }

            override fun onFailure(res: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun getGenreById(id: Int, success: (Genero?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.getGenreById(id).enqueue(object : Callback<Genero?> {
            override fun onResponse(res: Call<Genero?>, response: Response<Genero?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Genero?>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun updateGenre(
        genero: Genero,
        success: (Genero) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.updateGenre(genero, genero.id!!).enqueue(object : Callback<Genero> {
            override fun onResponse(res: Call<Genero>, response: Response<Genero>) {
                val objGenero = response.body()
                success(objGenero!!)
            }

            override fun onFailure(res: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun deleteGenre(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibraryService =
            retrofit.create(APILibraryService::class.java)
        service.deleteGenre(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}