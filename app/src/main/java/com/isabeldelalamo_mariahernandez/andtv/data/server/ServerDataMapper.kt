package com.isabeldelalamo_mariahernandez.andtv.data.server

import com.isabeldelalamo_mariahernandez.andtv.model.Usuario
import java.util.*
import java.util.concurrent.TimeUnit

class ServerDataMapper {

    fun convertToDomain(usuario: UsuarioResult): Usuario =
            Usuario(email=usuario.email,
                    password = usuario.password,
                    peliculasFavoritasID = usuario.peliculasFavoritas)

}