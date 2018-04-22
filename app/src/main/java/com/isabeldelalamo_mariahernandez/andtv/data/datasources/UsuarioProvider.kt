package com.isabeldelalamo_mariahernandez.andtv.data.datasources

import com.isabeldelalamo_mariahernandez.andtv.data.db.UsuarioDb
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario

object UsuarioProvider {
    private val SOURCE = UsuarioDb()

    fun getUserByEmail(em:String): Usuario?{
        return SOURCE.getUserByEmail(em)
    }

    fun saveUsuario(em:String, pass:String){
        SOURCE.saveUsuario(em, pass)
    }

    fun addFavoriteFilm(idFilm:Int,email:String){
        SOURCE.updateUsuario(idFilm,email)
    }
}