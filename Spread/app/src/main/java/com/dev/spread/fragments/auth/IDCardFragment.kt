package com.dev.spread.fragments.auth

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragIDCardBinding

class IDCardFragment : Fragment() {
    private var _binding: FragIDCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragIDCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkImageUri()

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.rlNextToCamera.tvNext.text = "Capture photo"
        binding.rlNextToCamera.tvNext.setOnClickListener {
            checkPermissionAndGo()
        }
        binding.tvCapture.setOnClickListener {
            checkPermissionAndGo()
        }
        binding.rlNextToStep4.tvNext.setOnClickListener {
            val action = R.id.actionIDCardToBankInfo
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        checkImageUri()
    }

    companion object {
        const val TAG = "CameraXGFG"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 30
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)
        var imagePathIDCard = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkPermissionAndGo() {
        if (allPermissionsGranted()) {
            val action = R.id.actionIDCardToScanID
            findNavController().navigate(action)
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkImageUri() {
        if (imagePathIDCard == "") {
            binding.ivLicense.visibility = View.VISIBLE
            binding.rlNextBeforeScan.visibility = View.VISIBLE
            binding.rlShow.visibility = View.GONE
            binding.rlNextAfterScan.visibility = View.GONE
        } else {
            binding.tvDesc.text = "ID scanned."
            binding.ivLicense.visibility = View.GONE
            binding.rlNextBeforeScan.visibility = View.GONE
            binding.rlShow.visibility = View.VISIBLE
            binding.rlNextAfterScan.visibility = View.VISIBLE
            binding.ivShow.setImageURI(imagePathIDCard.toUri())
        }
    }

}