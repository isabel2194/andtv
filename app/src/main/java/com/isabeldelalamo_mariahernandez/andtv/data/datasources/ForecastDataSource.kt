package com.isabeldelalamo_mariahernandez.andtv.data.datasources

import com.isabeldelalamo_mariahernandez.andtv.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}