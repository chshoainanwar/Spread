package com.dev.spread.fragments.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.amintopup.base.viewGone
import com.dev.spread.fragments.auth.SignUpFragment.Companion.checkOTP
import com.dev.spread.R
import com.dev.spread.databinding.FragForgotBinding


class ForgotFragment : Fragment() {


    private var _binding: FragForgotBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragForgotBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btn?.tvNext?.text = "Send code"
        binding?.etEmail?.ivImage?.viewGone()
        binding?.etEmail?.etInput?.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

            binding.btn.tvNext.setOnClickListener {
                checkOTP = "FromForgotPassword"
                val action = R.id.action_forgotFragment2_to_otpFragment
                findNavController().navigate(action)
            }

        binding?.ivBackArrow?.setOnClickListener {
            findNavController().popBackStack()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


