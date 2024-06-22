package com.example.barim2eff

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var valider: Button
    private lateinit var num: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valider = findViewById(R.id.valider)
        num = findViewById(R.id.txtNum)
       val dbM = DataBase(this)

        valider.setOnClickListener {
            val tnum = num.text.toString()
            if (tnum.isNotEmpty()) {
                val rech = dbM.rechercher(tnum)
                if (rech !=null) {
                    Toast.makeText(applicationContext, "Already exists", Toast.LENGTH_SHORT).show()
                }
                else {
                    val intent = Intent(this, M2::class.java).apply {
                        putExtra("num", tnum)
                    }
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Field is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
