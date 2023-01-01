package com.dev.spread.fragments.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.fragments.influencer.Influencer.Companion.toContracts
import com.dev.spread.fragments.setting.contracts.ContractsFragment.Companion.toReview
import com.dev.spread.R
import com.dev.spread.databinding.FragSettingBinding

class SettingFragment : Fragment() {
    private var _binding: FragSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rlProfile.setOnClickListener {
            val action = R.id.action_settingFragmentToProfile
            findNavController().navigate(action)
        }
//        binding.rlLock.setOnClickListener {
//            val action = R.id.action_settingFragmentToPassword
//            findNavController().navigate(action)
//        }

        binding.rlLanguage.setOnClickListener {
            val action = R.id.action_settingFragment_to_languageFragment
            findNavController().navigate(action)
        }

        binding.rlLogout.setOnClickListener {
            val action = R.id.actionSettingFragmentToLogin
            findNavController().navigate(action)
        }

        binding.rlHelp.setOnClickListener {
//            val action = R.id.action_settingFragment_to_helpFragment
//            findNavController().navigate(action)
        }

        binding.rlLegal.setOnClickListener {
            val action = R.id.action_settingFragment_to_LegalFragment
            findNavController().navigate(action)
        }
        binding.tvReviews.setOnClickListener {
            toReview = "FromMyReview"
            val action = R.id.actionSettingToReviews
            findNavController().navigate(action)
        }
//        binding.rlContract.setOnClickListener {
//            toContracts = ""
//            val action = R.id.actionSettingToContracts
//            findNavController().navigate(action)
//        }

        binding.rlPayments.setOnClickListener {
            val action = R.id.action_settingFragment_to_PaymentFragment
            findNavController().navigate(action)
        }

        binding.topBar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}