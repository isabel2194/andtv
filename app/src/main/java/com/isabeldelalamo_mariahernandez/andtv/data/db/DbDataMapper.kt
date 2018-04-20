package com.isabeldelalamo_mariahernandez.andtv.data.db

import com.danimeana.weatherapp.model.Forecast
import com.danimeana.weatherapp.model.ForecastList
import com.danimeana.weatherapp.model.Usuario

class DbDataMapper {
    fun convertToDomain(usuario: Usuario) = with(usuario) {
        val daily = usuario.map { convertUsuarioToDomain(it) }
        Usuario(_id, email, name, daily)
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