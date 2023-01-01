package com.dev.spread.fragments.pastads

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
import com.dev.spread.fragments.craeteads.RecordVideoFragment.Companion.checkVideo
import com.dev.spread.fragments.currentads.CurrentAdsFragment.Companion.fromCurrentAds
import com.dev.spread.fragments.pastads.adapter.PastVideosAdapter
import com.dev.spread.fragments.pastads.model.PastVideosModel
import com.dev.spread.R
import com.dev.spread.databinding.FragPastVideosBinding

class PastVideosFragment : Fragment() {
    private var _binding: FragPastVideosBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<PastVideosModel>()
    private var mAdapter: PastVideosAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragPastVideosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopBar()
        setRecyclerView()
        setDummyData()

        binding.rlNext.tvNext.setOnClickListener {
            checkVideo = "FromVideo"
            fromCurrentAds = "RepostAds"
            val action = R.id. actionPastVideoToCreateAds
            findNavController().navigate(action)
        }

    }

    override fun onResume() {
        super.onResume()
        setTopBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() {
        list.clear()
        mAdapter = PastVideosAdapter(requireActivity() as BaseActivity, list)
        binding.rvPastVideos.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvPastVideos.adapter = mAdapter
    }

    private fun setDummyData(){
        list.add(PastVideosModel("Promotion video",R.drawable.video2))
        list.add(PastVideosModel("Promotion video",R.drawable.video2))
        list.add(PastVideosModel("Promotion video",R.drawable.video2))
        list.add(PastVideosModel("Promotion video",R.drawable.video2))
        list.add(PastVideosModel("Promotion video",R.drawable.video2))
    }

    @SuppressLint("SetTextI18n")
    private fun setTopBar(){
        binding.rlTopBar.tvText.text = "Search"
        binding.rlTopBar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}