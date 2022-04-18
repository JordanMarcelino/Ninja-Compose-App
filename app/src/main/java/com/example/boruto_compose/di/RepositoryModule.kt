package com.example.boruto_compose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.paging.ExperimentalPagingApi
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSource
import com.example.boruto_compose.data.repository.BorutoRepositoryImpl
import com.example.boruto_compose.data.repository.DataStoreRepositoryImpl
import com.example.boruto_compose.data.repository.ResourceProviderImpl
import com.example.boruto_compose.domain.repository.BorutoRepository
import com.example.boruto_compose.domain.repository.DataStoreRepository
import com.example.boruto_compose.domain.repository.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesResourceProvider(
        @ApplicationContext context: Context
    ): ResourceProvider = ResourceProviderImpl(context)

    @Provides
    @Singleton
    fun providesDataStoreRepository(
        dataStore: DataStore<Preferences>
    ): DataStoreRepository = DataStoreRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun providesBorutoRepository(
        borutoRemoteDataSource: BorutoRemoteDataSource,
        borutoDatabase: BorutoDatabase
    ): BorutoRepository = BorutoRepositoryImpl(
        borutoRemoteDataSource = borutoRemoteDataSource,
        borutoDatabase = borutoDatabase
    )
}