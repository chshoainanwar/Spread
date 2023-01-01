package com.dev.spread.fragments.setting.password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragPasswordBinding

class PasswordFragment : Fragment() {
    private var _binding: FragPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topBar.tvText.text = "Password"

        binding.rlEdit.setOnClickListener {
            val action = R.id.action_passwordFragmentToEditPassword
            findNavController().navigate(action)
        }

        binding.topBar.ivBackArrow.setOnClickListener{
            findNavController().navigateUp()
        }

    }

    override fun onResume() {
        super.onResume()
        binding.topBar.tvText.text = "Password"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}