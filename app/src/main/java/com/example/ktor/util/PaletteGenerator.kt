package com.example.ktor.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.ktor.util.Constants.DARKVIDRANT
import com.example.ktor.util.Constants.ONDARKVIBRANT
import com.example.ktor.util.Constants.VIBRANT

object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {

        val loader = ImageLoader(context = context)

        val request = ImageRequest.Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()

        val imageResult = loader.execute(request = request)

        return if(imageResult is SuccessResult){
            (imageResult.drawable as BitmapDrawable).bitmap
        }else{
            null
        }

    }

    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String>{

        return mapOf(
            VIBRANT to parseColorSwatch(color = Palette.from(bitmap).generate().vibrantSwatch),
            DARKVIDRANT to parseColorSwatch(color = Palette.from(bitmap).generate().darkVibrantSwatch),
            ONDARKVIBRANT to parseBodyColor(color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor),
        )

    }

    private fun parseColorSwatch(color: Palette.Swatch?): String {
        return if(color != null){
            val parsedColor = Integer.toHexString(color.rgb)
            return "#$parsedColor"
        }else{
            return "#000000"
        }
    }

    private fun parseBodyColor(color: Int?): String{
        return if(color != null){
            val parsedColor = Integer.toHexString(color)
            return "#$parsedColor"
        }else{
            return "#FFFFFF"
        }
    }

}