package com.example.barim2eff

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class M2 : AppCompatActivity() {
    lateinit var dbM:DataBase
    lateinit var aj:Button
    lateinit var show:TextView
    lateinit var tnv:EditText
    lateinit var tq:EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_m2)
        show=findViewById(R.id.showN)
        aj=findViewById(R.id.ajouter)
        tnv=findViewById(R.id.txtNumVente)
        tq=findViewById(R.id.txtQte)
        val db=DataBase(this)
        val s=  intent.getStringExtra("num")
        show.text=s
        aj.setOnClickListener {
             val Sshow=show.text.toString()
            val Stnv=tnv.text.toString()
            val Stq=tq.text.toString()
            val S =Sshow.toInt()
            val T =Stq.toInt()

            val AjDb=db.ajouterVent(S,Stnv,T)
            if (AjDb != -1L){
                Toast.makeText(applicationContext,"nadi ",Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(applicationContext,"laaaaaaaaaa ",Toast.LENGTH_SHORT).show()

        }

    }
}