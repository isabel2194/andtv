package com.isabeldelalamo_mariahernandez.andtv.data.datasources

import com.isabeldelalamo_mariahernandez.andtv.data.server.FilmResult
import com.isabeldelalamo_mariahernandez.andtv.data.server.FilmServer

object FilmProvider {

    private val SOURCE = FilmServer()

    fun getFilmsByCategory(categoryID:String): FilmResult {
        return SOURCE.requestFilms(categoryID)
    }
}