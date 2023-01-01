package com.dev.spread.fragments.auth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.spread.R
import com.dev.spread.base.BaseActivity
import com.dev.spread.databinding.ItemsPictureBinding
import javax.security.auth.callback.Callback

class GalleryAdapter (
    var context: BaseActivity,
    var list: ArrayList<String>,
    var callBack : ((position : Int, item : String)->Unit)
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_picture, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mList = list[holder.absoluteAdapterPosition]

        Glide.with(context)
            .load(mList)
            .into(holder.binding.ivImage);

        holder.binding.ivImage.setOnClickListener {
            callBack.invoke(position,mList)
        }

        if (position==0){
            holder.binding.cvCamera.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int {
        return list.size+1
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemsPictureBinding = ItemsPictureBinding.bind(itemView)
    }
}