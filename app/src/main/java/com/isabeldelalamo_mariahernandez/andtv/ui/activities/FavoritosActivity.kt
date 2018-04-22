package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.isabeldelalamo_mariahernandez.andtv.R
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.CategoriesProvider
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.FilmProvider
import com.isabeldelalamo_mariahernandez.andtv.data.server.Film
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario
import com.isabeldelalamo_mariahernandez.andtv.ui.adapters.FilmListAdapter
import kotlinx.android.synthetic.main.activity_favoritos.*
import kotlinx.android.synthetic.main.activity_listado.*
import kotlinx.android.synthetic.main.activity_principal.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class FavoritosActivity : AppCompatActivity() {

    companion object{
        const val PARAM_EMAIL = "FavoritosActivity:paramEmail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        initialize()
    }

    fun initialize(){
        var email = intent.getStringExtra(FavoritosActivity.PARAM_EMAIL)
        val userResult = Usuario.getUserByEmail(email)
        forecastFavList.layoutManager = LinearLayoutManager(this)
        /*
        if(userResult != null &&  userResult.peliculasFavoritasID.isNotEmpty())
            userResult.peliculasFavoritasID.forEach{

        }
        */
        var lista = listOf(384680, 57201, 281957)
        doAsync() {
            val listaFilm: MutableList<Film> = FilmProvider.getFilmsById(lista)
            if (listaFilm != null) {
                uiThread {
                    forecastFavList.adapter = FilmListAdapter(listaFilm) {
                        startActivity<DetailActivity>(
                                DetailActivity.PARAM_FILM to it.id
                        )
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.add(0,0,0, "Categorías")
        menu!!.add(0,1,0, "Cerrar sesion")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            0 -> {
                startActivity<Principal>(
                        Principal.PARAM_EMAIL to intent.getStringExtra(Principal.PARAM_EMAIL))
                true
            }

            1 -> {
                startActivity<MainActivity>()
                true
            }
            else -> false
        }
    }
}
