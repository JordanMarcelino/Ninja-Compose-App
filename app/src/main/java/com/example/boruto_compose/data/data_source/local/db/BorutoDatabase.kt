package com.example.boruto_compose.data.data_source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.boruto_compose.data.data_source.local.db.dao.NinjaDao
import com.example.boruto_compose.data.data_source.local.db.dao.NinjaRemoteKeyDao
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.model.NinjaRemoteKey


@Database(
    entities = [
        Ninja::class,
        NinjaRemoteKey::class
    ],
    exportSchema = false,
    version = 1,
)
@TypeConverters(
    DatabaseConverter::class
)
abstract class BorutoDatabase : RoomDatabase(){
    abstract fun getNinjaDao() : NinjaDao
    abstract fun getNinjaRemoteKeyDao() : NinjaRemoteKeyDao
}