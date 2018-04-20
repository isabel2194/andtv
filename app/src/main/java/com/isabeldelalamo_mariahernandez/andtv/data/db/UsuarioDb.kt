package com.isabeldelalamo_mariahernandez.andtv.data.db

import com.danimeana.weatherapp.data.db.UsuarioTable
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.UsuarioDataSource
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class UsuarioDb: UsuarioDataSource {

    private val usuarioDbHelper: UsuarioDbHelper = UsuarioDbHelper.instance
    private val dbDataMapper: DbDataMapper = DbDataMapper()


    fun saveUsuario(usuario: UsuarioList) = usuarioDbHelper.use {
        execSQL("delete from ${UsuarioTable.NAME}")

        with(dbDataMapper.convertFromDomain(usuario)) {
            insert(UsuarioTable.NAME, *(mapToPairArray(map)))
            usuario.forEach {
                insert(UsuarioTable.NAME, *(mapToPairArray(it.map)))
            }
        }
    }

    private fun mapToPairArray(map: Map<String, Any?>): Array<Pair<String, Any>> {
        return map.map { Pair(it.key, it.value!!) }.toTypedArray()
    }
}