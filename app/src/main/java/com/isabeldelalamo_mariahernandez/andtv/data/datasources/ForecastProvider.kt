package com.danimeana.weatherapp.data.datasources

import com.danimeana.weatherapp.data.db.ForecastDb
import com.danimeana.weatherapp.data.server.ForecastServer
import com.danimeana.weatherapp.model.ForecastList

object ForecastProvider {
    private const val DAYS = 5
    private const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
    private val SOURCES = listOf(ForecastDb(), ForecastServer())

    fun requestForecastByZipCode(zipCode: Long): ForecastList? {
        for (source in SOURCES) {
            val result = source.requestForecastByZipCode(zipCode, todayTimeSpan())
            if (result != null && result.dailyForecast.size >= DAYS)
                return result
        }
        return null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

}