package com.dev.spread.fragments

import android.Manifest.permission.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.base.BaseFragment
import com.dev.spread.databinding.FragSplashBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class SplashFragment : BaseFragment() {
    private var _binding: FragSplashBinding? = null
    private val binding get() = _binding!!
    var counter = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()
        binding.root.setOnClickListener {
            checkPermissionForClick()
        }

    }


    private val callBackRunnable = Runnable {
        counter += 1
        Log.d("Counter", "Counter Value $counter")
        val action = R.id.actionSplashToLogin
        findNavController().navigate(action)
    }

    private fun checkPermission() {
        Dexter.withContext(mActivityItem)
            .withPermissions(
                CAMERA,
                RECORD_AUDIO,
                WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {

                    handler.postDelayed(callBackRunnable, 5000)

                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }


            }).check()
    }


    private fun checkPermissionForClick() {
        handler.removeCallbacks(callBackRunnable)
        Dexter.withContext(mActivityItem)
            .withPermissions(
                CAMERA,
                RECORD_AUDIO,
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    val action = R.id.actionSplashToLogin
                    findNavController().navigate(action)
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }


            }).check()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}