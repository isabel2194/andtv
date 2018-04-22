package com.isabeldelalamo_mariahernandez.andtv.data.db

import android.content.ContentValues
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.UsuarioDataSource
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update


class UsuarioDb : UsuarioDataSource {

    private val usuarioDbHelper: UsuarioDbHelper = UsuarioDbHelper.instance
    private val dbDataMapper: DbDataMapper = DbDataMapper()

    fun getUserByEmail(email: String) = usuarioDbHelper.use {
        val emailWhere = "${UsuarioTable.EMAIL} = ?"
        val usuario = select(UsuarioTable.NAME)
                .whereSimple(emailWhere, email)
                .parseOpt(object : MapRowParser<Usuario> {
                    override fun parseRow(columns: Map<String, Any?>) = Usuario(columns.toMutableMap())
                })
        if (usuario != null) dbDataMapper.convertToDomain(usuario) else null
    }

    fun saveUsuario(email: String, password: String) = usuarioDbHelper.use {
        execSQL("delete from ${UsuarioTable.NAME}")
        val values = ContentValues()
        values.put("email", email)
        values.put("password", password)
        values.put("peliculasFavoritasID", emptyList<Int>().toString())
        insert(UsuarioTable.NAME, null, values)
    }

    fun updateUsuario(filmId:Int,email: String)= usuarioDbHelper.use {
        val usuario = getUserByEmail(email)

        if(usuario!=null) {
            usuario.peliculasFavoritasID.add(filmId)

            val pares1= Pair("email", usuario.email)
            val pares2= Pair("password", usuario.password)
            val pares3= Pair("peliculasFavoritasID", usuario.peliculasFavoritasID.toString())
            update(UsuarioTable.NAME, pares1,pares2,pares3)
        }
    }
}