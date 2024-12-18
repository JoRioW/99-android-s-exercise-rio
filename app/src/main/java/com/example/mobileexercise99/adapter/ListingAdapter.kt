package com.example.mobileexercise99.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobileexercise99.DetailActivity
import com.example.mobileexercise99.R
import com.example.mobileexercise99.model.ApartmentList

class ListingAdapter : RecyclerView.Adapter<ListingAdapter.ListingHolder>() {

    private var itemLists = ArrayList<ApartmentList>()

    fun apartmentLists(itemLists: List<ApartmentList>) {
        this.itemLists = ArrayList(itemLists)
        notifyDataSetChanged()
    }

    class ListingHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImageIV: ImageView = itemView.findViewById(R.id.itemImageIV)
        var itemNameTV: TextView = itemView.findViewById(R.id.itemNameTV)
        var itemAddressTV: TextView = itemView.findViewById(R.id.itemAddressTV)
        var itemCategoryTV: TextView = itemView.findViewById(R.id.itemCategoryTV)
        var itemAttributeTV: TextView = itemView.findViewById(R.id.itemAttributeTV)
        var itemPriceTV: TextView = itemView.findViewById(R.id.itemPriceTV)
        val itemLL : LinearLayout = itemView.findViewById(R.id.itemLL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item,parent,false)
        return ListingHolder(v)
    }

    override fun onBindViewHolder(holder: ListingHolder, position: Int) {
        val item = itemLists[position]

        Glide.with(holder.itemView.context)
            .load(item.photo)
            .into(holder.itemImageIV)

        holder.itemNameTV.text = item.project_name
        holder.itemAddressTV.text = item.address.street_name + " . " + item.address.district
        holder.itemCategoryTV.text = "Exec " + item.category + " . " + item.tenure + " yrs"
        holder.itemAttributeTV.text = item.attributes.bedrooms.toString() + " Beds . " + item.attributes.bathrooms.toString() + " Baths . " + item.attributes.area_size.toString() + " sqft"
        holder.itemPriceTV.text = "$" + String.format("%,d", item.attributes.price) + "/mo"

        holder.itemLL.setOnClickListener{
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("id", item.id)
            Log.d("item_id", item.id.toString())
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemLists.size
    }
}