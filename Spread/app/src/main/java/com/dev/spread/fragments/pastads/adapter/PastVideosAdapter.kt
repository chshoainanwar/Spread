package com.dev.spread.fragments.pastads.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.spread.base.BaseActivity
import com.dev.spread.fragments.pastads.model.PastVideosModel
import com.dev.spread.R
import com.dev.spread.databinding.ItemsPastVideosBinding

class PastVideosAdapter(
    var context: BaseActivity,
    var list: ArrayList<PastVideosModel>
) : RecyclerView.Adapter<PastVideosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_past_videos, parent, false)
        return ViewHolder(v)
    }

    var checkPosition: Int = -1
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mList = list[holder.absoluteAdapterPosition]

        holder.binding.tvText.text = mList.name
        Glide.with(context)
            .load(mList.videos)
            .into(holder.binding.ivVideo);

        holder.binding.ivVideo.setOnClickListener {
            val prePosition = checkPosition
            checkPosition = position
            notifyItemChanged(checkPosition)
            if (prePosition > -1)
                notifyItemChanged(prePosition)
        }

        if (checkPosition == position) {
            holder.binding.rlMain.alpha = 0.7F
        } else {
            holder.binding.rlMain.alpha = 1F
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemsPastVideosBinding = ItemsPastVideosBinding.bind(itemView)
    }
}