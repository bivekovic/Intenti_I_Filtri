package com.algebra.intentiifiltri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity( ) {

    private lateinit var etValue: EditText
    private lateinit var bStartSecond: Button
    private lateinit var bImplicit: Button
    private lateinit var bStartForResult: Button
    private lateinit var bPickContact: Button

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )
        initWidgets( )
        setupListeners( )
    }

    private fun setupListeners( ) {
        bStartSecond.setOnClickListener {
            val intent = Intent( this, SecondActivity::class.java )
            startActivity( intent )
        }

        bImplicit.setOnClickListener {
            val intent = Intent( ).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra( Intent.EXTRA_TEXT, etValue.text.toString( ) )
            }

            startActivity( intent )

        }
    }

    private fun initWidgets( ) {
        etValue = findViewById(R.id.etValue)
        bStartSecond = findViewById(R.id.bStartSecond)
        bImplicit = findViewById(R.id.bStartImplicit)
        bStartForResult = findViewById(R.id.bStartAcForRes)
        bPickContact = findViewById(R.id.bContact)
    }
}