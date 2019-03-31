package com.example.omb_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login_Activity : AppCompatActivity() {

    fun Formulario_Registrar(view: View){
        startActivity(Intent(this, Register_Activity::class.java))
    }

    fun Provisional(view: View){
        startActivity(Intent(this, Actividad_Main::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
    }
}
