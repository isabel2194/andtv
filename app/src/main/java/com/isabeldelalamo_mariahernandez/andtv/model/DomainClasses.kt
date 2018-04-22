package com.isabeldelalamo_mariahernandez.andtv.model

import com.isabeldelalamo_mariahernandez.andtv.data.datasources.CategoriesProvider
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.FilmProvider
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.UsuarioProvider
import java.util.*


data class Usuario(val email:String, val password:String, val peliculasFavoritasID:MutableList<Int>) {
    companion object {
        fun getUserByEmail(em:String) = UsuarioProvider.getUserByEmail(em)
        fun saveUsuario(em:String, pass:String) = UsuarioProvider.saveUsuario(em, pass)
        fun addFavoriteFilm(idFilm:Int,email:String) = UsuarioProvider.addFavoriteFilm(idFilm,email)
    }
}

data class Category(val id:String, val name:String) {
    companion object {
        fun requestCategories() = CategoriesProvider.getCategories()
    }
}

data class Film(val adult:Boolean, val backdrop_path:String, val genre_ids:List<Int>,
                val id:Int, val original_language:String, val original_title:String,
                val overview:String, val release_date:String, val poster_path:String,
                val popularity:Double, val title:String, val video:Boolean, val vote_average:Double,
                val vote_count:Int) {
    companion object {
        fun getFilmsByCategory(id: String) = FilmProvider.getFilmsByCategory(id)
    }
}
