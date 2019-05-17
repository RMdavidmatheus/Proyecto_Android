package com.example.omb_android

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.health.TimerStat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Login_Activity : AppCompatActivity() {

    private lateinit var Btn_error_login:Button
    private lateinit var Close_btn_popup_error:ImageView
    private lateinit var Popup_error:Dialog
    private lateinit var Pop_up_login_ok:Dialog
    private lateinit var Close_btn_popup_ok:ImageView
    private lateinit var Btn_continuar_ok:Button

    fun Mostrar_popup_error(){
        Popup_error.setContentView(R.layout.ventana_error_login)
        Close_btn_popup_error = Popup_error.findViewById(R.id.Btn_close_popup_login_error)
        Btn_error_login = Popup_error.findViewById(R.id.Btn_continuar_error)
        Close_btn_popup_error.setOnClickListener(View.OnClickListener { Popup_error.dismiss() })
        Btn_error_login.setOnClickListener(View.OnClickListener { Popup_error.dismiss() })
        Popup_error.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Popup_error.show()
    }

    fun Mostrar_popup_login_correcto(){
        Pop_up_login_ok.setContentView(R.layout.ventana_login_loading)
        Close_btn_popup_ok = Pop_up_login_ok.findViewById(R.id.Btn_close_popup_login)
        Btn_continuar_ok = Pop_up_login_ok.findViewById(R.id.Btn_continuar_ok)
        Close_btn_popup_ok.setOnClickListener(View.OnClickListener { Pop_up_login_ok.dismiss() })
        Btn_continuar_ok.setOnClickListener(View.OnClickListener { startActivity(Intent(this,Actividad_Main::class.java)) })
        Pop_up_login_ok.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Pop_up_login_ok.show()
    }
    fun Abriendo_pop_up(view: View){
        Mostrar_popup_login_correcto()
    }
    fun Formulario_Registrar(view: View){
        startActivity(Intent(this, Register_Activity::class.java))
    }
    fun Abriendo_error(view: View){
        Mostrar_popup_error()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)

        Pop_up_login_ok = Dialog(this)
        Popup_error = Dialog(this)
    }
}
