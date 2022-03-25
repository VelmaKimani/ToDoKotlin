package com.example.todo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
        private lateinit var imageView: TextInputLayout
        private lateinit var name: TextInputLayout
        private lateinit var email: TextInputLayout
        private lateinit var password: TextInputLayout
        private lateinit var confirmPassword: TextInputLayout
        private lateinit var createAcc: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initViews()
        /*createAcc.setOnClickListener { toValidateDataSent()*/
        val buttonClick = findViewById<Button>(R.id.createAcc)
        buttonClick.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java )
            startActivity(intent)

        }

    }

    private fun toValidateDataSent() {
        val imageView = imageView.editText?.text?.toString()?.trim()
        val name = name.editText?.text?.toString()?.trim()
        val email = email.editText?.text?.toString()?.trim()
        val password = password.editText?.text?.toString()?.trim()
        val confirmPassword = confirmPassword.editText?.text?.toString()?.trim()

        if (imageView!!.isEmpty() || name!!.isEmpty() || email!!.isEmpty() || password!!.isEmpty() || confirmPassword!!.isEmpty()
        ) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
        } else {
            toConfirmPassword(imageView.toString(), name, email, password, confirmPassword)
        }


    }

    private fun errorMessage () {
        print("Password Mismatch")
    }
    private fun toConfirmPassword(
        imageView: String, name: String, email: String, password: String,
        confirmPassword: String
    ) {
        if (password==confirmPassword){
            toTheNextPage(imageView, name, email,password, confirmPassword)
        }else{
            return errorMessage()}

            Toast.makeText(this@SignUpActivity, "Confirm your password", Toast.LENGTH_SHORT)
                .show()
        }


    private fun toTheNextPage(
        imageView: String,
        name: String, email: String, password: String,
        confirmPassword: String,

        ) {
        //intent
        val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("email", email)
        intent.putExtra("imageView", imageView)
        intent.putExtra("password", password)
        intent.putExtra("confirmPassword", confirmPassword)

        startActivity(intent)

    }

    private fun initViews() {
        imageView = findViewById(R.id.imageView)
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        createAcc = findViewById(R.id.createAcc)

    }
}

