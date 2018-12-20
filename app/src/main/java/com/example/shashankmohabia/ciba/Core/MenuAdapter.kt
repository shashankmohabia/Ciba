package com.example.shashankmohabia.ciba.Core

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.shashankmohabia.ciba.R
import com.example.shashankmohabia.ciba.Utils.Extensions.ItemData
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot


class MenuAdapter(options: FirestoreRecyclerOptions<ItemData>, private val mContext: Context) : FirestoreRecyclerAdapter<ItemData, MenuAdapter.ItemHolder>(options) {

    override fun onBindViewHolder(holder: ItemHolder, position: Int, model: ItemData) {
        holder.textViewName.text = model.name
        holder.textViewPrice.text = model.price
        holder.layout.setOnClickListener {
            val intent=Intent(mContext,MenuExpanded::class.java)
            mContext.startActivity(intent)
        }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
       val v : View = LayoutInflater.from(parent.context).inflate(R.layout.menu_item,parent,false)

        return ItemHolder(v)
    }

    private lateinit var listenerr : OnItemClickListener

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewName = itemView.findViewById<TextView>(R.id.text_view_item_name) as TextView
            val textViewPrice = itemView.findViewById<TextView>(R.id.text_view_item_price) as TextView
            val layout = itemView.findViewById<RelativeLayout>(R.id.relative_layout_item)as RelativeLayout
            init {
                itemView.setOnClickListener {


                    Toast.makeText(itemView.context, "Fuck", Toast.LENGTH_LONG).show()
                }
            }


    }
     interface OnItemClickListener{
         fun onItemClick(documentSnapshot: DocumentSnapshot,position: Int)
     }
    fun setOnItemClickListener(listener:OnItemClickListener){
        this.listenerr= listener
    }





}


