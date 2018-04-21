package com.isabeldelalamo_mariahernandez.andtv.data.db

import android.content.ContentValues
import com.isabeldelalamo_mariahernandez.andtv.data.db.UsuarioTable
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.UsuarioDataSource
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select



class UsuarioDb: UsuarioDataSource {

    private val usuarioDbHelper: UsuarioDbHelper = UsuarioDbHelper.instance
    private val dbDataMapper: DbDataMapper = DbDataMapper()


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