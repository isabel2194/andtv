package com.danimeana.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.danimeana.weatherapp.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.card_forecast_item.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATE = "DetailActivity::date"
        const val EXTRA_DESCRIPTION = "DetailActivity::description"
        const val EXTRA_MIN_TEMPERATURE = "DetailActivity::minTemperature"
        const val EXTRA_MAX_TEMPERATURE = "DetailActivity::maxTemperature"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initialize()
    }

    private fun initialize() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txDate.text = intent.getStringExtra(EXTRA_DATE)
        txDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
        txMinTemperature.text = "${intent.getDoubleExtra(EXTRA_MIN_TEMPERATURE, 0.0)}º"
        txMaxTemperature.text = "${intent.getDoubleExtra(EXTRA_MAX_TEMPERATURE, 0.0)}º"
    }
}
