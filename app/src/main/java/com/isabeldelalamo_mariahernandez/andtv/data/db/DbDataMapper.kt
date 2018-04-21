package com.isabeldelalamo_mariahernandez.andtv.data.db

import com.isabeldelalamo_mariahernandez.andtv.model.Forecast
import com.isabeldelalamo_mariahernandez.andtv.model.ForecastList
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario

class DbDataMapper {

    fun convertToDomain(usuario: Usuario) = with(usuario) {
        val daily = usuario.map { convertUsuarioToDomain(it) }
        Usuario(email, nombre, password, peliculasFavoritasID)
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
    }
}