package com.dev.spread.fragments.setting.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.dev.spread.fragments.auth.TradeLicenseFragment
import com.dev.spread.R
import com.dev.spread.databinding.FragNewLicenseBinding

class NewLicenseFragment : Fragment() {
    private var _binding: FragNewLicenseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragNewLicenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        checkImageUri()
        binding.topBar.tvText.text = "Trade license"
        binding.rlAddNew.tvNext.text = "Add new licence"
        binding.rlAddNew.tvNext.setOnClickListener {
            fromAddNewLicense = "YesAdd"
            val action = R.id.action_newTradeToScanLicense
            findNavController().navigate(action)
        }

        binding.topBar.ivBackArrow.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    override fun onResume() {
        super.onResume()
        checkImageUri()
        binding.topBar.tvText.text = "Trade license"
        binding.rlAddNew.tvNext.text = "Add new licence"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        var fromAddNewLicense = ""
    }

    private fun checkImageUri() {
        if (TradeLicenseFragment.imagePathLicense == "") {

        } else {
            binding.ivShow.setImageURI(TradeLicenseFragment.imagePathLicense.toUri())
        }
    }

}