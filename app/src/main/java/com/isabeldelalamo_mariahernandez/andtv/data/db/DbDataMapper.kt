package com.isabeldelalamo_mariahernandez.andtv.data.db

import com.isabeldelalamo_mariahernandez.andtv.model.Usuario

class DbDataMapper {

    fun convertToDomain(usuario: Usuario) = with(usuario) {
        Usuario(email, nombre, password, peliculasFavoritasID)
    }


    fun convertFromDomain(usuario: Usuario) = with(usuario) {
        Usuario(email, nombre, password, peliculasFavoritasID)

    }
}