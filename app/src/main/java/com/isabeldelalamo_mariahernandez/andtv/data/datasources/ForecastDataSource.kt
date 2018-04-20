package com.danimeana.weatherapp.data.datasources

import com.danimeana.weatherapp.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}