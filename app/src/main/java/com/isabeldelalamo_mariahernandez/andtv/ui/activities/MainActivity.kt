package com.isabeldelalamo_mariahernandez.andtv.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
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
/*
        var str = "[1, 2, 3]"
        var empty = emptyList<Int>().toString()
        println("+++++++++++++++++++++++++++ lenght " + empty.length)
        str = str.substring(1, str.length-1)
        empty = empty.substring(1, empty.length-1)

        println("+++++++++++++++++++++++++++ " + str)
        var result: List<Int> = str.split(",").map { it.trim().toInt() }
        var result2: List<Int> = empty.split(",").map { it.trim().toInt() }
        println("+++++++++++++++++++++++++++ " + result)
        println("+++++++++++++++++++++++++++ " + result2)
        println("+++++++++++++++++++++++++++ " + emptyList<Int>().toString())
        */
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
                    //CREAR USUARIO E INICIAR SESIÓN
                    //irAPrincipal()
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
                    irAPrincipal()
                else
                    toast(getString(R.string.loginInvalido))
            }
        }




    }

    private fun irAPrincipal(){
        startActivity<Principal>()
    }
}