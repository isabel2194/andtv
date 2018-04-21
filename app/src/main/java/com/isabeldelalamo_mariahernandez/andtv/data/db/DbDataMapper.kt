package com.isabeldelalamo_mariahernandez.andtv.data.db

import com.isabeldelalamo_mariahernandez.andtv.model.Usuario as UsuarioModel

class DbDataMapper {

    fun convertToDomain(usuario: Usuario) = with(usuario) {
        UsuarioModel(email, password, peliculasFavoritasID)
    }


    fun convertFromDomain(usuario: UsuarioModel) = with(usuario) {
        Usuario(email, password, peliculasFavoritasID)

    }
}