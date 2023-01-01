package com.dev.spread.fragments.setting.reviews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.spread.base.BaseActivity
import com.dev.spread.fragments.setting.contracts.ContractsFragment.Companion.toReview
import com.dev.spread.fragments.setting.reviews.Adapter.ReviewsAdapter
import com.dev.spread.fragments.setting.reviews.Model.ReviewsModel
import com.dev.spread.R
import com.dev.spread.databinding.FragmentReviewsBinding

class ReviewsFragment : Fragment() {
    private var _binding: FragmentReviewsBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<ReviewsModel>()
    private var mAdapter: ReviewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopBar()
        setRecyclerView()

    }


    private fun setRecyclerView() {
        list.clear()
        mAdapter = ReviewsAdapter(requireActivity() as BaseActivity, list)
        binding.rvReviews.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvReviews.adapter = mAdapter
    }

    private fun setDummyData(){
        list.add(ReviewsModel("Influencer’s name","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",R.drawable.iv_review))
        list.add(ReviewsModel("Wade Warren","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat ",R.drawable.iv_review))
        list.add(ReviewsModel("Influencer’s name","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",R.drawable.iv_review))
        list.add(ReviewsModel("Brooklyn Simmons","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit .",R.drawable.iv_review))
        list.add(ReviewsModel("Influencer’s name","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",R.drawable.iv_review))
    }
    private fun setDummyDataFromMessages(){
        list.add(ReviewsModel("Advertisement title","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",R.drawable.iv_review))
        list.add(ReviewsModel("Promotion video for my restaurant","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat",R.drawable.iv_review))
        list.add(ReviewsModel("Promotion video","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",R.drawable.iv_review))
        list.add(ReviewsModel("Promotion video for my restaurant","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit .",R.drawable.iv_review))
        list.add(ReviewsModel("Promotion video for my restaurant","30 Aug 2022",5, "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",R.drawable.iv_review))
    }

    override fun onResume() {
        super.onResume()
        setTopBar()
    }

    @SuppressLint("SetTextI18n")
    private fun setTopBar(){
        binding.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        when (toReview) {
            "FromRating" -> {
                setDummyDataFromMessages()
                binding.tvText.text = "Cameron’s Reviews"
            }
            "FromMyReview" -> {
                setDummyData()
                binding.tvText.text = "My Reviews"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}