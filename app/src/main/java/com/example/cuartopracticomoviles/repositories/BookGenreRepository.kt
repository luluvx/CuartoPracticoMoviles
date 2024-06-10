package com.example.cuartopracticomoviles.repositories

import com.example.cuartopracticomoviles.api.APILibraryService
import com.example.cuartopracticomoviles.models.Pivot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object BookGenreRepository {
fun insertBookGenre(
    pivot: Pivot,
    success: (Pivot) -> Unit,
    failure: (Throwable) -> Unit
) {
    val retrofit = RetrofitRepository.getRetrofitInstance()

    val service: APILibraryService =
        retrofit.create(APILibraryService::class.java)
    service.insertBookGenre(pivot).enqueue(object : Callback<Pivot> {
        override fun onResponse(res: Call<Pivot>, response: Response<Pivot>) {
            val objPivot = response.body()
            success(objPivot!!)
        }

        override fun onFailure(res: Call<Pivot>, t: Throwable) {
            failure(t)
        }
    })
}}