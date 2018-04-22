package com.isabeldelalamo_mariahernandez.andtv.data.server

import com.google.gson.Gson
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.FilmDataSource
import java.net.URL

class FilmServer: FilmDataSource {

    companion object {
        private const val API_KEY = "2df2dfb5659354751f2053d8ffff0b3f"
        private const val COUNTRY_CODE = "es"
        private const val CATEGORIES_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key=${API_KEY}&language=${COUNTRY_CODE}"
    }


    override fun requestFilms(categoryID:String): FilmResult {
        val filmsJsonStr = URL("https://api.themoviedb.org/3/genre/$categoryID/movies?api_key=${API_KEY}&language=${COUNTRY_CODE}&include_adult=false&sort_by=created_at.asc").readText()
        val result = Gson().fromJson(filmsJsonStr, FilmResult::class.java)
        return result
    }

    override fun requestCategories():CategoriesResult{
        var categoriesJsonStr = URL(CATEGORIES_URL).readText()
        var result = Gson().fromJson(categoriesJsonStr,CategoriesResult::class.java)
        return result

    }

    override fun requestFilmById(filmID:String): Film {
        val filmsJsonStr = URL("https://api.themoviedb.org/3/movie/$filmID?api_key=${API_KEY}&language=${COUNTRY_CODE}").readText()
        val result = Gson().fromJson(filmsJsonStr, Film::class.java)
        return result
    }
}