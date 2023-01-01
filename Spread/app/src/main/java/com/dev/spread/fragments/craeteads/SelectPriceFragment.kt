package com.dev.spread.fragments.craeteads

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
import com.dev.spread.fragments.craeteads.CreateAdsFragment.Companion.rePostToPrice
import com.dev.spread.fragments.craeteads.adapter.PriceAdapter
import com.dev.spread.fragments.craeteads.model.PriceModel
import com.dev.spread.R
import com.dev.spread.databinding.FragSelectPriceBinding

class SelectPriceFragment : Fragment() {
    private var _binding: FragSelectPriceBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<PriceModel>()
    private var mAdapter: PriceAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragSelectPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rlPost.tvNext.text = "Post Advertisement"
        setRecyclerView()
        setDummyData()
        if (rePostToPrice == "FromRePost"){

        }
        binding.rlPost.tvNext.setOnClickListener {
            when(rePostToPrice){
                "FromRePost" ->{
                    toWelcome = "RePostAds"
                    val action = R.id.actionPriceFragmentToWelcome
                    findNavController().navigate(action)
                }
                else->{
                    toWelcome = "PostAds"
                    val action = R.id.actionPriceFragmentToWelcome
                    findNavController().navigate(action)
                }
            }
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        binding.rlPost.tvNext.text = "Post Advertisement"
    }

    private fun setRecyclerView() {
        list.clear()
        mAdapter = PriceAdapter(requireActivity() as BaseActivity, list)
        binding.rvPrices.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvPrices.adapter = mAdapter
    }

    private fun setDummyData(){
        list.add(PriceModel("$500","+10K followers"))
        list.add(PriceModel("$1,500","+50K followers"))
        list.add(PriceModel("$3,000","+100K followers"))
        list.add(PriceModel("$5,000","+200K followers"))
        list.add(PriceModel("$10,000","+400K followers"))
        list.add(PriceModel("$20,000","+800K followers"))
    }

    companion object{
        var toWelcome = ""
    }

}