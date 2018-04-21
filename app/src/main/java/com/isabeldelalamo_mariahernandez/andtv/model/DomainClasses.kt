package com.isabeldelalamo_mariahernandez.andtv.model

import com.isabeldelalamo_mariahernandez.andtv.data.datasources.UsuarioProvider


data class Usuario(val email:String, val password:String, val peliculasFavoritasID:List<Int>) {
    companion object {
        fun getUserByEmail(email: String) = UsuarioProvider.getUserByEmail(email)
    }
}
