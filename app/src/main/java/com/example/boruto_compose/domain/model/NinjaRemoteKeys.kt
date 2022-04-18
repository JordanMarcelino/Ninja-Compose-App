package com.example.boruto_compose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boruto_compose.util.Constant.NINJA_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = NINJA_REMOTE_KEY_DATABASE_TABLE)
data class NinjaRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val prevPage : Int?,
    val nextPage : Int?,
    val lastUpdated : Long? = null
)
