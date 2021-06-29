package com.algebra.intentiifiltri

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : AppCompatActivity() {

    private lateinit var bBack: Button
    private lateinit var eText: EditText

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_second )

        val textFromMain = intent?.getStringExtra( STRING_KEY ) ?: ""
        eText = findViewById( R.id.editText2 )
        eText.setText( textFromMain )

        bBack = findViewById( R.id.bBack )
        bBack.setOnClickListener { finish( ) }
    }

    override fun finish( ) {
        Intent( ).apply {
            setResult( Activity.RESULT_OK, this )
            putExtra( REZULTAT_KEY, eText.text.toString( ) )
        }
        super.finish( )
    }

}
