package com.dev.spread.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.spread.R
import com.dev.spread.databinding.DelVideoDialogBinding

class PayDialog : BaseDialogueFragment() {
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
        binding.rlDelete.tvNext.text = "Pay"
        binding.rlCancel.tvNext.text = "Cancel"
        binding.rlCancel.tvNext.setBackgroundColor(requireContext().getColor(R.color.bg_cancel))
        binding.tvDesc.text = "Are you sure you want to release the\npayment?"
        val param = binding.rlMain.layoutParams
        param.width =
            binding.rlMain.context.resources.getDimension(com.intuit.sdp.R.dimen._300sdp).toInt()
        binding.rlMain.layoutParams = param

        binding.rlCancel.tvNext.setOnClickListener {
            dialog?.dismiss()
        }
        binding.rlDelete.tvNext.setOnClickListener {
            onCallBackForRedirection?.invoke()
            dialog?.dismiss()
        }
        isCancelable = false
//        onCallBackForRedirection
    }
}