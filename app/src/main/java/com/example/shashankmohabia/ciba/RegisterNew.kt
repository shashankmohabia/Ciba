package com.example.shashankmohabia.ciba

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login_customer.*
import kotlinx.android.synthetic.main.regnew.*

private var mAuth: FirebaseAuth? = null

class RegisterNew:AppCompatActivity(){
    override fun onContextMenuClosed(menu: Menu?) {
        super.onContextMenuClosed(menu)
        setContentView(R.layout.welcome1)
        RegisterButton.setOnClickListener {
            Toast.makeText(this,"${username_reg.text.toString()}",Toast.LENGTH_SHORT).show()
            //sleep(2000)
            createAccount(username_reg.text.toString(),password_reg.text.toString())
        }

    }
    private fun createAccount(email: String, password: String) {
        //Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }



        // [START create_user_with_email]
        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI

                        val user = mAuth!!.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }


                }
        // [END create_user_with_email]
    }
    private fun updateUI(account: FirebaseUser?){


        val intent1 = Intent(this, Login_customer::class.java)
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