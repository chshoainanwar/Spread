package com.dev.spread.fragments.influencer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.dev.spread.R
import com.dev.spread.databinding.InfluencerItemBinding

class Adapter(
    private var Context: AppCompatActivity,
    private val Data: ArrayList<Model>,
    val callback: ((position: Int) -> Unit)
) : RecyclerView.Adapter<Adapter.AdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view = LayoutInflater.from(Context).inflate(R.layout.influencer_item, parent, false)

        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        val item = Data[position]
        if (position == 0) {
            holder.binding.tvTxtCounter.visibility = View.VISIBLE
            holder.binding.tvInvited.visibility = View.VISIBLE
            holder.binding.ivOnline.visibility = View.VISIBLE
        } else {
            holder.binding.tvTxtCounter.visibility = View.GONE
            holder.binding.tvInvited.visibility = View.GONE
            holder.binding.ivOnline.visibility = View.GONE
        }
        holder.binding.Circlularimage.setImageResource(item.circleimage!!)
        holder.binding.tvname.text = item.name
        holder.binding.tvViewValue.text = item.value
        holder.binding.tvrating.text = item.rating

        holder.itemView.setOnClickListener {
            callback.invoke(position)
        }

    }

    fun addItem(item: Model, position: Int) {
        try {
            Data.add(position, item)
            notifyItemInserted(position)
        } catch (e: Exception) {
            Log.e("MainActivity", e.message!!)
        }
    }

    fun removeItem(position: Int): Model? {
        var item: Model? = null
        try {
            item = Data[position]
            Data.removeAt(position)
            notifyItemRemoved(position)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return item
    }

    override fun getItemCount(): Int {
        return Data.size
    }

    class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: InfluencerItemBinding = InfluencerItemBinding.bind(itemView)
    }

}