package com.example.boruto_compose.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("prevPage")
    val prevPage: Int? = null,
    @SerializedName("nextPage")
    val nextPage: Int? = null,
    @SerializedName("ninjas")
    val ninjas: List<Ninja> = emptyList(),
    @SerializedName("lastUpdated")
    val lastUpdated : Long? = null,
)
