package com.dev.spread.fragments.messages.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.spread.base.BaseActivity
import com.dev.spread.fragments.messages.model.MessageModel
import com.dev.spread.R
import com.dev.spread.databinding.ItemConversationBinding


class ChatListAdapter(
    var context: BaseActivity,
    private val notifyList: ArrayList<MessageModel>,
    var callBack: (NotificationList: MessageModel) -> Unit,
) : RecyclerView.Adapter<ChatListAdapter.AdapterVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val view = LayoutInflater.from(context).inflate(R.layout.item_conversation, parent, false)

        return AdapterVH(view)
    }

    override fun onBindViewHolder(holder: AdapterVH, position: Int) {
        val item = notifyList[position]
        holder.binding.ivUser.setImageResource(item.circleimage!!)
        holder.binding.tvNumber.text = item.name
        holder.binding.tvTest.text = item.message
        holder.binding.tvDay.text = item.time

        if(item.flag==true){
            holder.binding.messageCount.visibility = View.VISIBLE
        }else{
            holder.binding.messageCount.visibility = View.GONE
        }

        holder?.itemView?.setOnClickListener {
            callBack.invoke(item)
        }


    }
    fun addItem(item: MessageModel, position: Int) {
        try {
            notifyList.add(position, item)
            notifyItemInserted(position)
        } catch (e: Exception) {
            Log.e("MainActivity", e.message!!)
        }
    }
    fun removeItem(position: Int): MessageModel? {
        var item: MessageModel? = null
        try {
            item = notifyList[position]
            notifyList.removeAt(position)
            notifyItemRemoved(position)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return item
    }

    override fun getItemCount(): Int {
        return notifyList.size
    }

    class AdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemConversationBinding = ItemConversationBinding.bind(itemView)
    }
}