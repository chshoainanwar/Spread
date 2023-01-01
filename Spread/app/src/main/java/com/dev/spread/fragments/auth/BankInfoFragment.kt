package com.dev.spread.fragments.auth

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragmentBlankInfoBinding

class BankInfoFragment : Fragment() {
    private var _binding: FragmentBlankInfoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBlankInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBankNameField()
        setAccountNameField()
        setAccountNumberField()
        setIBANField()
        accountTypeSelection()

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.rlNext.tvNext.setOnClickListener {
            val action = R.id.actionBankInfoToProfilePhoto
            findNavController().navigate(action)
        }

    }

    override fun onResume() {
        super.onResume()
        setBankNameField()
        setAccountNameField()
        setAccountNumberField()
        setIBANField()
    }

    private fun accountTypeSelection() {
        binding.rlCurrent.setOnClickListener {
            binding.rlCurrent.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_bank)
            binding.rlSavings.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected_field)
            binding.tvCurrent.setTextColor(requireContext().getColor(R.color.white))
            binding.tvSavings.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
        binding.rlSavings.setOnClickListener {
            binding.rlSavings.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_bank)
            binding.rlCurrent.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected_field)
            binding.tvSavings.setTextColor(requireContext().getColor(R.color.white))
            binding.tvCurrent.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
    }

    private fun setBankNameField() {
        binding.rlBankName.etLabel.hint = "Bank name"
        binding.rlBankName.ivImage.visibility = View.GONE
        binding.rlBankName.etInput.inputType = InputType.TYPE_CLASS_TEXT
    }

    private fun setAccountNameField() {
        binding.rlAccountName.etLabel.hint = "Name on account"
        binding.rlAccountName.ivImage.visibility = View.GONE
        binding.rlAccountName.etInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    private fun setAccountNumberField() {
        binding.rlAccountNumber.etLabel.hint = "Account number"
        binding.rlAccountNumber.ivImage.visibility = View.GONE
        binding.rlAccountNumber.etInput.inputType = InputType.TYPE_CLASS_PHONE
    }

    private fun setIBANField() {
        binding.rlIBAN.etLabel.hint = "IBAN"
        binding.rlIBAN.ivImage.visibility = View.GONE

        binding.rlIBAN.etInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}