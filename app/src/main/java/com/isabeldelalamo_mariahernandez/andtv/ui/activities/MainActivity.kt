package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.isabeldelalamo_mariahernandez.andtv.R
import com.isabeldelalamo_mariahernandez.andtv.model.Usuario
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
        val email = editTextCorreo.text.toString()
        val password = editTextPassword.text.toString()




        if(editTextPasswordRepeat.visibility == View.VISIBLE) {
            val repeatPassword = editTextPasswordRepeat.text.toString()
            if(email.isNullOrEmpty() || password.isNullOrEmpty() || repeatPassword.isNullOrEmpty())
                toast(getString(R.string.rellenarValores))
            else if(password != repeatPassword)
                toast(getString(R.string.contrasenasNoIguales))
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                toast(getString(R.string.emailValido))
            else{
                //comprobar usuario con ese email
                val userResult = Usuario.getUserByEmail(email)
                if(userResult != null)
                    toast(getString(R.string.usuarioYaExiste))
                else{
                    Usuario.saveUsuario(email, password)
                    toast(getString(R.string.usuarioCreado))
                }
            }
        }
        else {
            if(email.isNullOrEmpty() || password.isNullOrEmpty())
                toast(getString(R.string.rellenarValores))
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                toast(getString(R.string.emailValido))
            else{
                //comprobar usuario con ese email
                val userResult = Usuario.getUserByEmail(email)
                if(userResult != null && userResult.password == password)
                    irAPrincipal(userResult.email)
                else
                    toast(getString(R.string.loginInvalido))
            }
        }
    }

    private fun irAPrincipal(email:String){
        startActivity<Principal>(
                Principal.PARAM_EMAIL to email)
    }
}