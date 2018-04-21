package com.isabeldelalamo_mariahernandez.andtv.data.datasources

import com.isabeldelalamo_mariahernandez.andtv.data.db.UsuarioDb
import com.isabeldelalamo_mariahernandez.andtv.data.server.CategoriesResult
import com.isabeldelalamo_mariahernandez.andtv.data.server.FilmServer

object CategoriesProvider {

    private val SOURCE = FilmServer()

    fun getCategories(): CategoriesResult{
        return SOURCE.requestCategories()
    }
}