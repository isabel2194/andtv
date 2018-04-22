package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.isabeldelalamo_mariahernandez.andtv.R
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.isabeldelalamo_mariahernandez.andtv.model.Film
import com.isabeldelalamo_mariahernandez.andtv.ui.adapters.FilmListAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_listado.*
import kotlinx.android.synthetic.main.activity_principal.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class ListadoActivity : AppCompatActivity() {

    companion object{
        const val PARAM_CATEGORY = "ListadoActivity:paramCategory"
        const val PARAM_USER = "ListadoActivity:paramUser"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        initialize()
    }

    fun initialize(){
        val category = intent.getIntExtra(ListadoActivity.PARAM_CATEGORY, 0)
        val user = intent.getStringExtra(ListadoActivity.PARAM_USER)

        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync() {
            // Acciones en segundo plano
            val filmResult = Film.getFilmsByCategory(category.toString())
            if (filmResult != null) {
                uiThread {
                    // Actualización de la interfaz con el resultado
                    forecastList.adapter = FilmListAdapter(filmResult.results) {
                        startActivity<DetailActivity>(
                                DetailActivity.PARAM_FILM to it.id, DetailActivity.PARAM_CATEGORY to category, DetailActivity.PARAM_USER to user
                        )
                    }

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.add(0,0,0, "Categorías")
        menu!!.add(0,1,0, "Favoritos")
        menu!!.add(0,2,0, "Cerrar sesion")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            0 ->{
                startActivity<Principal>(
                        Principal.PARAM_EMAIL to intent.getStringExtra(ListadoActivity.PARAM_USER))
                true
            }
            1 ->{
                startActivity<FavoritosActivity>(
                        FavoritosActivity.PARAM_EMAIL to intent.getStringExtra(ListadoActivity.PARAM_USER))
                true
            }
            2 ->{
                startActivity<MainActivity>()
                true
            }
            else -> false
        }
    }

}
