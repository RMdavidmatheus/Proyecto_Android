package com.example.omb_android

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth


class Login_Activity : AppCompatActivity() {

    private lateinit var Autentificacion_login:FirebaseAuth
    private lateinit var Usuario_txt:EditText
    private lateinit var Password_user:EditText
    private lateinit var Btn_error_login:Button
    private lateinit var Close_btn_popup_error:ImageView
    private lateinit var Popup_error:Dialog
    private lateinit var Pop_up_login_ok:Dialog
    private lateinit var Close_btn_popup_ok:ImageView
    private lateinit var Btn_continuar_ok:Button

    private fun Mostrar_popup_error(){
        Popup_error.setContentView(R.layout.ventana_error_login)
        Close_btn_popup_error = Popup_error.findViewById(R.id.Btn_close_popup_login_error)
        Btn_error_login = Popup_error.findViewById(R.id.Btn_continuar_error)
        Close_btn_popup_error.setOnClickListener(View.OnClickListener { Popup_error.dismiss() })
        Btn_error_login.setOnClickListener(View.OnClickListener { Popup_error.dismiss() })
        Popup_error.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Popup_error.show()
    }

    private fun Mostrar_popup_login_correcto(){
        Pop_up_login_ok.setContentView(R.layout.ventana_login_loading)
        Close_btn_popup_ok = Pop_up_login_ok.findViewById(R.id.Btn_close_popup_login)
        Btn_continuar_ok = Pop_up_login_ok.findViewById(R.id.Btn_continuar_ok)
        Close_btn_popup_ok.setOnClickListener(View.OnClickListener { Pop_up_login_ok.dismiss() })
        Btn_continuar_ok.setOnClickListener(View.OnClickListener { startActivity(Intent(this,Actividad_Main::class.java)) })
        Pop_up_login_ok.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Pop_up_login_ok.show()
    }

    private fun Loguear(){
        val Usuario:String=Usuario_txt.text.toString()
        val Contraseña:String=Password_user.text.toString()

        if (!TextUtils.isEmpty(Usuario) && !TextUtils.isEmpty(Contraseña)){
            Autentificacion_login.signInWithEmailAndPassword(Usuario,Contraseña).addOnCompleteListener(this){

                task ->
                if (task.isSuccessful){
                    Mostrar_popup_login_correcto()
                }
                else{
                    Mostrar_popup_error()
                }
            }
        }
    }

    private fun Abrir_registro(){
        startActivity(Intent(this,Register_Activity::class.java))
    }

    private fun Abrir_forgot(){
        startActivity(Intent(this,Forgot_Activity::class.java))
    }

    fun Formulario_forgot(view: View){
        Abrir_forgot()
    }

    fun Formulario_registro(view: View){
        Abrir_registro()
    }

    fun Iniciar_sesion(view: View){
        Loguear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)

        // INICIALIZACION DE POPUPS
        Pop_up_login_ok = Dialog(this)
        Popup_error = Dialog(this)
        // -------------------

        // INICIALIZACION DE EDIT TEXTS
        Usuario_txt = findViewById(R.id.Txt_Usuario)
        Password_user = findViewById(R.id.Txt_Password)
        // ------------------

        // INICIALIZACION BASES DE DATOS
        Autentificacion_login = FirebaseAuth.getInstance()
    }
}
