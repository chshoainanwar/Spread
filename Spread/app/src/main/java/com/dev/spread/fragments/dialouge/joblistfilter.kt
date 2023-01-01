package com.dev.spread.fragments.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dev.spread.fragments.CourseRVAdapter
import com.dev.spread.R
import com.dev.spread.databinding.FragmentJoblistfilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class joblistfilter : BottomSheetDialogFragment() {

    private var _binding: FragmentJoblistfilterBinding? = null
    private val binding get() = _binding!!
    lateinit var courseList: ArrayList<CourseRVModal>
    lateinit var courseRVAdapter: CourseRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJoblistfilterBinding.inflate(inflater, container, false)
        courseList = ArrayList()
        inIt()

        return binding.root

    }

    private fun inIt() {
        val layoutManager = GridLayoutManager(context, 3)

        _binding?.recyclerView?.layoutManager = layoutManager

        // on below line we are initializing our adapter
        courseRVAdapter = CourseRVAdapter(courseList, context)

        // on below line we are setting
        // adapter to our recycler view.
        _binding?.recyclerView?.adapter = courseRVAdapter

        courseList.add(CourseRVModal(500))
        courseList.add(CourseRVModal(1500))
        courseList.add(CourseRVModal(3000))
        courseList.add(CourseRVModal(5000))
        courseList.add(CourseRVModal(10000))
        courseList.add(CourseRVModal(20000))

        courseRVAdapter.notifyDataSetChanged()


    }
   data class CourseRVModal (
        val price:Int? = null
   )

}