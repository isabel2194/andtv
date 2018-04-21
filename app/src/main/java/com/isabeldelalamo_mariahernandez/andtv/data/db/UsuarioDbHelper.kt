package com.isabeldelalamo_mariahernandez.andtv.data.db

import android.database.sqlite.SQLiteDatabase
import com.isabeldelalamo_mariahernandez.andtv.ui.App
import org.jetbrains.anko.db.*

class UsuarioDbHelper : ManagedSQLiteOpenHelper(App.instance,
        UsuarioDbHelper.DB_NAME, null, UsuarioDbHelper.DB_VERSION) {

    companion object {
        const val DB_NAME = "usuario.db"
        const val DB_VERSION = 1
        val instance by lazy { UsuarioDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(UsuarioTable.NAME, true,
                UsuarioTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                UsuarioTable.EMAIL to TEXT,
                UsuarioTable.PASSWORD to TEXT,
                UsuarioTable.PELICULASFAVORITASID to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(UsuarioTable.NAME, true)
        onCreate(db)
    }

}