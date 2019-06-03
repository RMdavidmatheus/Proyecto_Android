package com.example.omb_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class Actividad_Main : AppCompatActivity() {

    private fun Texto_prueba(){
        val Btn_prueba = findViewById<Button>(R.id.Btn_Prueba)
        val Texto_1 = findViewById<TextView>(R.id.Bienvenida_Ttl)
        var i = 0
        val Tiempo = Timer().run {
            schedule(object : TimerTask() {
                override fun run() {
                    runOnUiThread { Texto_1.setText("Numero: "+i);i++ }
                }
               }, 0, 1000)

        }
    }

    fun Click_test(view: View){
        Texto_prueba()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
