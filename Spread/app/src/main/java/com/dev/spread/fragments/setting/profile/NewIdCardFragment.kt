package com.dev.spread.fragments.setting.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.fragments.auth.IDCardFragment
import com.dev.spread.R
import com.dev.spread.databinding.FragNewIdCardBinding

class NewIdCardFragment : Fragment() {
    private var _binding: FragNewIdCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragNewIdCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkImageUri()
        binding.topBar.tvText.text = "ID card"
        binding.rlAddNew.tvNext.text = "Add new ID card"
        binding.rlAddNew.tvNext.setOnClickListener {
            fromAddNewIDCard = "YesAdd"
            val action = R.id.action_newIDCardToScanCard
            findNavController().navigate(action)
        }

        binding.topBar.ivBackArrow.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    override fun onResume() {
        super.onResume()
        checkImageUri()
        binding.topBar.tvText.text = "ID card"
        binding.rlAddNew.tvNext.text = "Add new ID card"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        var fromAddNewIDCard = ""
    }

    private fun checkImageUri() {
        if (IDCardFragment.imagePathIDCard == "") {
        } else {
            binding.ivShow.setImageURI(IDCardFragment.imagePathIDCard.toUri())
        }
    }

}