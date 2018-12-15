package com.example.shashankmohabia.ciba.Core

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.shashankmohabia.ciba.Auth.LoginActivity
import com.example.shashankmohabia.ciba.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

lateinit var mGoogleSignInClient: GoogleSignInClient
lateinit var gso:GoogleSignInOptions


class MenuActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        val  toolbar_menu : Toolbar = findViewById(R.id.toolbar_menu)
        setSupportActionBar(toolbar_menu)


       //GSO
        gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)

        //logging out here from toolbar




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.menu_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            R.id.sign_out ->logout()
            R.id.notification -> GotoNotification()
            R.id.current_orders -> GotoCurrOrders()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun logout(){
        val intent = Intent(this,LoginActivity::class.java)
        mGoogleSignInClient.signOut().addOnCompleteListener {
            startActivity(intent)
            Toast.makeText(this,"YOU just logged out",Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    private fun GotoNotification(){
     Toast.makeText(this,"Need to create the activity for notification",Toast.LENGTH_SHORT).show()
    }

    private fun GotoCurrOrders(){
        Toast.makeText(this,"Need to create the activity for notification",Toast.LENGTH_SHORT).show()

    }

}