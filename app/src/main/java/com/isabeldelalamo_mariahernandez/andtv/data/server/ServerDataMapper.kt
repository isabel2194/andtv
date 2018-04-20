package com.danimeana.weatherapp.data.server

import com.danimeana.weatherapp.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.danimeana.weatherapp.model.Forecast as ModelForecast

class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult): ForecastList =
            ForecastList(id = zipCode,
                    city = forecast.city.name,
                    country = forecast.city.country,
                    dailyForecast = convertForecastListToDomain(forecast.list))

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(date = forecast.dt,
                description = forecast.weather[0].description,
                high = forecast.temp.max.toInt(),
                low = forecast.temp.min.toInt())
    }


}