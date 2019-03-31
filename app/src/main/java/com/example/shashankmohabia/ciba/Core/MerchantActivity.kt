package com.example.shashankmohabia.ciba.Core

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.shashankmohabia.ciba.R
import com.example.shashankmohabia.ciba.UserType.UserTypeSelectionActivity
import com.example.shashankmohabia.ciba.Utils.Constants.currMerchant
import com.example.shashankmohabia.ciba.Utils.Constants.currUser
import com.example.shashankmohabia.ciba.Utils.Extensions.filteredData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.menu_activity.*
import kotlinx.android.synthetic.main.merchant_activity.*
import kotlinx.android.synthetic.main.toolbar_merchant.*


lateinit var myGoogleSignInClient: GoogleSignInClient
lateinit var mgso: GoogleSignInOptions
val dbmerch = FirebaseFirestore.getInstance()
//resume by adding a logout fun

class MerchantActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.profile->{
                Toast.makeText(this,"PROFILE",Toast.LENGTH_SHORT).show()
            }
            R.id.orders->{                Toast.makeText(this," ORDERS ",Toast.LENGTH_SHORT).show()
            }
            R.id.logout->{               logout()
            }
            R.id.contactUs->{                Toast.makeText(this,"CONTACTus",Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout_merchant.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.merchant_activity)
        val toolbar=findViewById<Toolbar>(R.id.toolbar_menu_merchant)
        setSupportActionBar(toolbar)

        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout_merchant, toolbar_menu_merchant,
                R.string.Navigation_drawer_open, R.string.Navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                // Toast.makeText(drawerView.context,"working",Toast.LENGTH_SHORT).show()
                setupFragment()
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                Toast.makeText(drawerView.context, "working", Toast.LENGTH_SHORT).show()

                setupFragment()
                invalidateOptionsMenu()
            }
        }

        drawer_layout_merchant.addDrawerListener(toggle)
        toggle.syncState()
       setUpRecyclerView(query)

        nav_view_merchant.setNavigationItemSelectedListener(this)

        //GSO
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        myGoogleSignInClient = GoogleSignIn.getClient(this, gso)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_menu, menu)
        val searchView = MenuItemCompat.getActionView(menu!!.findItem(R.id.search_menu)) as SearchView
        val searchManager: SearchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean {
                if (!text.trim { it <= ' ' }.isEmpty()) {
                   // search(text)
                    filteredData.filterData.clear()
                    setupSearchRecyclerView()
                } else {
                }
                setupSearchRecyclerView()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filteredData.filterData.clear()

                if (newText.trim { it <= ' ' }.isEmpty()) {
                    setUpRecyclerView(query)
                    adapter!!.startListening()
                    filteredData.filterData.clear()

                } else {

                    //search(newText)
                    setupSearchRecyclerView()


                }

                return false
            }
        })
        searchView.setOnSearchClickListener {
          //  Toast.makeText(this@MenuActivity, "FUCK_ME2", Toast.LENGTH_SHORT).show()

        }
        searchManager.setOnCancelListener {
           // Toast.makeText(this@MenuActivity, "FUCK_ME_3", Toast.LENGTH_SHORT).show()

        }
        searchView.setOnCloseListener {
            //Toast.makeText(this@MenuActivity, "FUCK", Toast.LENGTH_SHORT).show()
            return@setOnCloseListener false
        }


        return true
    }

    private fun setupSearchRecyclerView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupFragment() {
        val navigationViewHeader = nav_view_merchant.getHeaderView(0)
        val userdp = navigationViewHeader.findViewById<ImageView>(R.id.userDP)
        val username = navigationViewHeader.findViewById<TextView>(R.id.UserName)
        val useremail = navigationViewHeader.findViewById<TextView>(R.id.UserEmail)
        Glide.with(this).load(currMerchant.profileUrl).override(300, 300).into(userdp)
        username.text = currMerchant.name
        username.isAllCaps = true
        useremail.text = currMerchant.email
        //CHecking if the text view is null
        if (currMerchant.name.equals("")) {
            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show()
        }
        username.text = currMerchant.name

    }

    fun logout() {
        val intent = Intent(this, UserTypeSelectionActivity::class.java)
        myGoogleSignInClient.signOut().addOnCompleteListener {
            startActivity(intent)
            Toast.makeText(this, "YOU just logged out", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}
