package com.example.techtestalfa2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class StudentAdapter(private val itemList: List<Student>) : RecyclerView.Adapter<StudentAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val address: TextView = itemView.findViewById(R.id.address)
        val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val student = itemList[position]
        holder.name.text = student.name
        holder.address.text = student.address
        Glide.with(holder.itemView.context)
            .load(student.profilePictureURL)
            .transform(CircleCrop())
            .into(holder.profileImage)
    }

    override fun getItemCount(): Int = itemList.size
}