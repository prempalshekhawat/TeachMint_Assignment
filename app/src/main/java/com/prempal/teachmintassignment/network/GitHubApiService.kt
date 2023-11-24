package com.prempal.teachmintassignment.network

import com.prempal.teachmintassignment.data.GitSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    @GET("repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GitSearchResponse
}