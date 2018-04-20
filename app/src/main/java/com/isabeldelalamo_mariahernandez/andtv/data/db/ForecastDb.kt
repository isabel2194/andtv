package com.danimeana.weatherapp.data.db

import com.danimeana.weatherapp.data.datasources.ForecastDataSource
import com.danimeana.weatherapp.model.ForecastList
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb : ForecastDataSource {

    private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance
    private val dbDataMapper: DbDataMapper = DbDataMapper()

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyForecastSelect = "${DayForecastTable.CITY_ID} = {id} AND ${DayForecastTable.DATE} >= {date}"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereArgs(dailyForecastSelect, "id" to zipCode, "date" to date.toString())
                .parseList(object : MapRowParser<DayForecast> {
                    override fun parseRow(columns: Map<String, Any?>) = DayForecast(columns.toMutableMap())
                })

        val citySelect = "${CityForecastTable.ID} = ?"
        val city = select(CityForecastTable.NAME)
                .whereSimple(citySelect, zipCode.toString())
                .parseOpt(object : MapRowParser<CityForecast> {
                    override fun parseRow(columns: Map<String, Any?>) = CityForecast(columns.toMutableMap(), dailyForecast)
                })

        if (city != null) dbDataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        execSQL("delete from ${CityForecastTable.NAME}")
        execSQL("delete from ${DayForecastTable.NAME}")

        with(dbDataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *(mapToPairArray(map)))
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *(mapToPairArray(it.map)))
            }
        }
    }

    private fun mapToPairArray(map: Map<String, Any?>): Array<Pair<String, Any>> {
        return map.map { Pair(it.key, it.value!!) }.toTypedArray()
    }




}