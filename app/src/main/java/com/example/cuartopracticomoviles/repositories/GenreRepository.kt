package com.example.cuartopracticomoviles.repositories

import com.example.cuartopracticomoviles.api.APILibraryService
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
                val genreList = response.body()
                success(genreList)
            }

            override fun onFailure(res: Call<Generos>, t: Throwable) {
                failure(t)
            }
        })
    }
}