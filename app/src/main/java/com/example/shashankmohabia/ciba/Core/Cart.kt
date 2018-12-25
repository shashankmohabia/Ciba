package com.example.shashankmohabia.ciba.Core

import android.content.ClipData
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.shashankmohabia.ciba.Auth.LoginActivity
import com.example.shashankmohabia.ciba.R
import com.example.shashankmohabia.ciba.Utils.Extensions.CartAdapter
import com.example.shashankmohabia.ciba.Utils.Extensions.data
import kotlinx.android.synthetic.main.activity_cart.*
import org.w3c.dom.Text

class Cart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val  toolbar_cart : Toolbar = findViewById(R.id.toolbar_cart_layout)
        setSupportActionBar(toolbar_cart)
        setUpRecyclerView()

        calculateTotal()

        val placeOrder = findViewById<Button>(R.id.btn_place_order)
        placeOrder.setOnClickListener {
            finish()
        }

    }

    private fun calculateTotal() {
        val finalAmt= findViewById<TextView>(R.id.total_amt)
        val finalQty = findViewById<TextView>(R.id.total_qty)
        var finalAmount :Int=0
        var finalQuantity :Int =0
        for (item in data.items){
            finalQuantity=finalQuantity+item.qty
            finalAmount=finalAmount+item.amt
        }
        finalQty.text=finalQuantity.toString()+" items for "
        finalQty.isAllCaps=false
        finalAmt.text="Rs."+finalAmount.toString()
        finalAmt.isAllCaps=false
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
       recycler_view_cart.layoutManager=layoutManager

        val adapter = CartAdapter(this,data.items)
        recycler_view_cart.adapter=adapter

       val simpleCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
           override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                    return false
           }

           override fun onSwiped(viewHolder: RecyclerView.ViewHolder,direction:Int){
               val adapterPosition =viewHolder.adapterPosition
               if(direction==ItemTouchHelper.RIGHT){
                   adapter.deleteItem(adapterPosition)
                   calculateTotal()
                   recycler_view_cart.adapter=adapter
               }

           }
       }
        val itemTouchHelper= ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recycler_view_cart)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater= menuInflater
        inflater.inflate(R.menu.cart_menu,menu)
        return true
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.sign_out_cart ->logout()
            R.id.notification_cart -> GotoNotification()
            R.id.current_orders_cart -> GotoCurrOrders()
        }
        return super.onContextItemSelected(item)
    }
    private fun logout(){
        val intent = Intent(this, LoginActivity::class.java)
        mGoogleSignInClient.signOut().addOnCompleteListener {
            startActivity(intent)
            Toast.makeText(this,"YOU just logged out", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    private fun GotoNotification(){
        Toast.makeText(this,"Need to create the activity for notification", Toast.LENGTH_SHORT).show()
    }

    private fun GotoCurrOrders(){
        Toast.makeText(this,"Need to create the activity for notification", Toast.LENGTH_SHORT).show()

    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"Swipe right to remove items",Toast.LENGTH_LONG).show()
    }
}
