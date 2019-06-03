package com.example.omb_android

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register_Activity : AppCompatActivity() {

    private lateinit var Documento_txt: EditText
    private lateinit var Nombres_txt: EditText
    private lateinit var Apellidos_txt: EditText
    private lateinit var Telefono_txt: EditText
    private lateinit var Email_txt: EditText
    private lateinit var Contraseña_txt: EditText
    private lateinit var Conf_contraseña_txt: EditText
    private lateinit var Boton_registro: Button
    private lateinit var db_referencia: DatabaseReference
    private lateinit var Base_de_datos: FirebaseDatabase
    private lateinit var Autentificacion: FirebaseAuth
    private lateinit var Pop_up_reg_ok: Dialog
    private lateinit var Close_btn_popup_ok_reg: ImageView
    private lateinit var Btn_popup_reg_ok: Button
    private lateinit var Progress:ProgressBar
    private lateinit var Imagen_reg:ImageView
    private lateinit var layout_txt_doc:TextInputLayout
    private lateinit var layout_txt_nombres:TextInputLayout
    private lateinit var layout_txt_apellidos:TextInputLayout
    private lateinit var layout_txt_telefono:TextInputLayout
    private lateinit var layout_txt_email:TextInputLayout
    private lateinit var layout_txt_pass1:TextInputLayout
    private lateinit var layout_txt_pass2:TextInputLayout

    private fun Mostrar_popup_correcto_reg(){
        Pop_up_reg_ok.setContentView(R.layout.registro_ok)
        Close_btn_popup_ok_reg = Pop_up_reg_ok.findViewById(R.id.Btn_close_popup_reg)
        Btn_popup_reg_ok = Pop_up_reg_ok.findViewById(R.id.Btn_ok)
        Close_btn_popup_ok_reg.setOnClickListener(View.OnClickListener { Pop_up_reg_ok.dismiss() })
        Btn_popup_reg_ok.setOnClickListener(View.OnClickListener { startActivity(Intent(this,Login_Activity::class.java)) })
        Pop_up_reg_ok.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Pop_up_reg_ok.show()
    }

    private fun Crear_usuario(){
        val Documento:String = Documento_txt.text.toString()
        val Nombres:String = Nombres_txt.text.toString()
        val Apellidos:String = Apellidos_txt.text.toString()
        val Telefono:String = Telefono_txt.text.toString()
        val Email:String = Email_txt.text.toString()
        val Password:String = Contraseña_txt.text.toString()
        val Conf_pass:String = Conf_contraseña_txt.text.toString()

        if (Password == Conf_pass) {
            if (!TextUtils.isEmpty(Documento) && !TextUtils.isEmpty(Nombres) &&
                !TextUtils.isEmpty(Apellidos) && !TextUtils.isEmpty(Telefono) &&
                !TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(Conf_pass)) {

                // OCULTANDO
                layout_txt_doc.visibility = View.GONE
                layout_txt_nombres.visibility = View.GONE
                layout_txt_apellidos.visibility = View.GONE
                layout_txt_telefono.visibility = View.GONE
                layout_txt_email.visibility = View.GONE
                layout_txt_pass1.visibility = View.GONE
                layout_txt_pass2.visibility = View.GONE
                Boton_registro.visibility = View.GONE
                Documento_txt.visibility = View.GONE
                Nombres_txt.visibility = View.GONE
                Apellidos_txt.visibility = View.GONE
                Telefono_txt.visibility = View.GONE
                Email_txt.visibility = View.GONE
                Contraseña_txt.visibility = View.GONE
                Conf_contraseña_txt.visibility = View.GONE
                Imagen_reg.visibility = View.GONE

                // ---------------

                // MOSTRANDO
                Progress.visibility = View.VISIBLE
                // ------------

                Autentificacion.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this){
                    task ->

                    if (task.isComplete){
                      val user:FirebaseUser?=Autentificacion.currentUser
                        Verificar_correo(user)
                        val Usuario_bd = db_referencia.child(user?.uid!!)
                        Usuario_bd.child("Documento").setValue(Documento)
                        Usuario_bd.child("Nombres").setValue(Nombres)
                        Usuario_bd.child("Apellidos").setValue(Apellidos)
                        Usuario_bd.child("Telefono").setValue(Telefono)
                        Mostrar_popup_correcto_reg()
                    }
                }
            }
        }
    }

    private fun Verificar_correo(usuario:FirebaseUser?){
        usuario?.sendEmailVerification()?.addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                Toast.makeText(this ,"Se envio el correo a la direccion de email",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this ,"Error al enviar correo de verificación",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun Registrar_usuario(view: View){
        Crear_usuario()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_)

        // IMAGEN REGISTRO
        Imagen_reg = findViewById(R.id.Imagen_reg)
        // -------------

        // INPUT LAYOUTS TEXT INICIALIZADOS
        layout_txt_doc = findViewById(R.id.lay_doc)
        layout_txt_nombres = findViewById(R.id.lay_nom)
        layout_txt_apellidos = findViewById(R.id.lay_ape)
        layout_txt_telefono = findViewById(R.id.lay_tel)
        layout_txt_email = findViewById(R.id.lay_ema)
        layout_txt_pass1 = findViewById(R.id.lay_pass1)
        layout_txt_pass2 = findViewById(R.id.lay_pass2)

        // EDIT TEXTS INICIALIZADOS
        Documento_txt = findViewById(R.id.Txt_Documento)
        Nombres_txt = findViewById(R.id.Txt_Nombres)
        Apellidos_txt = findViewById(R.id.Txt_Apellidos)
        Telefono_txt = findViewById(R.id.Txt_Tel)
        Email_txt = findViewById(R.id.Txt_Email)
        Contraseña_txt = findViewById(R.id.Txt_Pass)
        Conf_contraseña_txt = findViewById(R.id.Txt_Pass_Conf)
        // ------------------

        // BOTONES INICIALIZADOS
        Boton_registro = findViewById(R.id.Btn_Registrarse)
        // ------------------

        // INICIALIZACION BASE DE DATOS
        Base_de_datos = FirebaseDatabase.getInstance()
        Autentificacion = FirebaseAuth.getInstance()
        db_referencia = Base_de_datos.reference.child("Usuarios")
        // ---------------

        // PROGRESSBAR
        Progress = findViewById(R.id.Progress_reg_loading)
        // -----------

        // POP UPS
        Pop_up_reg_ok = Dialog(this)

        Conf_contraseña_txt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //DESPUES
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //ANTES
            }

            override fun onTextChanged(letras: CharSequence?, start: Int, before: Int, count: Int) {
                //EN EL INSTANTE

                //VALIDACION CONTRASEÑA 1 y 2

                if (Contraseña_txt.text.toString() != letras.toString()){
                    Conf_contraseña_txt.error = "Las contraseñas no coinciden"
                }
                else{
                    Conf_contraseña_txt.error = null
                }
                // FIN VALIDACION
            }
        })
    }
}
