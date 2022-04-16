package com.example.boruto_compose.data.data_source.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boruto_compose.domain.model.NinjaRemoteKey
import com.example.boruto_compose.util.Constant.NINJA_REMOTE_KEY_DATABASE_TABLE

@Dao
interface NinjaRemoteKeyDao {

    @Query("SELECT * FROM $NINJA_REMOTE_KEY_DATABASE_TABLE WHERE id = :id")
    suspend fun getNinjaRemoteKey(id : Int) : NinjaRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNinjaRemoteKeys(ninjaRemoteKeys : List<NinjaRemoteKey>)

    @Query("DELETE FROM $NINJA_REMOTE_KEY_DATABASE_TABLE")
    suspend fun deleteAllNinjaRemoteKeys()
}