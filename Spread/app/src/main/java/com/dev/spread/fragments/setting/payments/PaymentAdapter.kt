package com.dev.spread.fragments.setting.payments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.dev.spread.R
import com.dev.spread.databinding.ItemCardBinding
import com.dev.spread.fragments.SelectedPaymentMethod
import com.dev.spread.fragments.SelectedPaymentMethod.Companion.fromMessages

class PaymentAdapter(
    private var Context: AppCompatActivity,
    private val Data: ArrayList<PaymentModel>,
    val callback: ((position: Int) -> Unit)
) : RecyclerView.Adapter<PaymentAdapter.PaymentAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentAdapterVH {
        val view = LayoutInflater.from(Context).inflate(R.layout.item_card, parent, false)

        return PaymentAdapterVH(view)
    }

    override fun onBindViewHolder(holder: PaymentAdapterVH, position: Int) {
        val item = Data[position]
        holder.binding.ivCard.setImageResource(item.cardimage!!)
        holder.binding.ivBin.setImageResource(item.binimage!!)
        holder.binding.tvlabel.text=item.labeltext

        if (fromMessages == "card") {
            holder.binding.ivBin.visibility = View.GONE
        }

        holder.binding.ivBin.setOnClickListener {
            callback.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return Data.size
    }

    class PaymentAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemCardBinding = ItemCardBinding.bind(itemView)
    }

}