package com.dev.spread.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.DelVideoDialogBinding
import com.dev.spread.fragments.craeteads.RecordVideoFragment.Companion.checkVideo

class DeleteVideoDialog(val callback: ((Boolean) -> Unit)) : BaseDialogueFragment() {
    public var flag = ""
    private lateinit var binding: DelVideoDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DelVideoDialogBinding.inflate(inflater)

        return binding.root
    }


    var onCallBackForRedirection: (() -> Unit)? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rlDelete.tvNext.text = "Delete"
        binding.rlDelete.tvNext.setBackgroundColor(requireContext().getColor(R.color.red))
        binding.rlCancel.tvNext.text = "Cancel"

        val param = binding.rlMain.layoutParams
        param.width =
            binding.rlMain.context.resources.getDimension(com.intuit.sdp.R.dimen._300sdp).toInt()
        binding.rlMain.layoutParams = param

        binding.rlCancel.tvNext.setOnClickListener {
            dismiss()
        }
        binding.rlDelete.tvNext.setOnClickListener {
            if (flag.equals("hide")) {
                dismiss()
                callback.invoke(true)
            } else {
                checkVideo = ""
                findNavController().navigate(R.id.actionCreateAdsToSame)
            }

        }
        isCancelable = false
//        onCallBackForRedirection
    }
}