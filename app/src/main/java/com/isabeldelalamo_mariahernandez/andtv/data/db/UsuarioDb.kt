package com.isabeldelalamo_mariahernandez.andtv.data.db

import android.content.ContentValues
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.UsuarioDataSource
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.select



class UsuarioDb: UsuarioDataSource {

    private val usuarioDbHelper: UsuarioDbHelper = UsuarioDbHelper.instance
    private val dbDataMapper: DbDataMapper = DbDataMapper()


    fun getUserByEmail(email: String) = usuarioDbHelper.use{
        val emailWhere = "${UsuarioTable.EMAIL} = $email"
        val usuario = select(UsuarioTable.NAME)
                .whereSimple(emailWhere)
                .parseOpt(object : MapRowParser<com.isabeldelalamo_mariahernandez.andtv.data.db.Usuario> {
                    override fun parseRow(columns: Map<String, Any?>) = com.isabeldelalamo_mariahernandez.andtv.data.db.Usuario(columns.toMutableMap())
                })
        if (usuario != null) dbDataMapper.convertToDomain(usuario) else null
    }

    fun saveUsuario(usuario: Usuario) = usuarioDbHelper.use {
        execSQL("delete from ${UsuarioTable.NAME}")

        with(dbDataMapper.convertFromDomain(usuario)) {
            val values = ContentValues();
            values.put("email",usuario.email)
            values.put("password",usuario.password)
            insert(UsuarioTable.NAME, null,values)
        }
    }
}