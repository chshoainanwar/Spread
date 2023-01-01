package com.dev.spread.fragments.auth

import android.R.attr
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.spread.R
import com.dev.spread.base.BaseActivity
import com.dev.spread.databinding.FragmentProfilePhotoBinding
import com.dev.spread.fragments.auth.adapter.GalleryAdapter
import com.dev.spread.fragments.craeteads.SelectPriceFragment.Companion.toWelcome
import com.google.android.material.bottomsheet.BottomSheetDialog


class ProfilePhotoFragment : Fragment() {
    private var _binding: FragmentProfilePhotoBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<String>()
    var imageUri : String? = ""
    private var mAdapter: GalleryAdapter? = null
    private val MY_CAMERA_PERMISSION_CODE = 100
    private val CAMERA_REQUEST = 1888

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfilePhotoBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.rlContinue.tvNext.setBackgroundColor(requireContext().getColor(R.color.border_color))
        binding.rlContinue.tvNext.isEnabled = false

        binding.ivPickImage.setOnClickListener {
            uploadProfileImageBottomDialog()
        }

        binding.rlContinue.tvNext.setOnClickListener {
            toWelcome = "fromInfluencer"
            val action = R.id.actionProfileToWelcome
            findNavController().navigate(action)
        }

        binding.cvDelete.setOnClickListener {
            binding.rlBeforeImg.visibility = View.VISIBLE
            binding.rlAfterImg.visibility = View.GONE
            binding.rlContinue.tvNext.setBackgroundColor(requireContext().getColor(R.color.border_color))
            binding.rlContinue.tvNext.isEnabled = false
            binding.tvUploadLabel.text = "Upload Profile Photo"
            binding.tvDesc.text = "Make a good first impression."
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        binding.rlContinue.tvNext.text = "Continue"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    private fun uploadProfileImageBottomDialog() {
        val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        val layoutDialog =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_pictures, null, false)
        dialog.setContentView(layoutDialog)
        dialog.setCancelable(true)
        dialog.show()

        val rvItems = layoutDialog.findViewById<RecyclerView>(R.id.rvItems)


        list.clear()
        val columns = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media._ID)
        val imagecursor: Cursor = requireActivity().managedQuery(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, ""
        )
        for (i in 0 until imagecursor.count) {
            imagecursor.moveToPosition(i)
            val dataColumnIndex =
                imagecursor.getColumnIndex(MediaStore.Images.Media.DATA)
            list.add(imagecursor.getString(dataColumnIndex))
        }

        mAdapter = GalleryAdapter(requireActivity() as BaseActivity, list) {it,item->
            imageUri = item
            dialog.dismiss()
            if (it==0){
                openCamera()
            }
            else{
                binding.rlContinue.tvNext.setBackgroundColor(requireContext().getColor(R.color.app_color))
                binding.rlContinue.tvNext.isEnabled = true
                binding.rlBeforeImg.visibility = View.GONE
                binding.rlAfterImg.visibility = View.VISIBLE
                binding.tvUploadLabel.text = "Photo Uploaded"
                binding.tvDesc.text = "Delete to upload another photo."
                Glide.with(requireActivity())
                    .load(imageUri)
                    .into(binding.ivAfterPic)
            }
        }
        rvItems.layoutManager =
            GridLayoutManager(requireContext(), 3)
        rvItems.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()

//        btn.setOnClickListener {
//            list.add(
//                PaymentModel(
//                    R.drawable.ic_card_pattern,
//                    R.drawable.ic_vector_bin,
//                    "Card cannot be deleted while you have a running contract."
//                )
//            )
//            dialog.dismiss()
//            Adapter?.notifyDataSetChanged()
//        }

//        rlCancel.setOnClickListener {
//            dialog.dismiss()
//        }


    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === CAMERA_REQUEST && resultCode === Activity.RESULT_OK) {
            binding.rlContinue.tvNext.setBackgroundColor(requireContext().getColor(R.color.app_color))
            binding.rlContinue.tvNext.isEnabled = true
            binding.rlBeforeImg.visibility = View.GONE
            binding.rlAfterImg.visibility = View.VISIBLE
            binding.tvUploadLabel.text = "Photo Uploaded"
            binding.tvDesc.text = "Delete to upload another photo."
            Glide.with(requireActivity())
                .load(data?.extras?.get("data"))
                .into(binding.ivAfterPic)
        }

    }

    private fun openCamera(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST)
    }
}