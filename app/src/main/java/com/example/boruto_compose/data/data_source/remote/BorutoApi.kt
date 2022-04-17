package com.example.boruto_compose.data.data_source.remote

import com.example.boruto_compose.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET("boruto/ninjas")
    fun getNinjas(
        @Query("page") page : Int = 1
    ) : ApiResponse

    @GET("search/ninjas")
    fun searchNinjas(
        @Query("query") query : String
    ) : ApiResponse

}