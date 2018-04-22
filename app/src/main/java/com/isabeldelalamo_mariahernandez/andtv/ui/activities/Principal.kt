package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.isabeldelalamo_mariahernandez.andtv.R
import com.isabeldelalamo_mariahernandez.andtv.data.datasources.CategoriesProvider
import kotlinx.android.synthetic.main.activity_principal.*
import org.jetbrains.anko.*

class Principal : AppCompatActivity() {

    companion object{
        const val PARAM_EMAIL = "Principal:paramEmail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        initialize()
    }

    private fun initialize() {
        val layoutCategories = findViewById(R.id.LinearLayoutPrincipalCategories) as LinearLayout

        doAsync() {

            val result = CategoriesProvider.getCategories()
            if (result != null) {
                uiThread {
                    result.genres.forEach {
                        val buttonCategory = Button(this@Principal)
                        buttonCategory.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        buttonCategory.text = it.name
                        buttonCategory.id = it.id
                        buttonCategory.setOnClickListener{
                            detalleCategoria(buttonCategory.id)
                        }
                        layoutCategories.addView(buttonCategory)
                    }
                }
            }
        }
    }

    fun detalleCategoria(id:Int){
        startActivity<ListadoActivity>(
                ListadoActivity.PARAM_CATEGORY to id, ListadoActivity.PARAM_USER to intent.getStringExtra(PARAM_EMAIL))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.add(0,0,0, getString(R.string.favoritos))
        menu!!.add(0,1,0, getString(R.string.cerrarSesion))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            0 ->{
                startActivity<FavoritosActivity>(
                        FavoritosActivity.PARAM_EMAIL to intent.getStringExtra(PARAM_EMAIL))
                true
            }
            1 ->{
                startActivity<MainActivity>()
                true
            }
            else -> false
        }
    }
}