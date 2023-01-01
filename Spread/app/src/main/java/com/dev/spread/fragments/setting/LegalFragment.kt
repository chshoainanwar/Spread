package com.dev.spread.fragments.setting

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.databinding.FragLegalBinding


class LegalFragment : Fragment() {
    private var _binding: FragLegalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragLegalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopBar()



    }

    override fun onResume() {
        super.onResume()
        setTopBar()
    }

    @SuppressLint("SetTextI18n")
    private fun setTopBar(){
        binding.topbar.tvText.text = "Legal"
        binding.topbar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}