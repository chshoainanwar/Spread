package com.dev.spread.fragments.setting.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topBar.tvText.text = "Profile"



        binding.ivprofileEdit.setOnClickListener {
            val action = R.id.action_profileFragmentToEditProfile
            findNavController().navigate(action)
        }

        binding.ivpasswordEdit.setOnClickListener {
            val action = R.id.action_profileFragmentToEditPasswordFragment
            findNavController().navigate(action)
        }
//        binding.rlTrade.setOnClickListener {
//            val action = R.id.action_profileFragmentToNewTradeLicense
//            findNavController().navigate(action)
//        }
//        binding.rlIDCard.setOnClickListener {
//            val action = R.id.action_profileFragmentToNewIDCard
//            findNavController().navigate(action)
//        }

         binding.topBar.ivBackArrow.setOnClickListener{
             findNavController().navigateUp()
         }
    }

    override fun onResume() {
        super.onResume()
        binding.topBar.tvText.text = "Profile"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}