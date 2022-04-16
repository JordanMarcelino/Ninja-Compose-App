package com.example.boruto_compose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.boruto_compose.data.repository.DataStoreRepositoryImpl
import com.example.boruto_compose.data.repository.ResourceProviderImpl
import com.example.boruto_compose.domain.repository.DataStoreRepository
import com.example.boruto_compose.domain.repository.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesResourceProvider(
        @ApplicationContext context : Context
    ) : ResourceProvider = ResourceProviderImpl(context)

    @Provides
    @Singleton
    fun providesDataStoreRepository(
        dataStore : DataStore<Preferences>
    ) : DataStoreRepository = DataStoreRepositoryImpl(dataStore)

}