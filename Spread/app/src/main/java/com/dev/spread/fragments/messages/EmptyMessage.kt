package com.dev.spread.fragments.messages

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dev.spread.R
import com.dev.spread.databinding.FragmentEmptyMessageBinding


class EmptyMessage : Fragment() {
    private var _binding: FragmentEmptyMessageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmptyMessageBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        setSearchBar()
    }

    private fun setSearchBar() {
        binding.input.etInput.inputType = InputType.TYPE_CLASS_TEXT
        binding.input.etLabel.hint = "Search"
        binding.input.rlField.setBackgroundResource(R.drawable.bg_serach)
        binding.input.ivImage.setImageResource(R.drawable.iv_search)
    }
}