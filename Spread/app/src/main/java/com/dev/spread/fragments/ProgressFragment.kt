package com.dev.spread.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragProgressBinding

class ProgressFragment : Fragment() {
    private var _binding: FragProgressBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvGoBack.setOnClickListener {
            val action = R.id.actionWorkingProgressToLogin
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}