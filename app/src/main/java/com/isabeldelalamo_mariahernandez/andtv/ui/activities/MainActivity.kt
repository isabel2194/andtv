package com.danimeana.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.danimeana.weatherapp.R
import com.danimeana.weatherapp.model.ForecastList
import com.danimeana.weatherapp.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        setSupportActionBar(toolbar)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync() {
            // Acciones en segundo plano
            val result = ForecastList.requestForecastByZipCode(33510)
            if (result != null) {
                uiThread {
                    // Actualización de la interfaz con el resultado
                    forecastList.adapter = ForecastListAdapter(result.dailyForecast) {
                        startActivity<DetailActivity>(
                                DetailActivity.EXTRA_DATE to it.dateToString(),
                                DetailActivity.EXTRA_DESCRIPTION to it.description,
                                DetailActivity.EXTRA_MIN_TEMPERATURE to it.low,
                                DetailActivity.EXTRA_MAX_TEMPERATURE to it.high
                        )
                    }
                }
            }
        }

    }


}

