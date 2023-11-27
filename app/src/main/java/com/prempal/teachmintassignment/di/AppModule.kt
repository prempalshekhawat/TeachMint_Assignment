package com.prempal.teachmintassignment.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.prempal.teachmintassignment.data.database.MainDatabase
import com.prempal.teachmintassignment.data.local.MainDao
import com.prempal.teachmintassignment.data.remote.MainService
import com.prempal.teachmintassignment.data.repository.MainRepository
import com.prempal.teachmintassignment.data.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideGSON(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = with(OkHttpClient.Builder()) {
        addInterceptor(httpLoggingInterceptor)
    }.build()


    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson, okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }


    @Provides
    @Singleton
    fun provideGitHubApiService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }

    @Singleton
    @Provides
    fun provideMainDatabase(
        application: Application
    ): MainDatabase {
        return Room.databaseBuilder(
            application, MainDatabase::class.java, "main_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideMAinDao(mainDatabase: MainDatabase): MainDao {
        return mainDatabase.mainDao()
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(
        apiService: MainService,
        dao: MainDao
    ): MainRepository {
        return MainRepositoryImpl(apiService, dao)
    }

}