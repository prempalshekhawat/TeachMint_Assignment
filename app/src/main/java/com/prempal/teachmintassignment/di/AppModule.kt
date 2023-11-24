package com.prempal.teachmintassignment.di

import com.google.gson.GsonBuilder
import com.prempal.teachmintassignment.network.ApiService
import com.prempal.teachmintassignment.network.GitHubApiService
import com.prempal.teachmintassignment.repository.DefaultGitHubRepository
import com.prempal.teachmintassignment.repository.DefaultItemRepository
import com.prempal.teachmintassignment.repository.GitHubRepository
import com.prempal.teachmintassignment.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    var gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
//            .baseUrl("https://www.howtodoandroid.com/apis/")
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideItemRepository(apiService: ApiService): MovieRepository {
        return DefaultItemRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideGitHubApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(apiService: GitHubApiService): GitHubRepository {
        return DefaultGitHubRepository(apiService)
    }

}