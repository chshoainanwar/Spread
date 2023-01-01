package com.dev.spread.fragments.setting.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragEditBinding

class EditProfileFragment : Fragment() {
    private var _binding: FragEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNameField()
        setEmailField()
        binding.topBar.tvText.text = "Profile"
        binding.rlNext.tvNext.text = "Save changes"

    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        setNameField()
        setEmailField()
        binding.topBar.tvText.text = "Profile"
        binding.rlNext.tvNext.text = "Save changes"

        binding.rlNext.tvNext.setOnClickListener {
            val action = R.id.action_EditProfileFragmentToSetting
            findNavController().navigate(action)
        }

        binding.topBar.ivBackArrow.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun setNameField(){
        binding.rlOwnerName.etLabel.hint = "Name"
        binding.rlOwnerName.ivImage.visibility = View.GONE
        binding.rlOwnerName.etInput.setText("")
        binding.rlOwnerName.etInput.inputType = InputType.TYPE_CLASS_TEXT
    }
    private fun setEmailField(){
        binding.rlEmail.etLabel.hint = "Email"
        binding.rlEmail.ivImage.visibility = View.GONE
        binding.rlEmail.etInput.setText("")
        binding.rlEmail.etInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}