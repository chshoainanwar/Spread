package com.dev.spread.fragments.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.spread.R
import com.dev.spread.databinding.FragmentSwipelimitationBinding
import com.dev.spread.fragments.dashboard.Dashboard.Companion.oncreate
import com.dev.spread.utils.BaseDialogueFragment


class swipelimitation : BaseDialogueFragment() {

    lateinit var binding:FragmentSwipelimitationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSwipelimitationBinding.inflate(layoutInflater,container,false)

        init()
        return binding.root
    }

    private fun init() {
        binding?.cross?.setOnClickListener {
            oncreate=""
            dialog?.dismiss()
        }

        if(oncreate=="oncreate"){
            binding?.swiptitle?.text = "Swipe Jobs"
            binding?.swipdetail?.text = "Swipe right or left to accept or decline a job."
            binding?.image?.setImageResource(R.drawable.swipleftright)
        }else{
            binding?.swiptitle?.text = "Swipe Right Limit: 50 "
            binding?.swipdetail?.text = "You can apply to a maximum of 50 jobs daily."
            binding?.image?.setImageResource(R.drawable.handsign)
        }

    }


}