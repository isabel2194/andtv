package com.isabeldelalamo_mariahernandez.andtv.data.db

import android.content.ContentValues
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.UsuarioDataSource
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select


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
        if(usuario!=null && usuario.peliculasFavoritasID.indexOf(filmId) == -1){
            usuario.peliculasFavoritasID.add(filmId)

            val emailWhere = "${UsuarioTable.EMAIL} = ?"
            val newValues: ContentValues = ContentValues()
            newValues.put(UsuarioTable.PELICULASFAVORITASID, usuario.peliculasFavoritasID.toString())
            update(UsuarioTable.NAME, newValues, emailWhere, arrayOf(email))
            true
        }
        false
    }
}