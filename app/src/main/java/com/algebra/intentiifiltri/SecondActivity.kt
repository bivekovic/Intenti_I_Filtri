package com.algebra.intentiifiltri

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : AppCompatActivity() {

    private lateinit var bBack: Button

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_second )

        val editText: EditText = findViewById( R.id.editText2 )

        val textFromMain = intent?.getStringExtra( STRING_KEY ) ?: ""
        editText.setText( textFromMain )

        bBack = findViewById( R.id.bBack )
        bBack.setOnClickListener { finish( ) }
    }

    override fun finish() {
        Intent( this, MainActivity::class.java ).apply {
            setResult( Activity.RESULT_OK )
        }
        super.finish( )
    }

}
