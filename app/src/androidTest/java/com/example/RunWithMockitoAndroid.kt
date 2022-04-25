package com.example

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class RunWithMockitoAndroid {

    lateinit var borutoDatabase: BorutoDatabase

    @Before
    fun setUp() {
        println("test")
        borutoDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BorutoDatabase::class.java
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        borutoDatabase.close()
    }
}