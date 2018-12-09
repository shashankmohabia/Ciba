package com.example.shashankmohabia.ciba

import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.shashankmohabia.ciba.R.id.password
import com.example.shashankmohabia.ciba.R.id.username
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login_customer.*
import java.lang.Thread.sleep


private var mAuth: FirebaseAuth? = null



class Login_customer: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_customer)

        mAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener {
            Toast.makeText(this,"${username.text}",Toast.LENGTH_SHORT).show()
            //sleep(2000)
            signIn(username.text.toString(),password.text.toString())
        }
        register.setOnClickListener {
            val intent3= Intent(this,RegisterNew::class.java)
            startActivity(intent3)
            finish()
        }
    }

    private fun signIn(email: String, password: String) {
       // Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }



        // [START sign_in_with_email]
        mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                       // Log.d(TAG, "signInWithEmail:success")
                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                       // Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }

                    // [START_EXCLUDE]
                   // if (!task.isSuccessful) {
                     //   status.setText(R.string.auth_failed)
                    //}

                    // [END_EXCLUDE]
                }
        // [END sign_in_with_email]
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.getCurrentUser()
        if(currentUser!=null){
        updateUI(currentUser)}
    }

    private fun updateUI(account: FirebaseUser?){


        val intent1 = Intent(this,Menu::class.java)
        startActivity(intent1)


    }
public fun validateForm(): Boolean {
    var valid = true

    val email = username.text.toString()
    if (TextUtils.isEmpty(email)) {
        username.error = "Required."
        valid = false
    } else {
        username.error = null
    }

    val passwor = password.text.toString()
    if (TextUtils.isEmpty(passwor)) {
        password.error = "Required."
        valid = false
    } else {
        password.error = null
    }

    return valid
}




}