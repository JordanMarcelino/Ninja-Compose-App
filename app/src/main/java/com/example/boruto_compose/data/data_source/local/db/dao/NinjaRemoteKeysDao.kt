package com.example.boruto_compose.data.data_source.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boruto_compose.domain.model.NinjaRemoteKeys
import com.example.boruto_compose.util.Constant.NINJA_REMOTE_KEY_DATABASE_TABLE

@Dao
interface NinjaRemoteKeysDao {

    @Query("SELECT * FROM $NINJA_REMOTE_KEY_DATABASE_TABLE WHERE id = :id")
    suspend fun getNinjaRemoteKeys(id : Int) : NinjaRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNinjaRemoteKeys(ninjaRemoteKeys : List<NinjaRemoteKeys>)

    @Query("DELETE FROM $NINJA_REMOTE_KEY_DATABASE_TABLE")
    suspend fun deleteAllNinjaRemoteKeys()
}