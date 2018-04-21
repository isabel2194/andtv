package com.isabeldelalamo_mariahernandez.andtv.data.server


data class CategoriesResult(val Category:List<Category>)

data class Category(val id:Int, val name:String)

data class FilmResult(val id:Int, val page:Int, val results:List<Film> )

data class Film(val adult:Boolean, val backdrop_path:String, val genre_ids:List<Int>,
                val id:Int, val original_language:String, val original_title:String,
                val overview:String, val release_date:String, val poster_path:String,
                val popularity:Double, val title:String, val video:Boolean, val vote_average:Double,
                val vote_count:Int)

data class UsuarioResult( val email:String, val password:String, val peliculasFavoritas:List<Int>)