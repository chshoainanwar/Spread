package com.dev.spread.fragments.craeteads.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.spread.base.BaseActivity
import com.dev.spread.fragments.craeteads.model.PriceModel
import com.dev.spread.R
import com.dev.spread.databinding.ItemPricesBinding

class PriceAdapter(
    var context: BaseActivity,
    var list: ArrayList<PriceModel>
) : RecyclerView.Adapter<PriceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_prices, parent, false)
        return ViewHolder(v)
    }

    var checkPosition: Int = -1

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mList = list[holder.absoluteAdapterPosition]

        holder.binding.tvPrice.text = mList.price
        holder.binding.tvFollowers.text = mList.followers
        when (position) {
            0 -> {
                holder.binding.ivBar.setImageResource(R.drawable.p1)
            }
            1 -> {
                holder.binding.ivBar.setImageResource(R.drawable.p2)
            }
            2 -> {
                holder.binding.ivBar.setImageResource(R.drawable.p3)
            }
            3 -> {
                holder.binding.ivBar.setImageResource(R.drawable.p4)
            }
            4 -> {
                holder.binding.ivBar.setImageResource(R.drawable.p5)
            }
            5 -> {
                holder.binding.ivBar.setImageResource(R.drawable.p6)
            }
        }

        holder.binding.rlMain.setOnClickListener {
            val prePosition = checkPosition
            checkPosition = position
            notifyItemChanged(checkPosition)
            if (prePosition > -1)
                notifyItemChanged(prePosition)
        }

        if (checkPosition == position) {
            holder.binding.ivSelect.setImageResource(R.drawable.iv_select)
            holder.binding.rlBar.setBackgroundColor(context.getColor(R.color.white))
            holder.binding.rlMain.setBackgroundResource(R.drawable.bg_price_selected)
        } else {
            holder.binding.ivSelect.setImageResource(R.drawable.iv_unselect)
            holder.binding.rlBar.setBackgroundColor(context.getColor(R.color.box_color))
            holder.binding.rlMain.setBackgroundResource(R.drawable.bg_field)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemPricesBinding = ItemPricesBinding.bind(itemView)
    }
}