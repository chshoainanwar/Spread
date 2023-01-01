package com.dev.spread.fragments.setting.contracts.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.spread.R
import com.dev.spread.databinding.ItemsContractsBinding
import com.dev.spread.fragments.influencer.Influencer.Companion.toContracts
import com.dev.spread.fragments.setting.contracts.ContractsFragment.Companion.fromContracts
import com.dev.spread.fragments.setting.contracts.model.ContractsModel

class ContractsAdapter(
    var context: Fragment,
    var list: ArrayList<ContractsModel>
) : RecyclerView.Adapter<ContractsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_contracts, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mList = list[holder.absoluteAdapterPosition]

        holder.binding.tvDate.text = mList.date
        holder.binding.tvAmount.text = mList.amount
        holder.binding.tvName.text = mList.name
        Glide.with(context)
            .load(mList.image)
            .into(holder.binding.ivImage)

        if (toContracts == "ForHome"){
            holder.binding.tvRefunded.visibility = View.GONE
        }else{
            when (position) {
                0 -> {
                    holder.binding.tvRefunded.visibility = View.VISIBLE
                }
                1 -> {
                    holder.binding.tvRefunded.text = "Disputed"
                    holder.binding.tvRefunded.visibility = View.VISIBLE
                }
            }
        }

        holder.binding.rlMain.setOnClickListener {
            if (toContracts == "ForHome"){
                fromContracts = ""
                val action = R.id.actionContractToDetails
                findNavController(context).navigate(action)
            }else{
                when (position) {
                    0 -> {
                        fromContracts = "Refunded"
                        val action = R.id.actionContractToDetails
                        findNavController(context).navigate(action)
                    }
                    1 -> {
                        fromContracts = "Disputed"
                        val action = R.id.actionContractToDetails
                        findNavController(context).navigate(action)
                    }
                    else ->{
                        fromContracts = ""
                        holder.binding.tvRefunded.visibility = View.GONE
                        val action = R.id.actionContractToDetails
                        findNavController(context).navigate(action)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemsContractsBinding = ItemsContractsBinding.bind(itemView)
    }
}

