package com.example.boruto_compose.di

import androidx.paging.ExperimentalPagingApi
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import com.example.boruto_compose.data.data_source.remote.BorutoApi
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSource
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSourceImpl
import com.example.boruto_compose.util.Constant.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .readTimeout(15, TimeUnit.MINUTES)
        .connectTimeout(15, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient
    ): Retrofit {
        val mediaType = "application/json"
            .toMediaType()

        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(mediaType))
            .build()
    }

    @Provides
    @Singleton
    fun providesBorutoApi(
        retrofit: Retrofit
    ): BorutoApi = retrofit.create(BorutoApi::class.java)

    @Provides
    @Singleton
    fun providesBorutoRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDatabase: BorutoDatabase
    ): BorutoRemoteDataSource = BorutoRemoteDataSourceImpl(
        borutoApi = borutoApi,
        borutoDatabase = borutoDatabase
    )
}