package com.isabeldelalamo_mariahernandez.andtv.ui.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.isabeldelalamo_mariahernandez.andtv.R
import com.isabeldelalamo_mariahernandez.andtv.model.Forecast
import kotlinx.android.synthetic.main.card_forecast_item.view.*

//Recibe en el constructor los elementos de la lista y la acción al pulsar.
class ForecastListAdapter(val items: List<Forecast>, val itemClick: (Forecast) -> Unit)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    // Implementación de RecyclerView.ViewHolder que se usará en la función onCreateViewHolder
    class ViewHolder(val cardView: CardView, val itemClick: (Forecast) -> Unit)
        : RecyclerView.ViewHolder(cardView) {

        // Hace cambios en la vista creada según el elemento pasado.
        fun bindForecast(forecast: Forecast) {
            // Dentro del bloque with, this es forecast
            // itemView es la vista card_forecast_item cargada más adelante
            with(forecast) {
                itemView.txDate.text = dateToString()
                itemView.txDescription.text = description
                itemView.txMinTemperature.text = "${low}º"
                itemView.txMaxTemperature.text = "${high}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    // Crea nuevas vistas para cada elemento de la lista. Lo invoca el LayoutManager.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Crea una nueva vista.
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_forecast_item, parent, false) as CardView
        // Retorna la vista bajo un objeto que herede de RecyclerView.ViewHolder
        return ViewHolder(view, itemClick)
    }

    // Devuelve el número de elementos.
    override fun getItemCount() = items.size

    // Llama a la función para rellenar la vista pasando el elemento de la posición dada.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(items[position])
    }
}

