package com.dev.spread.fragments.auth

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragSignUpBinding

class SignUpFragment : Fragment() {
    private var _binding: FragSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNameField()
        setEmailField()
        setPasswordField()
        countrySelection()

        binding.tvLogin.setOnClickListener {
            val action = R.id.actionSignUpToLogin
            findNavController().navigate(action)
        }

        binding.rlNext.tvNext.setOnClickListener {
            checkOTP = "FromSignUp"
            val action = R.id.actionSignUpToOtp
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        setNameField()
        setEmailField()
        setPasswordField()
        countrySelection()
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

    private fun setNameField(){
        binding.rlOwnerName.etLabel.hint = "Name"
        binding.rlOwnerName.ivImage.visibility = View.GONE
        binding.rlOwnerName.etInput.inputType = InputType.TYPE_CLASS_TEXT
    }
    private fun setEmailField(){
        binding.rlEmail.etLabel.hint = "Email"
        binding.rlEmail.ivImage.visibility = View.GONE
        binding.rlEmail.etInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }
    @SuppressLint("PrivateResource")
    private fun setPasswordField(){
        binding.rlPassword.etLabel.hint = "Password"
        binding.rlPassword.ivImage.setOnClickListener {
            if (binding.rlPassword.etInput.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.rlPassword.etInput.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.rlPassword.ivImage.setImageResource(R.drawable.ic_baseline_visibility_24)
            } else {
                binding.rlPassword.etInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.rlPassword.ivImage.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            }
            binding.rlPassword.etInput.setSelection(binding.rlPassword.etInput.length())
        }
    }

    companion object{
        var checkOTP = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}