package com.prempal.teachmintassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.prempal.teachmintassignment.data.MainPagingSource
import com.prempal.teachmintassignment.data.local.MainDao
import com.prempal.teachmintassignment.data.remote.MainService
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(
    private val api: MainService,
    private val dao: MainDao
) : MainRepository {
    override fun fetchRemoteRepositories(
        query: String,
    ) = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { MainPagingSource(api, query, dao) }
    ).flow

}
