package com.example.boruto_compose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boruto_compose.util.Constant.NINJA_DATABASE_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = NINJA_DATABASE_TABLE)
data class Ninja(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("about")
    val about: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("power")
    val power: Int,
    @SerializedName("month")
    val month: String,
    @SerializedName("day")
    val day: String,
    @SerializedName("family")
    val family: List<String>,
    @SerializedName("abilities")
    val abilities: List<String>,
    @SerializedName("natureTypes")
    val natureTypes: List<String>
) {
    fun toNinjaRemoteKey(id: Int, prevPage: Int?, nextPage: Int?, lastUpdated : Long?): NinjaRemoteKeys {
        return NinjaRemoteKeys(
            id = id,
            prevPage = prevPage,
            nextPage = nextPage,
            lastUpdated = lastUpdated
        )
    }
}
