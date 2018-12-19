package com.example.shashankmohabia.ciba.Core

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.shashankmohabia.ciba.R
import com.example.shashankmohabia.ciba.Utils.Extensions.ItemData
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions



class MenuAdapter: FirestoreRecyclerAdapter<ItemData, MenuAdapter.ItemHolder> {
    override fun onBindViewHolder(holder: ItemHolder, position: Int, model: ItemData) {
        holder.textViewName.text = model.getItemName()
        holder.textViewPrice.text = model.getItemPrice()
        holder.textViewPriority.text = model.getItemPriority().toString()    }

    constructor(options: FirestoreRecyclerOptions<ItemData>) : super(options)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
       val v : View = LayoutInflater.from(parent.context).inflate(R.layout.menu_item,parent,false)
        return ItemHolder(v)
    }



    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewName = itemView.findViewById<TextView>(R.id.text_view_item_name) as TextView
            val textViewPrice = itemView.findViewById<TextView>(R.id.text_view_item_price) as TextView
            val textViewPriority = itemView.findViewById<TextView>(R.id.text_view_priority) as TextView

    }
}