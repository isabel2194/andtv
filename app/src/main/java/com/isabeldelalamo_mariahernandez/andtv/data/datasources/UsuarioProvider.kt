package com.isabeldelalamo_mariahernandez.andtv.data.datasources

import com.isabeldelalamo_mariahernandez.andtv.data.db.UsuarioDb
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario

object UsuarioProvider {
    private val SOURCE = UsuarioDb()

    fun getUserByEmail(email: String): Usuario?{
        return SOURCE.getUserByEmail(email)
        //return Usuario(email="", password = "", peliculasFavoritasID = listOf(1, 2, 3))
    }
}