package com.dev.spread.fragments.setting.reviews.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.spread.base.BaseActivity
import com.dev.spread.fragments.setting.contracts.ContractsFragment.Companion.toReview
import com.dev.spread.fragments.setting.reviews.Model.ReviewsModel
import com.dev.spread.R
import com.dev.spread.databinding.ItemsReviewsBinding

class ReviewsAdapter(
    var context: BaseActivity,
    var list: ArrayList<ReviewsModel>
) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_reviews, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mList = list[holder.absoluteAdapterPosition]

        holder.binding.tvDate.text = mList.date
        holder.binding.tvDesc.text = mList.feedback
        holder.binding.tvName.text = mList.name
        holder.binding.tvRating.rating = mList.rating.toFloat()

        when (toReview) {
            "FromRating" -> {
                holder.binding.ivUser.visibility = View.GONE
            }
            "FromMyReview" -> {
                Glide.with(context)
                    .load(mList.image)
                    .into(holder.binding.ivUser);
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemsReviewsBinding = ItemsReviewsBinding.bind(itemView)
    }
}