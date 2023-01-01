package com.dev.spread.fragments.auth

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.amintopup.base.viewGone
import com.dev.spread.R
import com.dev.spread.databinding.FragLoginBinding
import com.dev.spread.fragments.influencer.Influencer

class LoginFragment : Fragment() {
    private var _binding: FragLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countrySelection()
        modificationButton()
        binding?.tvForgot?.setOnClickListener {
            val action = R.id.action_loginFragment_to_forgotFragment2
            findNavController().navigate(action)
        }

        binding.tvSignUp.setOnClickListener {
            val action = R.id.actionLoginToRegisterAs
            findNavController().navigate(action)
        }

        binding.btnSignin.tvNext.setOnClickListener {
            Influencer.toContracts = "ForHome"
            var fromWelcomeToContract = "NotGoBack"
            WelcomeFragment.fromRate = ""
            val action = R.id.action_loginFragment_to_contractsFragment
            findNavController().navigate(action)
        }

        binding.rlGoogle.setOnClickListener {
//            binding.rlInnerGoogle.setBackgroundResource(R.drawable.bg_verstyle)
//            binding.tvGoogle.setTextColor(requireContext().getColor(R.color.white))
//            binding.rlInnerApple.setBackgroundColor(android.R.color.transparent)
//            binding.tvApple.setTextColor(requireContext().getColor(R.color.black))
        }
        binding.rlApple.setOnClickListener {
//            binding.rlInnerApple.setBackgroundResource(R.drawable.bg_verstyle)
//            binding.tvApple.setTextColor(requireContext().getColor(R.color.white))
//            binding.rlInnerGoogle.setBackgroundColor(android.R.color.transparent)
//            binding.tvGoogle.setTextColor(requireContext().getColor(R.color.black))
        }

    }

    override fun onResume() {
        super.onResume()
        inputField()
    }

    private fun inputField() {
        binding?.emailField?.etInput?.inputType = InputType.TYPE_CLASS_TEXT
        binding?.emailField?.ivImage?.viewGone()

        binding?.passwordField?.etLabel?.hint = "Password"
        binding?.emailField?.etLabel?.hint = "Email"

        binding?.passwordField?.ivImage?.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        binding.passwordField.ivImage.setOnClickListener {
            if (binding.passwordField.etInput.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.passwordField.etInput.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.passwordField.ivImage.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                binding.passwordField.etInput.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.passwordField.ivImage.setImageResource(R.drawable.ic_baseline_visibility_24)
            }
            binding.passwordField.etInput.setSelection(binding.passwordField.etInput.length())
        }
    }

    private fun modificationButton() {
        binding?.btnSignin?.tvNext?.text = "Sign in"
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}