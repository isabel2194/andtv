package com.isabeldelalamo_mariahernandez.andtv.data.server

import com.isabeldelalamo_mariahernandez.andtv.data.db.ForecastDb
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.ForecastDataSource
import com.isabeldelalamo_mariahernandez.andtv.model.ForecastList
import com.google.gson.Gson
import java.net.URL

class ForecastServer() : ForecastDataSource {

    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "${API_URL}&APPID=${APP_ID}&zip="
        private const val COUNTRY_CODE = "es"
    }

    private val forecastDb: ForecastDb = ForecastDb()
    private val serverDataMapper: ServerDataMapper = ServerDataMapper()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val forecastJsonStr = URL("${COMPLETE_URL}$zipCode,${COUNTRY_CODE}").readText()
        val result = Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
        val converted = serverDataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}