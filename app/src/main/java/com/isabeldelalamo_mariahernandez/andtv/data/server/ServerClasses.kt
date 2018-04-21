package com.isabeldelalamo_mariahernandez.andtv.data.server


data class ForecastResult(val city: City, val list: List<Forecast>)

data class City(val id: Long, val name: String, val coord: Coordinates, val country: String, val population: Int)

data class Coordinates(val lon: Float, val lat: Float)

data class Forecast(val dt: Long, val temp: Temperature, val pressure: Float, val humidity: Int,
                    val weather: List<Weather>, val speed: Float, val deg: Int, val clouds: Int, val rain: Float)

data class Temperature(val day: Float, val min: Float, val max: Float, val night: Float, val eve: Float, val morn: Float)

data class Weather(val id: Long, val main: String, val description: String, val icon: String)


data class CategoriesResult(val Category:List<Category>)

data class Category(val id:Int, val name:String)

data class FilmResult(val id:Int, val page:Int, val results:List<Film> )

data class Film(val adult:Boolean, val backdrop_path:String, val genre_ids:List<Int>,
                val id:Int, val original_language:String, val original_title:String,
                val overview:String, val release_date:String, val poster_path:String,
                val popularity:Double, val title:String, val video:Boolean, val vote_average:Double,
                val vote_count:Int)