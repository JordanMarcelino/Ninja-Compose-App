package com.example.boruto_compose.domain.model

data class ApiResponse(
    val id : Int,
    val message : String? = null,
    val prevPage : Int? = null,
    val nextPage : Int? = null,
    val ninjas : List<Ninja> = emptyList(),
)
