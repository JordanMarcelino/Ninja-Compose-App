package com.example.boruto_compose.data.data_source.local.db

import androidx.room.TypeConverter
import java.lang.StringBuilder

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val sb = StringBuilder()

        list.forEach {
            sb.append(it).append(separator)
        }

        sb.setLength(sb.length - separator.length)

        return sb.toString()
    }

    @TypeConverter
    fun convertStringToList(list : String) : List<String>{
        return list.split(separator)
    }

}