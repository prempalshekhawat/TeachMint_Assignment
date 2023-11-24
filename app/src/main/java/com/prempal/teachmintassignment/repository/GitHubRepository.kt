package com.prempal.teachmintassignment.repository

import com.prempal.teachmintassignment.data.GitSearchResponse
import com.prempal.teachmintassignment.network.GitHubApiService
import javax.inject.Inject

interface GitHubRepository {
    suspend fun searchRepos(query: String, page: Int, perPage: Int): GitSearchResponse
}

class DefaultGitHubRepository @Inject constructor(private val apiService: GitHubApiService) :
    GitHubRepository {

    override suspend fun searchRepos(query: String, page: Int, perPage: Int): GitSearchResponse {
        return apiService.searchRepos(query, page, perPage)
    }
}