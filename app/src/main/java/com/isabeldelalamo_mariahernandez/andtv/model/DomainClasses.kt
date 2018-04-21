package com.isabeldelalamo_mariahernandez.andtv.model

import com.isabeldelalamo_mariahernandez.andtv.data.datasources.ForecastProvider
import java.text.DateFormat
import java.util.*

data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>) {

    companion object {
        fun requestForecastByZipCode(zipCode: Long) = ForecastProvider.requestForecastByZipCode(zipCode)
    }
}

data class Forecast(val date: Long, val description: String, val high: Int, val low: Int) {
    fun dateToString(): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
<<<<<<< HEAD
=======
}


data class Usuario(val email:String, val nombre:String, val password:String, val peliculasFavoritasID:List<Int>) {
}

data class Film(val title:String, val description:String val coverPage:String) {

}

data class Category(val name:String, val id:String){

>>>>>>> 8fcb077c2909e0a393caf25cfd64f56a390cbd9b
}