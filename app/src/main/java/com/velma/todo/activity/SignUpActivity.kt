package com.velma.todo.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.velma.todo.R
import com.velma.todo.model.User
import com.velma.todo.utils.Constants


class SignUpActivity : AppCompatActivity() {

    private lateinit var name: TextInputLayout
    private lateinit var email: TextInputLayout
    private lateinit var password: TextInputLayout
    private lateinit var confirmPassword: TextInputLayout
    private lateinit var createAcc: Button
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
         initViews()
        /*val buttonClick = findViewById<Button>(R.id.createAcc)
        buttonClick.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java )
            startActivity(intent)
        }*/
         createAcc.setOnClickListener {
             toValidateDataSent()
         }
         loginBtn.setOnClickListener{
             toLoginPage()
         }
    }

    private fun toLoginPage() {
        val email = email.editText?.text?.toString()?.trim()
        val password = password.editText?.text?.toString()?.trim()

        val intent = Intent(this@SignUpActivity, LogInActivity::class.java)
        intent.putExtra(Constants.EMAIL, email)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
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

     private fun passwordErrorMessage() {
         print("Password Mismatch")
     }
     private fun passwordMismatchMessage() {
         print("Invalid Password")
     }

     private fun toConfirmPassword(
       name: String, email: String, password: String,
         confirmPassword: String
     ) {
         if (isEmailValid(email)){
             if (password == confirmPassword) {
                 authentication(name,email,password)
             } else {
                 return passwordErrorMessage()
                 Toast.makeText(this@SignUpActivity, "Confirm your password", Toast.LENGTH_SHORT)
                     .show()
             }
         }
         else {
             return passwordMismatchMessage()
         }
     }

    private fun authentication(name: String, email: String, password: String) {
        FirebaseFirestore.getInstance().collection("users").document().id
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this)
        {
                task-> if (task.isSuccessful){
            saveUser(name,email)
        }
        else Toast.makeText(this, "Error in sign up, try again", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{ e ->
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUser(name: String, email: String) {
      val db = FirebaseFirestore.getInstance()
        val userReference = db.collection(Constants.USERS)
        val user = User(null, name, email)
        userReference.document().set(user).addOnSuccessListener {
            Toast.makeText(this, "Save data successful", Toast.LENGTH_SHORT).show()
                toTheNextPage(user)
        }.addOnFailureListener{e ->
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()}
    }

    private fun toTheNextPage(user: User) {
         //intent
         val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
         intent.putExtra(Constants.USER, user)
         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
         startActivity(intent)

     }

     private fun initViews() {

         name = findViewById(R.id.name)
         email = findViewById(R.id.email)
         password = findViewById(R.id.password)
         confirmPassword = findViewById(R.id.confirmPassword)
         createAcc = findViewById(R.id.createAcc)
         loginBtn = findViewById(R.id.loginBtn)

     }
    private fun isEmailValid(email: String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

