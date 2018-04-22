package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.isabeldelalamo_mariahernandez.andtv.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.card_forecast_item.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_FILM = "DetailActivity::paramFilm"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initialize()
    }

    private fun initialize() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val filmId = intent.getIntExtra(DetailActivity.PARAM_FILM, 0)
    }
}
