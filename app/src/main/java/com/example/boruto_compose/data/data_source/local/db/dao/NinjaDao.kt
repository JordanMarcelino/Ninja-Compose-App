package com.example.boruto_compose.data.data_source.local.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.util.Constant.NINJA_DATABASE_TABLE

@Dao
interface NinjaDao {

    @Query("SELECT * FROM $NINJA_DATABASE_TABLE ORDER BY id ASC")
    fun getNinjas() : PagingSource<Int, Ninja>

    @Query("SELECT * FROM $NINJA_DATABASE_TABLE WHERE id = :ninjaId")
    fun getNinja(ninjaId : Int) : Ninja

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNinjas(ninjas : List<Ninja>)

    @Query("DELETE FROM $NINJA_DATABASE_TABLE")
    suspend fun deleteAllNinjas()
}