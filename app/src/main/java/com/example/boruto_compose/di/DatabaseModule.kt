package com.example.boruto_compose.di

import android.content.Context
import androidx.room.Room
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import com.example.boruto_compose.util.Constant.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesBorutoDatabase(
        @ApplicationContext context : Context
    ) : BorutoDatabase = Room.databaseBuilder(
        context,
        BorutoDatabase::class.java,
        BORUTO_DATABASE
    ).build()

}