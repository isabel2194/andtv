package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        textEmail.text = intent.getStringExtra(PARAM_EMAIL)

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
                        layoutCategories.addView(buttonCategory)
                    }
                }
            }
        }

        btnCerrarSesion.setOnClickListener{
            startActivity<MainActivity>()
        }


    }

}