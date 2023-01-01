package com.dev.spread.fragments.auth

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragAddpasswordBinding

class AddpasswordFragment : Fragment() {
    private var _binding: FragAddpasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragAddpasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btn?.tvNext?.text = "Set password"

        binding?.btn?.tvNext?.setOnClickListener {
            val action = R.id.action_addpasswordToLogin
            findNavController().navigate(action)
        }
        binding?.ivBackArrow?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        inputField()
    }

    private fun inputField() {
        binding?.emailField?.etLabel?.hint = "Password"
        binding?.passwordField?.etLabel?.hint = "Confirm Password"

        // Setting the things of field
        binding?.emailField?.ivImage?.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        binding.emailField.ivImage.setOnClickListener {
            if (binding.emailField.etInput.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.emailField.etInput.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.emailField.ivImage.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                binding.emailField.etInput.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.emailField.ivImage.setImageResource(R.drawable.ic_baseline_visibility_24)
            }
            binding.emailField.etInput.setSelection(binding.emailField.etInput.length())
        }

        // Setting the things of Password 2nd field
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}