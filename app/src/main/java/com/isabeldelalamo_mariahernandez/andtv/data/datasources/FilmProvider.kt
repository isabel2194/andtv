package com.isabeldelalamo_mariahernandez.andtv.data.datasources

import com.isabeldelalamo_mariahernandez.andtv.data.server.Film
import com.isabeldelalamo_mariahernandez.andtv.data.server.FilmResult
import com.isabeldelalamo_mariahernandez.andtv.data.server.FilmServer

object FilmProvider {

    private val SOURCE = FilmServer()

    fun getFilmsByCategory(categoryID:String): FilmResult {
        return SOURCE.requestFilms(categoryID)
    }

    fun getFilmsById(lista : List<Int>): MutableList<Film>{
        val listaFilm: MutableList<Film> = mutableListOf()
        lista.forEach{
            var film = SOURCE.requestFilmById(it.toString())
            if(film != null)
                listaFilm.add(film)
        }
        return listaFilm
    }
}