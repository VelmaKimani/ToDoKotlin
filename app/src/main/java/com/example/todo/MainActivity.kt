package com.example.todo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var name: TextInputLayout
    private lateinit var email: TextInputLayout
    private lateinit var password: TextInputLayout
    private lateinit var confirmPassword: TextInputLayout
    private lateinit var createAcc: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        /* val buttonClick = findViewById<Button>(R.id.createAcc)
        buttonClick.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java )
            startActivity(intent)
        }*/
        createAcc.setOnClickListener {
            toValidateDataSent()
        }

    }

    private fun toValidateDataSent() {

        val name = name.editText?.text?.toString()?.trim()
        val email = email.editText?.text?.toString()?.trim()
        val password = password.editText?.text?.toString()?.trim()
        val confirmPassword = confirmPassword.editText?.text?.toString()?.trim()

        if ( name!!.isEmpty() || email!!.isEmpty() || password!!.isEmpty() || confirmPassword!!.isEmpty()
        ) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
        } else {
            toConfirmPassword( name, email, password, confirmPassword)
        }


    }

    private fun errorMessage() {
        print("Password Mismatch")
    }

    private fun toConfirmPassword(
      name: String, email: String, password: String,
        confirmPassword: String
    ) {
        if (password == confirmPassword) {
            toTheNextPage( name, email, password, confirmPassword)
        } else {
            return errorMessage()

            Toast.makeText ( this@MainActivity, "Confirm your password", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun toTheNextPage(

        name: String, email: String, password: String,
        confirmPassword: String,

        ) {
        //intent
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("email", email)


        startActivity(intent)

    }

    private fun initViews() {

        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        createAcc = findViewById(R.id.createAcc)

    }

}

