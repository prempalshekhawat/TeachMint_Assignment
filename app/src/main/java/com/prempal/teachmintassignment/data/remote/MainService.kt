package com.prempal.teachmintassignment.data.remote

import com.prempal.teachmintassignment.data.remote.response.MainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): MainResponse
}