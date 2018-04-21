package com.isabeldelalamo_mariahernandez.andtv.data.db


class Usuario(var map: MutableMap<String, Any?>){
    var _id:Long by map
    var nombre: String by map
    var email:String by map
    var password:String by map

    constructor(nombre:String, email:String,password:String) : this(HashMap()){
        this.nombre = nombre
        this.email=email
        this.password=password
    }

}