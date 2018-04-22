package com.isabeldelalamo_mariahernandez.andtv.model

import com.isabeldelalamo_mariahernandez.andtv.data.datasources.CategoriesProvider
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.FilmProvider
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.UsuarioProvider


data class Usuario(val email:String, val password:String, val peliculasFavoritasID:List<Int>) {
    companion object {
        fun getUserByEmail(em:String) = UsuarioProvider.getUserByEmail(em)
        fun saveUsuario(em:String, pass:String) = UsuarioProvider.saveUsuario(em, pass)
    }
}

data class Category(val id:String, val name:String) {
    companion object {
        fun requestCategories() = CategoriesProvider.getCategories()
    }
}
