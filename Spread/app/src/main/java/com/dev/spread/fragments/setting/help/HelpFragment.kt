package com.dev.spread.fragments.setting.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.spread.databinding.FragHelpBinding

class HelpFragment : Fragment() {
    private var _binding: FragHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


      binding?.topBar?.tvText?.text = "Help"




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}