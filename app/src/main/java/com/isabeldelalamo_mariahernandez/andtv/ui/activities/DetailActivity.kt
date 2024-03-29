package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.isabeldelalamo_mariahernandez.andtv.R
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.FilmProvider
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import com.bumptech.glide.Glide
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_FILM = "DetailActivity::paramFilm"
        const val PARAM_CATEGORY = "DetailActivity::paramCategory"
        const val PARAM_USER = "DetailActivity::paramUser"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initialize()
    }

    private fun initialize() {
        val filmId = intent.getIntExtra(DetailActivity.PARAM_FILM, 0)
        val categoryId = intent.getIntExtra(DetailActivity.PARAM_CATEGORY, 0)


        val detailView = findViewById(R.id.DetailLayout) as LinearLayout

        val titulo = findViewById(R.id.textTitleFilm) as TextView
        val imagen = findViewById(R.id.imageView) as ImageView
        val descripcion = findViewById(R.id.textSinopsisFilm) as TextView

        doAsync() {

            val result = FilmProvider.getDataFilm(filmId)
            if (result != null) {
                uiThread {
                    titulo.text=result.title
                    Glide.with(detailView).load("https://image.tmdb.org/t/p/w500"+result.poster_path).into(imagen)
                    descripcion.text=result.overview
                }
            }
        }

        btnDetailAtras.setOnClickListener{
            startActivity<ListadoActivity>(ListadoActivity.PARAM_CATEGORY to categoryId,
                    ListadoActivity.PARAM_USER to intent.getStringExtra(DetailActivity.PARAM_USER))
        }

        btnDetailFavorito.setOnClickListener{
            añadirFavorito(filmId)
        }
    }

    fun añadirFavorito(filmId:Int){
        val result : Boolean = Usuario.addFavoriteFilm(filmId, intent.getStringExtra(DetailActivity.PARAM_USER))
        if(result)
            toast(getString(R.string.addPeliculaOK))
        else
            toast(getString(R.string.addPeliculaKO))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.add(0,0,0, getString(R.string.categorias))
        menu!!.add(0,1,0, getString(R.string.favoritos))
        menu!!.add(0,2,0, getString(R.string.cerrarSesion))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            0 ->{
                startActivity<Principal>(
                        Principal.PARAM_EMAIL to intent.getStringExtra(DetailActivity.PARAM_USER))
                true
            }
            1 ->{
                startActivity<FavoritosActivity>(
                        FavoritosActivity.PARAM_EMAIL to intent.getStringExtra(DetailActivity.PARAM_USER))
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
