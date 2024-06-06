package com.example.cuartopracticomoviles.api




import com.example.cuartopracticomoviles.models.Libro

import com.example.cuartopracticomoviles.models.Generos

import com.example.cuartopracticomoviles.models.Libros

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APILibraryService {
    @GET("generos")
    fun getGeneros(): Call<Generos>

    @GET("libros")
    fun getBooks(): Call<Libros>

    @GET("libros/{id}")
    fun getBookById(
        @Path("id") id: Int
    ): Call<Libro?>

    @POST("libros")
    fun insertBook(
        @Body libro: Libro
    ): Call<Libro>

    @PUT("libros/{id}")
    fun updateBook(
        @Body libro: Libro,
        @Path("id") id: Int
    ): Call<Libro>


}