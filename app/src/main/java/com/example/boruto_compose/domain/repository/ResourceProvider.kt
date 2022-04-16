package com.example.boruto_compose.domain.repository

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(
        @StringRes
        resId : Int,
        vararg args : Any
    ) : String

}