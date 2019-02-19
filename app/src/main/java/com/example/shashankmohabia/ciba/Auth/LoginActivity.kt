package com.example.shashankmohabia.ciba.Auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.shashankmohabia.ciba.Core.MainActivity
import com.example.shashankmohabia.ciba.Core.MenuActivity
import com.example.shashankmohabia.ciba.Core.OrdersActivity
import com.example.shashankmohabia.ciba.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import es.dmoral.toasty.Toasty

val auth=FirebaseAuth.getInstance()
val dbref=FirebaseFirestore.getInstance()
class LoginActivity:AppCompatActivity(){

    lateinit var mGoogleSignInClient:GoogleSignInClient
    lateinit var gso:GoogleSignInOptions
    val RC_SIGN_IN:Int=1
    var isCustomer :Boolean=false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        val signin=findViewById<View>(R.id.gsignin) as SignInButton
        isCustomer=intent.getBooleanExtra("isCustomer",false)
        gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient=GoogleSignIn.getClient(this,gso)



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
    private fun handleResult(completedTask : Task<GoogleSignInAccount>){
        try{
            val account : GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            userExists(account,account!!.email.toString())
        }catch (e:ApiException){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
        }
    }

    //checks if a person is a first time user or not
    fun userExists(account: GoogleSignInAccount?,email : String) {
        var tempEmail:String? = null
        var n=0

        val query= dbref.collection("UserList")
        query.get().addOnSuccessListener {
            for(collection in it){
                tempEmail=collection.data["email_id"].toString()
                if (tempEmail.equals(email)){
                    n++
                    }else{



                }
            }
        }

        if(n>0){
            updateUI(account)
            Toast.makeText(this,"user Exitsts",Toast.LENGTH_SHORT).show()

        }else{Toast.makeText(this,"User is not registered please register",Toast.LENGTH_SHORT).show()
            val int=Intent(this,Pop::class.java)
            int.putExtra("email",account!!.email.toString())
            int.putExtra("picURL",account.photoUrl.toString())
            startActivity(int)}

    }
     fun updateUI(account : GoogleSignInAccount?){


        if(isCustomer.equals(true)) {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val intent = Intent(this, OrdersActivity::class.java)
            startActivity(intent)
            finish()

        }
    }



    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account!=null){
        updateUI(account)}
    }
}