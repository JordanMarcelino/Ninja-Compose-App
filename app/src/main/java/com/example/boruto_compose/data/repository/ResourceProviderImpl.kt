package com.example.boruto_compose.data.repository

import android.content.Context
import com.example.boruto_compose.domain.repository.ResourceProvider

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    override fun getString(resId: Int, vararg args: Any): String {
        return context.getString(resId, args)
    }
}