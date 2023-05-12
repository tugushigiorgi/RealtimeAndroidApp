package com.example.realtimefirbaseapp.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.realtimefirbaseapp.Database.Models.Student
import com.example.realtimefirbaseapp.R

class RecyclerAdapter(var data:ArrayList<Student>) :RecyclerView.Adapter<RecyclerAdapter.myholder>()  {


    class myholder(itemview: View):RecyclerView.ViewHolder(itemview){
        var Fullname=itemview.findViewById<TextView>(R.id.recycler_fullnameinput)
        var Email= itemview.findViewById<TextView>(R.id.recycler_emailinput)
        var image= itemview.findViewById<ImageView>(R.id.recycler_imageview)
        var ID= itemview.findViewById<TextView>(R.id.recycler_idinput)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
        var view :View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout,null)

        var holder =myholder(view )

        return  holder
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: myholder, position: Int) {

        var fullname = data[position].Name+" "+    data[position].Surname
           holder.Fullname.text = fullname
            holder.Email.text=data[position].EMAIL
            holder.ID.text=data[position].USER_ID

        Glide
            .with(holder.itemView.context)
            .load(data[position].ProfilePhoto)
            .centerCrop()

            .into(holder.image);

    }

}