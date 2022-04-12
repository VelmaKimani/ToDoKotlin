package com.velma.todo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.velma.todo.R
import com.velma.todo.model.User
import com.velma.todo.utils.Constants
import kotlinx.android.synthetic.main.activity_sign_up.*

class LogInActivity : AppCompatActivity() {
    private lateinit var email: TextInputLayout
    private lateinit var loginPassword: TextInputLayout
    private lateinit var cont: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        initViews()
        cont.setOnClickListener {
            toHomePage( User(
                "my_id",
                "john doe",
                "johndoe@2kt.com")
            )
        }
    }

    private fun toHomePage(user: User) {
        //val email = email.editText?.text?.toString()?.trim()
        //val loginPassword = loginPassword.editText?.text?.toString()?.trim()
        val intent = Intent(this@LogInActivity, HomeActivity::class.java)
        intent.putExtra(Constants.USER, user)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun initViews() {
        email = findViewById(R.id.email)
        loginPassword = findViewById(R.id.loginPassword)
        cont = findViewById(R.id.cont)

    }

    /*private fun confirmDetails() {
        binding.apply {
            val email = email.text.toString().trim()
            val password = password.editText?.text.toString().trim()
            if (email.isNotEmpty()) {
                if (isEmailValid(email)) {
                    if (password.isNotEmpty()) {

                    } else Toast.makeText(
                        this@LogInActivity,
                        "This is not a valid email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else Toast.makeText(this@LogInActivity, "Email can not be empty", Toast.LENGTH_SHORT)
                .show()
        }
    }*/
    }

private fun Intent.putExtra(email: String, email1: TextInputLayout) {

}
