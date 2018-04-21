package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.isabeldelalamo_mariahernandez.andtv.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonEnviar.setOnClickListener{ validarEntrada() }

        buttonCambiarModo.setOnClickListener { cambiarModo() }

    }

    private fun cambiarModo(){
        if(editTextPasswordRepeat.visibility == View.VISIBLE) {
            editTextPasswordRepeat.visibility = View.INVISIBLE
            buttonCambiarModo.text = getString(R.string.crearUsuario)
        }
        else {
            editTextPasswordRepeat.visibility = View.VISIBLE
            buttonCambiarModo.text = getString(R.string.login)
        }
    }

    private fun validarEntrada(){
        if(editTextPasswordRepeat.visibility == View.VISIBLE) {
        }
        else {

        }
        irAPrincipal()
    }

    private fun irAPrincipal(){
        startActivity<Principal>()
    }
}

