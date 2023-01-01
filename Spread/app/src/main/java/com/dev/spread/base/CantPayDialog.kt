package com.dev.spread.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.spread.databinding.DialogCantPayBinding

class CantPayDialog : BaseDialogueFragment() {
    private lateinit var binding: DialogCantPayBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCantPayBinding.inflate(inflater)

        return binding.root
    }


    var onCallBackForRedirection: (() -> Unit)? = null
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val param = binding.rlMain.layoutParams
        param.width =
            binding.rlMain.context.resources.getDimension(com.intuit.sdp.R.dimen._300sdp).toInt()
        binding.rlMain.layoutParams = param

        binding.ivCancel.setOnClickListener {
            dismiss()
            onCallBackForRedirection?.invoke()
        }

        isCancelable = false
//        onCallBackForRedirection
    }
}