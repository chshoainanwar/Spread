package com.dev.spread.fragments.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dev.spread.databinding.FragmentJobOfferDialougeBinding
import com.dev.spread.fragments.CourseRVAdapterDialouge
import com.dev.spread.utils.BaseDialogueFragment


class JobOfferDialouge : BaseDialogueFragment() {
    lateinit var binding: FragmentJobOfferDialougeBinding
    lateinit var courseList: ArrayList<joblistfilter.CourseRVModal>
    lateinit var courseRVAdapter: CourseRVAdapterDialouge
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentJobOfferDialougeBinding.inflate(layoutInflater,container,false)
        courseList = ArrayList()
        inIt()
        return binding.root    }

    private fun inIt() {
        val layoutManager = GridLayoutManager(context, 2)

        binding?.recycler?.layoutManager = layoutManager

        // on below line we are initializing our adapter
        courseRVAdapter = CourseRVAdapterDialouge(courseList, context)

        // on below line we are setting
        // adapter to our recycler view.
        binding?.recycler?.adapter = courseRVAdapter

        courseList.add(joblistfilter.CourseRVModal(500))
        courseList.add(joblistfilter.CourseRVModal(1500))
        courseList.add(joblistfilter.CourseRVModal(3000))
        courseList.add(joblistfilter.CourseRVModal(5000))
        courseList.add(joblistfilter.CourseRVModal(10000))
        courseList.add(joblistfilter.CourseRVModal(20000))

        courseRVAdapter.notifyDataSetChanged()


    }
}