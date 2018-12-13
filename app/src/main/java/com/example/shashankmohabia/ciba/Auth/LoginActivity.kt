package com.example.shashankmohabia.ciba.Auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shashankmohabia.ciba.R
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_activity.*

val auth=FirebaseAuth.getInstance()
class LoginActivity:AppCompatActivity(){

    lateinit var mGoogleSignInClient:GoogleSignInClient
    lateinit var gso:GoogleSignInOptions
    val RC_SIGN_IN:Int=1
    lateinit var signOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        val signin=findViewById<View>(R.id.gsignin) as SignInButton
        gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient=GoogleSignIn.getClient(this,gso)
        signOut = findViewById<View>(R.id.signout) as Button

        signin.setOnClickListener {
            signInGoogle()
        }
    }

    private fun signInGoogle(){
        val signInIntent: Intent =mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
    private fun handleResult(completedTask : Task<GoogleSignInAccount>){Toast.makeText(this,"Toast21",Toast.LENGTH_LONG).show()
        try{
            val account : GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            updateUI(account)
        }catch (e:ApiException){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
        }
    }
    private fun updateUI(account : GoogleSignInAccount?){
        val disp = findViewById<TextView>(R.id.name) as TextView
        disp.text=account!!.displayName
        val img = findViewById<ImageView>(R.id.DP)
        signOut.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {

                disp.text="Login"
                //disp.textSize="20sp"

            }
        }
    }
}