package com.isabeldelalamo_mariahernandez.andtv.data.datasources

import com.isabeldelalamo_mariahernandez.andtv.data.server.CategoriesResult
import com.isabeldelalamo_mariahernandez.andtv.data.server.Film
import com.isabeldelalamo_mariahernandez.andtv.data.server.FilmResult

interface FilmDataSource {

    fun requestFilms(categoryID: String): FilmResult

    fun requestCategories(): CategoriesResult

    fun requestFilmById(filmID:String): Film
}