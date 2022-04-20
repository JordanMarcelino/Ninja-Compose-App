package com.example.boruto_compose.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.boruto_compose.util.Constant.DARK_VIBRANT
import com.example.boruto_compose.util.Constant.ON_DARK_VIBRANT
import com.example.boruto_compose.util.Constant.VIBRANT

object PaletteGenerator {

    suspend fun parseImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest
            .Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        val result = loader.execute(request)

        return if (result is SuccessResult) {
            (result.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    fun extractColorFromBitmap(
        bitmap: Bitmap
    ): Map<String, String> {
        return mapOf(
            VIBRANT to parseColorSwatch(Palette.from(bitmap).generate().vibrantSwatch),
            DARK_VIBRANT to parseColorSwatch(Palette.from(bitmap).generate().darkVibrantSwatch),
            ON_DARK_VIBRANT to parseBodyTextColor(
                Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
            )
        )
    }

    private fun parseColorSwatch(
        color: Palette.Swatch?
    ): String {
        if (color == null) return "#000000"

        return "#${Integer.toHexString(color.rgb)}"
    }

    private fun parseBodyTextColor(
        color: Int?
    ): String {
        if (color == null) return "#FFFFFF"

        return "#${Integer.toHexString(color)}"
    }
}