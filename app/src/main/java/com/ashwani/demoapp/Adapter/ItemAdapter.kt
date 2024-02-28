package com.ashwani.demoapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashwani.demoapp.MainActivity
import com.ashwani.demoapp.Model.Data
import com.ashwani.demoapp.R
import com.bumptech.glide.Glide

class ItemAdapter(var dataList: ArrayList<Data>, mainActivity: MainActivity) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var ctx=mainActivity

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName) // Replace with your actual TextView ID
        val itemId: TextView = itemView.findViewById(R.id.itemId) // Replace with your actual TextView ID
        val itemtrips: TextView = itemView.findViewById(R.id.itemtrips) // Replace with your actual TextView ID
        val itemCountry: TextView = itemView.findViewById(R.id.itemCountry) // Replace with your actual TextView ID
        val itemSlogan: TextView = itemView.findViewById(R.id.itemSlogan) // Replace with your actual TextView ID
        val ivLogo: ImageView = itemView.findViewById(R.id.ivLogo) // Replace with your actual TextView ID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = dataList[0]
        holder.itemName.text = dataList.get(position).name
        holder.itemId.text = dataList.get(position)._id
        holder.itemtrips.text = dataList.get(position).trips.toString()
        holder.itemCountry.text = dataList.get(position).airline[0].country
        holder.itemSlogan.text = dataList.get(position).airline[0].slogan

        Glide.with(ctx)
            .load(dataList.get(position).airline[0].logo)
            .into(holder.ivLogo)

    }

    override fun getItemCount(): Int {
        try {
            return dataList.size
        } catch (e: Exception) {
            return 0
        }
    }


    fun setPostData(postList: ArrayList<Data>)
    {
//        this.dataList=postList
//        this.dataList.addAll(postList)
        this.dataList.addAll(postList)
        Log.e("asdadasdas", "setPostData: "+dataList.size )
        notifyDataSetChanged()
    }

    fun setSearchData(postList: ArrayList<Data>)
    {
//        this.dataList=postList
//        this.dataList.addAll(postList)
        this.dataList.clear()
        this.dataList.addAll(postList)
        Log.e("asdadasdas", "setPostData: "+dataList.size )
        notifyDataSetChanged()
    }

}
