package com.prempal.teachmintassignment.data.repository

import androidx.paging.PagingData
import com.prempal.teachmintassignment.data.remote.response.MainResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun fetchRemoteRepositories(
        query: String,
    ): Flow<PagingData<MainResponse.Item>>

}

