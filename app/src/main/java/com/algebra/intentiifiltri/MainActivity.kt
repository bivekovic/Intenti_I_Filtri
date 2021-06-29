package com.algebra.intentiifiltri

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

const val STRING_KEY = "STRING_KEY"
const val PICK_CONTACT_REQUEST = 2
const val REQUEST_CODE = 1

class MainActivity : AppCompatActivity( ) {

    private lateinit var etValue:         EditText
    private lateinit var bStartSecond:    Button
    private lateinit var bImplicit:       Button
    private lateinit var bStartForResult: Button
    private lateinit var bPickContact:    Button

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        initWidgets( )
        setupListeners( )
    }

    private fun setupListeners( ) {
        bImplicit.setOnClickListener { startImplicit( ) }

        bStartSecond.setOnClickListener {
            val startSecondActivity = Intent( this, SecondActivity::class.java ).apply {
                putExtra( STRING_KEY, etValue.text.toString( ) )
            }
            startActivity( startSecondActivity )
        }

        bStartForResult.setOnClickListener {
            val startSecondActivity = Intent( this, SecondActivity::class.java )
            startActivityForResult(startSecondActivity, REQUEST_CODE)
        }

        bPickContact.setOnClickListener { pickContact() }

    }

    private fun initWidgets() {
        etValue = findViewById(R.id.etValue)
        bStartSecond = findViewById(R.id.bStartSecond)
        bImplicit = findViewById(R.id.bStartImplicit)
        bStartForResult = findViewById(R.id.bStartAcForRes)
        bPickContact = findViewById(R.id.bContact)
    }

    private fun startImplicit() {
        // Create the text message with a string
        val sendIntent = Intent( ).apply {
            action = Intent.ACTION_SEND
            putExtra( Intent.EXTRA_TEXT, etValue.text.toString( ) )
            type = "text/plain"
        }

        // Verify that the intent will resolve to an activity
        if ( sendIntent.resolveActivity( packageManager ) != null) {
            startActivity( sendIntent )
        }

    }

    fun pickContact() {
        Intent( Intent.ACTION_PICK, Uri.parse( "content://contacts" ) ).also { pickContactIntent ->
            pickContactIntent.type = Phone.CONTENT_TYPE // Show user only contacts w/ phone numbers
            startActivityForResult( pickContactIntent, PICK_CONTACT_REQUEST )
        }
    }

    override fun onActivityResult( requestCode: Int, resultCode: Int, data: Intent? ) {
        super.onActivityResult( requestCode, resultCode, data )
        if ( requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK ) {
            Toast.makeText( this, "Back from second", Toast.LENGTH_LONG ).show( )
        }

        if ( requestCode==PICK_CONTACT_REQUEST ) {
            // Make sure the request was successful
            if ( resultCode==Activity.RESULT_OK ) {
                // We only need the NUMBER column, because there will be only one row in the result
                // Get the URI that points to the selected contact
                data?.data?.also { contactUri ->
                    // Perform the query on the contact to get the NUMBER column
                    // We don't need a selection or sort order (there's only one result for this URI)
                    // CAUTION: The query() method should be called from a separate thread to avoid
                    // blocking your app's UI thread. (For simplicity of the sample, this code doesn't
                    // do that.)
                    // Consider using <code><a href="/reference/android/content/CursorLoader.html">CursorLoader</a></code> to perform the query.
                    contentResolver.query( contactUri, arrayOf( Phone.NUMBER ), null, null, null )?.apply {
                        moveToFirst( )

                        // Retrieve the phone number from the NUMBER column
                        val column: Int = getColumnIndex( Phone.NUMBER )
                        val number: String? = getString( column )
                        Toast.makeText( this@MainActivity, "number: $number", Toast.LENGTH_LONG ).show( )
                    }
                }
            }
        }
    }

}


