package com.dev.spread.fragments.messages.adapter

import android.graphics.Color
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.spread.R
import com.dev.spread.base.BaseActivity
import com.dev.spread.databinding.ChatHeaderLayoutBinding
import com.dev.spread.databinding.ChatItemBinding
import com.dev.spread.fragments.messages.model.RecentChat


class ChatAdapter(
    val context: BaseActivity,
    val mData: ArrayList<RecentChat>,
    public val callback: ((position: Int, flag: Boolean) -> Unit)
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_CHAT = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_HEADER -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.chat_header_layout, parent, false)
                return HeaderViewHolder(view)
            }
            TYPE_CHAT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false)
                return ChatViewHolder(view)
            }
            else ->
                throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val mItem = mData[position]

        when (holder) {
            is ChatViewHolder -> {
                holder.bind(mItem as RecentChat, callback)
            }
            is HeaderViewHolder -> {
                holder.bind(mItem, callback)
            }
        }


    }

    override fun getItemCount(): Int = mData.size

    override fun getItemViewType(position: Int): Int {
//        if (mData[position].chatType == ChatType.HEADER) {
//            return HEADERCHAT
//        } else if(mData[position].chatType == ChatType.TEXT){
//            return CHATITEM
//        } else{
        return CHATITEM
//            throw IllegalArgumentException("Invalid type of data")
//        }
    }
}

class HeaderViewHolder(itemView: View) : BaseViewHolder<RecentChat>(itemView) {
    val binding: ChatHeaderLayoutBinding? = DataBindingUtil.bind(itemView)
    override fun bind(item: RecentChat) {
//        binding?.tvHeaderTextChatAdapter?.text = item.created_at?.timeAgo()
//        binding?.tvHeaderTextChatAdapter?.text = item.separaterTitle
    }

    override fun bind(item: RecentChat, callback: (position: Int, long_press: Boolean) -> Unit) {

    }

}

class ChatViewHolder(itemView: View) : BaseViewHolder<RecentChat>(itemView) {
    val binding: ChatItemBinding? = DataBindingUtil.bind(itemView)
    override fun bind(mItem: RecentChat, callback: ((position: Int, long_press: Boolean) -> Unit)) {
        binding?.chat?.visibility = View.VISIBLE
        itemView.setOnLongClickListener {
            callback.invoke(absoluteAdapterPosition, true)
            true
        }
        itemView.setOnClickListener {
            callback.invoke(absoluteAdapterPosition, false)
        }



        if (mItem.is_sender == false) {
            binding?.userImage?.setImageResource(mItem.receiver_image ?: 0)
            if (mItem?.chat_body.equals("Job declined")) {
                binding?.tvChatText?.setBackgroundResource(R.drawable.border_appcolor_def_parrot_red)
                binding?.tvChatText?.setTextColor(Color.parseColor("#ffffff"))
            } else if (mItem.chat_body.equals("Job accepted")) {
                binding?.tvChatText?.setBackgroundResource(R.drawable.border_appcolor_parrot_right)
                binding?.tvChatText?.setTextColor(Color.parseColor("#ffffff"))
            } else if (mItem.chat_body?.contains("Payment Requested") == true) {
                binding?.tvChatText?.setBackgroundResource(R.drawable.border_appcolor_parrot_right)
                binding?.tvChatText?.setTextColor(Color.parseColor("#ffffff"))
            } else {

            }
            binding?.tvChatText?.text = Html.fromHtml(mItem.chat_body, Html.FROM_HTML_MODE_COMPACT)
            binding?.chatimage?.setImageResource(mItem.chat_image ?: 0)
            binding?.tvTimerFromOwn?.text = mItem.time
            binding?.tvTimeSender?.text = mItem.time

            if (mItem.chat_image != 0 && mItem.chat_image != null) {

                if (mItem.chat_body.equals("advertisment")) {
                    binding?.tvChatText?.visibility = View.GONE
                    binding?.chatimage?.visibility = View.GONE
                    binding?.llTextMessage?.visibility = View.GONE
                    binding?.advertismentlayout?.visibility = View.VISIBLE
                    binding?.tvTimerFromOwn?.visibility = View.GONE
                    binding?.tvTimeSender?.visibility = View.GONE
                } else {

                    binding?.advertismentlayout?.visibility = View.GONE
                    binding?.tvChatText?.visibility = View.GONE
                    binding?.chatimage?.visibility = View.VISIBLE
//                    binding?.tvTimeChatt?.visibility = View.VISIBLE
                    binding?.llTextMessage?.visibility = View.VISIBLE
                    binding?.tvTimerFromOwn?.visibility = View.GONE
                    binding?.tvTimeSender?.visibility = View.VISIBLE
                }
            } else {
                binding?.advertismentlayout?.visibility = View.GONE
                binding?.tvChatText?.visibility = View.VISIBLE
//                binding?.tvTimeChatt?.visibility = View.VISIBLE
                binding?.llTextMessage?.visibility = View.VISIBLE
                binding?.tvTimerFromOwn?.visibility = View.GONE
                binding?.tvTimeSender?.visibility = View.VISIBLE
            }
            //                binding?.llChatInfo?.layoutParams = params
//            binding?.llTextMessage?.gravity = Gravity.END
//            val params = RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//            )
//            params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
            binding?.llTextMessage?.gravity = Gravity.START

        } else {

            binding?.advertismentlayout?.visibility = View.GONE
            binding?.tvChatText?.visibility = View.VISIBLE
//            binding?.tvTimeChatt?.visibility = View.VISIBLE
            binding?.llTextMessage?.visibility = View.VISIBLE
            binding?.tvTimerFromOwn?.visibility = View.VISIBLE
            binding?.tvTimeSender?.visibility = View.GONE


            binding?.userImage?.setImageResource(mItem.receiver_image ?: 0)
            binding?.tvChatText?.text = Html.fromHtml(mItem.chat_body, Html.FROM_HTML_MODE_COMPACT)
            binding?.chatimage?.setImageResource(mItem.chat_image ?: 0)
            binding?.tvTimerFromOwn?.text = mItem.time
            binding?.tvTimeSender?.text = mItem.time

            if (mItem.chat_image != 0 && mItem.chat_image != null) {
                binding?.chatimage?.visibility = View.VISIBLE
                binding?.tvChatText?.visibility = View.GONE
            } else {
                binding?.chatimage?.visibility = View.GONE
                binding?.tvChatText?.visibility = View.VISIBLE
            }


//            binding?.tvChatText?.background?.setTint(Color.parseColor("#F5F5F5"))


            if (mItem?.is_deleted == true) {
                binding?.tvChatText?.setBackgroundResource(R.drawable.border_appcolor_def_deleted)
                binding?.tvChatText?.setTextColor(Color.parseColor("#090A0A"))
                binding?.tvChatText?.typeface =
                    ResourcesCompat.getFont(itemView.context, R.font.inter_thinitalic)
                binding?.tvChatText?.setText("Message Deleted")
                binding?.chatimage?.visibility = View.GONE
                binding?.tvChatText?.visibility = View.VISIBLE
            } else if (mItem?.chat_body.equals("Job offer sent")) {
                binding?.tvChatText?.setBackgroundResource(R.drawable.border_appcolor_def_parrot)
                binding?.tvChatText?.setTextColor(Color.parseColor("#ffffff"))
            } else if (mItem.chat_body?.contains("Refund requested") == true) {
                binding?.tvChatText?.setBackgroundResource(R.drawable.border_appcolor_def_parrot_red_second)
                binding?.tvChatText?.setTextColor(Color.parseColor("#ffffff"))
            } else if (mItem.chat_body?.contains("Cameron W.") == true) {
                binding?.message2?.visibility = View.VISIBLE
                binding?.chatimage?.visibility = View.GONE
                binding?.tvChatText?.visibility = View.GONE
                binding?.userImage?.visibility = View.GONE
                binding?.card?.setBackgroundResource(R.drawable.custom_corner_darkcolor)
            } else {
                binding?.tvChatText?.setBackgroundResource(R.drawable.border_appcolor_def)
                binding?.tvChatText?.setTextColor(Color.parseColor("#ffffff"))

            }


            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
            binding?.llTextMessage?.gravity = Gravity.END
        }

    }

    override fun bind(item: RecentChat) {

    }


//    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
    abstract fun bind(item: T, callback: ((position: Int, long_press: Boolean) -> Unit))
}

enum class ChatType {
    HEADER, TEXT

}

const val HEADERCHAT = 0
const val CHATITEM = 1


