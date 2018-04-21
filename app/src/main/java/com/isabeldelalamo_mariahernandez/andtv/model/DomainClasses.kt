package com.isabeldelalamo_mariahernandez.andtv.model

import com.isabeldelalamo_mariahernandez.andtv.data.datasources.ForecastProvider
import java.text.DateFormat
import java.util.*


data class Usuario(val email:String, val password:String, val peliculasFavoritasID:List<Int>) {
}
