package com.example.boruto_compose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boruto_compose.util.Constant.NINJA_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = NINJA_DATABASE_TABLE)
data class Ninja(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
){
    fun toNinjaRemoteKey(id : Int, prevPage : Int?, nextPage : Int?) : NinjaRemoteKeys{
        return NinjaRemoteKeys(
            id = id,
            prevPage = prevPage,
            nextPage = nextPage
        )
    }
}
