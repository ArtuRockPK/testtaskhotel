package com.example.hotelapp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.R
import com.squareup.picasso.Picasso

class PhotoAdapter(var listPhotos: List<Int> = listOf(R.drawable.viewpager3), var listLinks:List<String> = emptyList()) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_holder, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(listPhotos,position,listLinks)
    }

    override fun getItemCount(): Int {
        if (listLinks.isEmpty()) return listPhotos.size else return  listLinks.size
    }
}
class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.viewpagerframe)

    fun bind(listPic : List<Int>,position: Int,listLinks: List<String>) {
        if (listLinks.isEmpty()) imageView.setImageResource(listPic[position]) else {
            Picasso.get()
                .load(listLinks[position])
                .into(imageView)
        }
    }
}