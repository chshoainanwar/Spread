package com.dev.spread.fragments.auth

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragRegisterAsBinding

class RegisterAsFragment : Fragment() {
    private var _binding: FragRegisterAsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragRegisterAsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countrySelection()
        asRegisterSelection()

        binding.rlNext.tvNext.setBackgroundColor(requireContext().getColor(R.color.border_color))
        binding.rlNext.tvNext.isEnabled = false

        binding.rlNext.tvNext.setOnClickListener {
            val action = R.id.actionRegisterAsToSignUp
            findNavController().navigate(action)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun countrySelection(){
        binding.rlEng.setOnClickListener {
            binding.rlEng.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected)
            binding.rlAra.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_unselected)
            binding.tvEng.setTextColor(requireContext().getColor(R.color.white))
            binding.tvAra.setTextColor(requireContext().getColor(R.color.selected_color))
        }
        binding.rlAra.setOnClickListener {
            binding.rlEng.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_unselected)
            binding.rlAra.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected)
            binding.tvEng.setTextColor(requireContext().getColor(R.color.selected_color))
            binding.tvAra.setTextColor(requireContext().getColor(R.color.white))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun asRegisterSelection(){
         binding.rlOwner.setOnClickListener {
             binding.rlOwner.background = ContextCompat.getDrawable(requireContext(),R.drawable.owner_selected)
             binding.tvBusinessLabel.setTextColor(requireContext().getColor(R.color.white))
             binding.tvBusinessDesc.setTextColor(requireContext().getColor(R.color.white))
             binding.rlInfluencer.background = ContextCompat.getDrawable(requireContext(),R.drawable.owner_unselected)
             binding.tvInfluencerLabel.setTextColor(requireContext().getColor(R.color.black))
             binding.tvInfluencerDesc.setTextColor(requireContext().getColor(R.color.text_hint_color))
             binding.rlNext.tvNext.setBackgroundColor(requireContext().getColor(R.color.app_color))
             binding.rlNext.tvNext.isEnabled = true
             registerAs = "Owner"
         }
        binding.rlInfluencer.setOnClickListener {
            binding.rlInfluencer.background = ContextCompat.getDrawable(requireContext(),R.drawable.owner_selected)
            binding.rlOwner.background = ContextCompat.getDrawable(requireContext(),R.drawable.owner_unselected)
            binding.tvInfluencerLabel.setTextColor(requireContext().getColor(R.color.white))
            binding.tvInfluencerDesc.setTextColor(requireContext().getColor(R.color.white))
            binding.tvBusinessLabel.setTextColor(requireContext().getColor(R.color.black))
            binding.tvBusinessDesc.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.rlNext.tvNext.setBackgroundColor(requireContext().getColor(R.color.app_color))
            binding.rlNext.tvNext.isEnabled = true
            registerAs = "Influencer"
//            findNavController().navigate(R.id.action_registerAsFragment_to_dashboard2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        var registerAs = ""
    }


}