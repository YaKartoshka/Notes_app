package com.example.notesapp

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.notesapp.databinding.ActivitySignInBinding
import com.example.notesapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            var confirmPassword = binding.repeatPassword.text.toString()

            if(password==confirmPassword){
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            Toast.makeText(baseContext, "Registration was successful.",
                                Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Failed.",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }else{
                Toast.makeText(baseContext, "Password doesn't match.",
                    Toast.LENGTH_SHORT).show()
            }


        }

    }
}