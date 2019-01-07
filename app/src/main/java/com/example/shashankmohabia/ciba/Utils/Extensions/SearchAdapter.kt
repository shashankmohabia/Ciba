package com.example.shashankmohabia.ciba.Utils.Extensions

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.shashankmohabia.ciba.Core.MenuExpanded
import com.example.shashankmohabia.ciba.R


class SearchAdapter(val context: Context, val item : List<ItemData>): RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.menu_item,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size   }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val itemData=item[p1]
        p0.setData(itemData,p1)
            /*p0.layout.setOnClickListener {
                val intent= Intent(context, MenuExpanded::class.java)
                intent.putExtra("availableOrNot",searchData.searchItems[p1].availableOrNot)
                intent.putExtra("vegOrNot",searchData.searchItems[p1].vegOrNot)
                intent.putExtra("itemName",searchData.searchItems[p1].name)
                intent.putExtra("itemPrice",searchData.searchItems[p1].price)
                intent.putExtra("prepTime",searchData.searchItems[p1].preptime)
                intent.putExtra("id",searchData.searchItems[p1].id)
                context.startActivity(intent)
              //  searchData.searchItems.clear()
            }*/


    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById<TextView>(R.id.text_view_item_name) as TextView
        val textViewPrice = itemView.findViewById<TextView>(R.id.text_view_item_price) as TextView
        val layout = itemView.findViewById<RelativeLayout>(R.id.relative_layout_item)as RelativeLayout
        fun setData(itemData: ItemData, pos:Int){
            textViewName.text=itemData.name.toString()
            textViewPrice.text=itemData.price.toString()
        }
    }

}