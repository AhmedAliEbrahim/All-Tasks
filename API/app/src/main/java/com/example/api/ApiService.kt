package com.example.api
import retrofit2.http.GET

interface ApiService {

    @GET("albums")
    suspend fun getAllAlbumList():List<Album>
}