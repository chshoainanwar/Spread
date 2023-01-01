package com.dev.spread.fragments

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.spread.R
import com.dev.spread.fragments.dialouge.joblistfilter

class CourseRVAdapter(
    private val courseList: ArrayList<joblistfilter.CourseRVModal>,
    private val context: Context?
) : RecyclerView.Adapter<CourseRVAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseRVAdapter.CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_price_filter,
            parent, false
        )

        return CourseViewHolder(itemView)
    }
    var checkPosition: Int = -1

    override fun onBindViewHolder(holder: CourseRVAdapter.CourseViewHolder, position: Int) {
        holder.courseNameTV.text = "$"+courseList.get(position).price.toString()


        holder.layout.setOnClickListener {
            val prePosition = checkPosition
            checkPosition = position
            notifyItemChanged(checkPosition)
            if (prePosition > -1)
                notifyItemChanged(prePosition)
        }

        if (checkPosition == position) {
            holder.courseNameTV.setTextColor(Color.parseColor("#0760F0"))
            holder.layout.setBackgroundResource(R.drawable.border_color_abc_appcolor)
        } else {
            holder.courseNameTV.setTextColor(Color.parseColor("#FF000000"))
            holder.layout.setBackgroundResource(R.drawable.border_color_abc)
        }
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourse)
        val layout: LinearLayout = itemView.findViewById(R.id.llayout)
    }


}