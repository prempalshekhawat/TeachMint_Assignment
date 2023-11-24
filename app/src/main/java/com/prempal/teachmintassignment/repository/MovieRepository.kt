package com.prempal.teachmintassignment.repository

import com.prempal.teachmintassignment.data.Movie
import com.prempal.teachmintassignment.network.ApiService
import javax.inject.Inject

interface MovieRepository {
    suspend fun getItems(): List<Movie>
}

class DefaultItemRepository @Inject constructor(private val apiService: ApiService) : MovieRepository {
    override suspend fun getItems(): List<Movie> {
        return apiService.getItems()
    }
}