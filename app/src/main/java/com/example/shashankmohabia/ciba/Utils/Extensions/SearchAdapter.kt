package com.example.shashankmohabia.ciba.Utils.Extensions

import android.app.Activity
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
import com.example.shashankmohabia.ciba.Core.MenuActivity
import com.example.shashankmohabia.ciba.Core.MenuExpanded
import com.example.shashankmohabia.ciba.R


class SearchAdapter(val context: Context, val item : List<ItemData>): RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
private val activity= context as MenuActivity

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.menu_item,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size   }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val itemData=item[p1]
        p0.setData(itemData,p1)
            p0.layout.setOnClickListener {
                val intent= Intent(context, MenuExpanded::class.java)
                intent.putExtra("availableOrNot",filteredData.filterData[p1].availableOrNot)
                intent.putExtra("vegOrNot",filteredData.filterData[p1].vegOrNot)
                intent.putExtra("itemName",filteredData.filterData[p1].name)
                intent.putExtra("itemPrice",filteredData.filterData[p1].price)
                intent.putExtra("prepTime",filteredData.filterData[p1].preptime)
                intent.putExtra("id",filteredData.filterData[p1].id)
                filteredData.filterData.clear()

                context.startActivity(intent)
                activity.finish()

            }


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