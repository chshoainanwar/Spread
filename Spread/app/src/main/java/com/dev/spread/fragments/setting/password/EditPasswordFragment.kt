package com.dev.spread.fragments.setting.password

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragEditPasswordBinding

class EditPasswordFragment : Fragment() {
    private var _binding: FragEditPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragEditPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCurrentField()
        setNewPassField()
        setNewPassConField()
        binding.topBar.tvText.text = "Password"
        binding.rlNext.tvNext.text="Save changes"


        binding.rlNext.tvNext.setOnClickListener {
            val action = R.id.action_editPasswordFragmentToSetting
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

    override fun onResume() {
        super.onResume()
        setCurrentField()
        setNewPassField()
        setNewPassConField()
        binding.topBar.tvText.text = "Password"
    }

    private fun setCurrentField() {
        binding.rlCurrentPass.etLabel.hint = "Password"
//        binding.rlCurrentPass.etInput.setText("Cameron Williamson")
        binding?.rlCurrentPass?.ivImage?.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        binding.rlCurrentPass.ivImage.setOnClickListener {
            if (binding.rlCurrentPass.etInput.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.rlCurrentPass.etInput.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.rlCurrentPass.ivImage.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                binding.rlCurrentPass.etInput.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.rlCurrentPass.ivImage.setImageResource(R.drawable.ic_baseline_visibility_24)
            }
            binding.rlCurrentPass.etInput.setSelection(binding.rlCurrentPass.etInput.length())
        }
    }


    private fun setNewPassField(){
        binding.rlNewPass.etLabel.hint = "Password"
//        binding.rlNewPass.etInput.setText("Cameron Williamson")
        binding?.rlNewPass?.ivImage?.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        binding.rlNewPass.ivImage.setOnClickListener {
            if (binding.rlNewPass.etInput.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.rlNewPass.etInput.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.rlNewPass.ivImage.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                binding.rlNewPass.etInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.rlNewPass.ivImage.setImageResource(R.drawable.ic_baseline_visibility_24)
            }
            binding.rlNewPass.etInput.setSelection(binding.rlCurrentPass.etInput.length())
        }

    }

    private fun setNewPassConField(){
        binding.rlNewPassCon.etLabel.hint = "Confirm Password"
//        binding.rlNewPassCon.etInput.setText("Cameron Williamson")
        binding?.rlNewPassCon?.ivImage?.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        binding.rlNewPassCon.ivImage.setOnClickListener {
            if (binding.rlNewPassCon.etInput.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.rlNewPassCon.etInput.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.rlNewPassCon.ivImage.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                binding.rlNewPassCon.etInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.rlNewPassCon.ivImage.setImageResource(R.drawable.ic_baseline_visibility_24)
            }
            binding.rlNewPassCon.etInput.setSelection(binding.rlCurrentPass.etInput.length())
        }
    }



}