package com.prempal.teachmintassignment.network

import com.prempal.teachmintassignment.data.Movie
import retrofit2.http.GET

interface ApiService {
    @GET("movielist.json")
    suspend fun getItems(): List<Movie>
}