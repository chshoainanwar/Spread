package com.dev.spread.fragments.auth

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragmentSnapchatBinding

class SnapchatFragment : Fragment() {
    private var _binding: FragmentSnapchatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSnapchatBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rlNext.tvNext.setBackgroundColor(requireContext().getColor(R.color.border_color))
        binding.rlNext.tvNext.isEnabled = false

        binding.rlNext.tvNext.setOnClickListener {
            val action = R.id.actionSnapToToIDCard
            findNavController().navigate(action)
        }

        binding.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvShow.setOnClickListener {
            binding.tvShow.setTextColor(requireContext().getColor(R.color.red_text))
        }

        binding.ivSnap.setOnClickListener {
            binding.tvShow.visibility = View.GONE
            binding.tvFollowers.visibility = View.VISIBLE
            binding.tvDesc.text = "Connected."
            binding.rlNext.tvNext.setBackgroundColor(requireContext().getColor(R.color.app_color))
            binding.rlNext.tvNext.isEnabled = true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}