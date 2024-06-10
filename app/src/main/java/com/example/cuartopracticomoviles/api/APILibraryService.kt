package com.example.cuartopracticomoviles.api




import com.example.cuartopracticomoviles.models.Genero
import com.example.cuartopracticomoviles.models.Libro

import com.example.cuartopracticomoviles.models.Generos
import com.example.cuartopracticomoviles.models.Pivot

import com.example.cuartopracticomoviles.models.Libros

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APILibraryService {


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
    @DELETE("libros/{id}")
    fun deleteBook(
        @Path("id") id: Int
    ): Call<Void>

    @GET("generos")
    fun getGenres(): Call<Generos>


    @GET("generos/{id}")
    fun getGenreById(
        @Path("id") id: Int
    ): Call<Genero?>
    @POST("generos")
    fun insertGenre(
        @Body genero: Genero
    ): Call<Genero>

    @PUT("generos/{id}")
    fun updateGenre(
        @Body genero: Genero,
        @Path("id") id: Int
    ): Call<Genero>
    @DELETE("generos/{id}")
    fun deleteGenre(
        @Path("id") id: Int
    ): Call<Void>

    @POST("libro-generos")
    fun insertBookGenre(
        @Body pivot: Pivot
    ): Call<Pivot>

}