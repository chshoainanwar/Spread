package com.dev.spread.fragments.craeteads

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daasuu.camerarecorder.CameraRecordListener
import com.daasuu.camerarecorder.CameraRecorder
import com.daasuu.camerarecorder.CameraRecorderBuilder
import com.daasuu.camerarecorder.LensFacing
import com.dev.spread.databinding.FragRecordVideoBinding
import com.dev.spread.widget.SampleGLView
import com.emrekose.recordbutton.OnRecordListener
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class RecordVideoFragment : Fragment() {
    private var _binding: FragRecordVideoBinding? = null
    private val binding get() = _binding!!
    var recording = false

    private var sampleGLView: SampleGLView? = null
    protected var cameraRecorder: CameraRecorder? = null
    private var filepath: String? = null
    protected var lensFacing = LensFacing.BACK
    protected var cameraWidth = 1280
    protected var cameraHeight = 720
    protected var videoWidth = 720
    protected var videoHeight = 1280
    private var counter: CountDownTimer? = null
    private var toggleClick = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragRecordVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }


        binding.ivTick.setOnClickListener {
            checkVideo = "FromVideo"
            fileVideo = "abc"
//            fileVideo = filepath.toString()
            findNavController().navigateUp()
        }

        binding.recordBtn.setRecordListener(object : OnRecordListener {
            override fun onRecordStart() {
                startTimer()
                binding.ivResume.visibility = View.VISIBLE
//                filepath = getVideoFilePath()
//                cameraRecorder!!.start(filepath)
            }

            override fun onRecord() {
                //Get Time
                Log.d("Check", "code")

//                val getTime = binding.recordBtn.getCurrentMiliSecond()
//                if (getTime == 25000 || getTime > 25000) {
//                    binding.ivTick.visibility = View.VISIBLE
//                } else {
//                    binding.ivTick.visibility = View.GONE
//                }
                //Timer

            }

            override fun onRecordCancel() {
//                Toast.makeText(context, filepath.toString(), Toast.LENGTH_SHORT).show()
//                cameraRecorder?.stop()
                binding.ivTick.visibility = View.VISIBLE
                binding.ivResume.visibility = View.GONE
                stopTimer()
            }

            override fun onRecordFinish() {
//                Toast.makeText(context, filepath.toString(), Toast.LENGTH_SHORT).show()
//                cameraRecorder?.stop()
                binding.ivTick.visibility = View.VISIBLE
                binding.ivResume.visibility = View.GONE
                stopTimer()
            }
        })

    }

    private fun startTimer() {
        counter = object : CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.btnSeconds.text = "" + millisUntilFinished / 1000 + " sec"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.btnSeconds.text = "30 Sec"
            }
        }
        counter?.start()
    }

    private fun stopTimer() {
        counter?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        setUpCamera()
    }

    override fun onStop() {
        super.onStop()
        releaseCamera()
    }

    private fun releaseCamera() {
        sampleGLView?.onPause()
        if (cameraRecorder != null) {
            cameraRecorder!!.stop()
            cameraRecorder!!.release()
            cameraRecorder = null
        }
        if (sampleGLView != null) {
            binding.main.removeView(sampleGLView)
            sampleGLView = null
        }
    }


    private fun setUpCameraView() {
        requireActivity().runOnUiThread {
            binding.main.removeAllViews()
            sampleGLView = null
            sampleGLView = SampleGLView(requireContext())
            sampleGLView!!.setTouchListener { event, width, height ->
                if (cameraRecorder == null) return@setTouchListener
                cameraRecorder!!.changeManualFocusPoint(event.x, event.y, width, height)
            }
            binding.main.addView(sampleGLView)
        }
    }


    private fun setUpCamera() {
        setUpCameraView()
        cameraRecorder =
            CameraRecorderBuilder(requireActivity(), sampleGLView) //.recordNoFilter(true)
                .cameraRecordListener(object : CameraRecordListener {
                    override fun onGetFlashSupport(flashSupport: Boolean) {

                    }

                    override fun onRecordComplete() {
                        filepath?.let {
                            exportMp4ToGallery(
                                requireContext(),
                                it
                            )
                        }
                    }

                    override fun onRecordStart() {}
                    override fun onError(exception: Exception) {
                        Log.e("CameraRecorder", exception.toString())
                    }

                    override fun onCameraThreadFinish() {
                        if (toggleClick) {
                            requireActivity().runOnUiThread {
                                setUpCamera()
                            }
                        }
                        toggleClick = false
                    }
                })
                .videoSize(videoWidth, videoHeight)
                .cameraSize(cameraWidth, cameraHeight)
                .lensFacing(lensFacing)
                .build()
    }

    fun exportMp4ToGallery(context: Context, filePath: String) {
        val values = ContentValues(2)
        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
        values.put(MediaStore.Video.Media.DATA, filePath)
        context.contentResolver.insert(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            values
        )
        context.sendBroadcast(
            Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://$filePath")
            )
        )
    }

    @SuppressLint("SimpleDateFormat")
    fun getVideoFilePath(): String {
        return getAndroidMoviesFolder()?.absolutePath + "/" + SimpleDateFormat("yyyyMM_dd-HHmmss").format(
            Date()
        ) + "cameraRecorder.mp4"
    }

    fun getAndroidMoviesFolder(): File? {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
    }

//    private fun checkPermissions() {
//        if (audioPermissionsGranted()) {
//            if (cameraPermissionsGranted()) {
//                if (storagePermissionsGranted()) {
//                    setUpCamera()
//                } else {
//                    ActivityCompat.requestPermissions(
//                        requireActivity(),
//                        REQUIRED_PERMISSIONS_STORAGE,
//                        REQUEST_CODE_STORAGE
//                    )
//                }
//            } else {
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    REQUIRED_PERMISSIONS_CAMERA,
//                    REQUEST_CODE_CAMERA
//                )
//            }
//        } else {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                REQUIRED_PERMISSIONS_AUDIO,
//                REQUEST_CODE_AUDIO
//            )
//        }
//    }

    private fun audioPermissionsGranted() = REQUIRED_PERMISSIONS_AUDIO.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun cameraPermissionsGranted() = REQUIRED_PERMISSIONS_CAMERA.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun storagePermissionsGranted() = REQUIRED_PERMISSIONS_STORAGE.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        checkPermissions()
//    }

    companion object {
        private val REQUIRED_PERMISSIONS_AUDIO = arrayOf(android.Manifest.permission.RECORD_AUDIO)
        private val REQUIRED_PERMISSIONS_CAMERA = arrayOf(android.Manifest.permission.CAMERA)
        private val REQUIRED_PERMISSIONS_STORAGE =
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        private const val REQUEST_CODE_AUDIO = 22
        private const val REQUEST_CODE_CAMERA = 23
        private const val REQUEST_CODE_STORAGE = 24
        var checkVideo = ""
        var fileVideo = ""
    }


}