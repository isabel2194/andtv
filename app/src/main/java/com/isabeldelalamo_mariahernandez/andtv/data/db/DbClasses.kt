package com.isabeldelalamo_mariahernandez.andtv.data.db


class Usuario(var map: MutableMap<String, Any?>){
    var _id:Long by map
    var email:String by map
    var password:String by map
    var peliculasFavoritasID:List<String> by map

    constructor(email:String, password:String, peliculasFavoritasID: List<String>) : this(HashMap()){
        this.email=email
        this.password=password
        this.peliculasFavoritasID=peliculasFavoritasID
    }
}