package com.dev.spread.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragmentJobofferSentBinding
import com.dev.spread.fragments.craeteads.SelectPriceFragment
import com.dev.spread.fragments.craeteads.SelectPriceFragment.Companion.toWelcome

class JobofferSent : Fragment() {
    private var _binding: FragmentJobofferSentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobofferSentBinding.inflate(inflater, container, false)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rlCreateAdvertise.tvNext.text = "Home"

        binding.rlCreateAdvertise.tvNext.setOnClickListener {
            toWelcome = ""
            val bundle = Bundle()
            bundle.putString("sendoffer", "send")
            findNavController().navigate(R.id.action_jobofferSent_to_messageDetail, bundle)
        }

    }
}