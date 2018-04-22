package com.isabeldelalamo_mariahernandez.andtv.data.db

import com.isabeldelalamo_mariahernandez.andtv.model.Usuario as UsuarioModel

class DbDataMapper {

    fun convertToDomain(usuario: Usuario) = with(usuario) {
        var pelis: List<Int> = emptyList()
        if(peliculasFavoritasID.length != 2){
            peliculasFavoritasID = peliculasFavoritasID.substring(1, peliculasFavoritasID.length-1)
            pelis = peliculasFavoritasID.split(",").map { it.trim().toInt() }
        }

        UsuarioModel(email, password, pelis)
    }


    fun convertFromDomain(usuario: UsuarioModel) = with(usuario) {
        Usuario(email, password, peliculasFavoritasID.toString())
    }
}