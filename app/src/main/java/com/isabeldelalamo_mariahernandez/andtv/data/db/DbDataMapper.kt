package com.isabeldelalamo_mariahernandez.andtv.data.db

<<<<<<< HEAD
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario

class DbDataMapper {

    fun convertToDomain(usuario: Usuario) = with(usuario) {
        Usuario(email, nombre, password, peliculasFavoritasID)
    }


    fun convertFromDomain(usuario: Usuario) = with(usuario) {
        Usuario(email, nombre, password, peliculasFavoritasID)
=======
import com.isabeldelalamo_mariahernandez.andtv.model.Forecast
import com.isabeldelalamo_mariahernandez.andtv.model.ForecastList

class DbDataMapper {
    fun convertToDomain(cityForecast: CityForecast) = with(cityForecast) {
        val daily = dailyForecast.map { convertDayForecastToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    private fun convertDayForecastToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date, description, high, low)
    }

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayForecastFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayForecastFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, cityId)
>>>>>>> origin/master
    }
}