package com.danimeana.weatherapp.data.db


object CityForecastTable {
    const val NAME = "CityForecast"
    const val ID = "_id"
    const val CITY = "city"
    const val COUNTRY = "country"
}

object DayForecastTable {
    const val NAME = "DayForecast"
    const val ID = "_id"
    const val DATE = "date"
    const val DESCRIPTION = "description"
    const val HIGH = "high"
    const val LOW = "low"
    const val CITY_ID = "cityId"
}

object UsuarioTable {
    const val NAME = "Usuario"
    const val ID = "_id"
    const val NOMBRE = "nombre"
    const val PASSWORD = "password"
    const val PELICULASFAVORITASID = "peliculasFavoritasID"
}