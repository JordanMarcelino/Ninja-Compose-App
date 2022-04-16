package com.example.boruto_compose.data.data_source.local.db

import androidx.room.RoomDatabase
import com.example.boruto_compose.data.data_source.local.db.dao.NinjaDao


abstract class BorutoDatabase : RoomDatabase(){
    abstract fun getNinjaDao() : NinjaDao
}