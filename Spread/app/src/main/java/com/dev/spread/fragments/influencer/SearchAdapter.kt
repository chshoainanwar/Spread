package com.dev.spread.fragments.influencer

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.dev.amintopup.base.viewVisible
import com.dev.spread.R
import com.dev.spread.databinding.InfluencerItemBinding

class SearchAdapter(
    private var Context: AppCompatActivity,
    private var Data: List<Model>
) : RecyclerView.Adapter<SearchAdapter.SearchAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterVH {
        val view = LayoutInflater.from(Context).inflate(R.layout.influencer_item, parent, false)

        return SearchAdapterVH(view)
    }

    override fun onBindViewHolder(holder: SearchAdapterVH, position: Int) {
        val item = Data[position]
        holder?.binding?.Circlularimage?.setImageResource(item.circleimage!!)
        holder?.binding?.tvname?.text = item.name
        holder?.binding?.tvViewValue?.text = item.value
        holder?.binding?.tvrating?.text = item.rating
        holder.binding.ivOnline.visibility = View.GONE
        holder.binding.tvTxtCounter.visibility = View.GONE
        holder.binding.tvInvited.visibility = View.GONE


        holder.binding.btn.viewVisible()
        holder.binding.btn.setOnClickListener {
            holder.binding.rlCard.setBackgroundResource(R.color.clickedbg)
            holder.binding.tvNext.setTextColor(Color.BLACK)
            holder.binding.tvNext.text = "Invitation Sent"
        }


    }

    override fun getItemCount(): Int {
        return Data.size
    }

    class SearchAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: InfluencerItemBinding = InfluencerItemBinding.bind(itemView)
    }

}