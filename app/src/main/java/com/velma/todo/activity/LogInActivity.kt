package com.velma.todo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.velma.todo.R
import com.velma.todo.utils.Constants

class LogInActivity : AppCompatActivity() {
    private lateinit var email: TextInputLayout
    private lateinit var loginPassword: TextInputLayout
    private lateinit var cont: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        initViews()
        cont.setOnClickListener {
            toHomePage()
        }
    }
    private fun toHomePage() {
        val email = email.editText?.text?.toString()?.trim()
        val loginPassword = loginPassword.editText?.text?.toString()?.trim()
        val intent = Intent(this@LogInActivity, HomeActivity::class.java)
       // intent.putExtra(Constants.EMAIL, email)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    private fun initViews() {
        email = findViewById(R.id.email)
        loginPassword = findViewById(R.id.loginPassword)
        cont = findViewById(R.id.cont)
    }
}