package com.dev.spread.fragments.setting.contracts

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.CompoundButtonCompat
import androidx.navigation.fragment.findNavController
import com.dev.spread.fragments.craeteads.SelectPriceFragment.Companion.toWelcome
import com.dev.spread.R
import com.dev.spread.base.BaseFragment
import com.dev.spread.databinding.FragRequestRefundBinding

class RequestRefundFragment : BaseFragment() {
    private var _binding: FragRequestRefundBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragRequestRefundBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopBar()
        binding.rlSubmit.tvNext.setOnClickListener {
            toWelcome = "RequestRefund"
            val action = R.id.actionRequestRefundToWelcome
            findNavController().navigate(action)
        }

    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        setTopBar()
        binding.cb1.setOnClickListener {
            if (binding.cb1.isChecked){
                CompoundButtonCompat.setButtonTintList(binding.cb1, ColorStateList.valueOf(mActivityItem.getColor(R.color.app_color)));
            }else{
                CompoundButtonCompat.setButtonTintList(binding.cb1, ColorStateList.valueOf(mActivityItem.getColor(R.color.box_color)));
            }
        }
        binding.cb2.setOnClickListener {
            if (binding.cb2.isChecked){
                CompoundButtonCompat.setButtonTintList(binding.cb2, ColorStateList.valueOf(mActivityItem.getColor(R.color.app_color)));
            }else{
                CompoundButtonCompat.setButtonTintList(binding.cb2, ColorStateList.valueOf(mActivityItem.getColor(R.color.box_color)));
            }
        }
        binding.cb3.setOnClickListener {
            if (binding.cb3.isChecked){
                CompoundButtonCompat.setButtonTintList(binding.cb3, ColorStateList.valueOf(mActivityItem.getColor(R.color.app_color)));
            }else{
                CompoundButtonCompat.setButtonTintList(binding.cb3, ColorStateList.valueOf(mActivityItem.getColor(R.color.box_color)));
            }
        }
        binding.cb4.setOnClickListener {
            if (binding.cb4.isChecked){
                CompoundButtonCompat.setButtonTintList(binding.cb4, ColorStateList.valueOf(mActivityItem.getColor(R.color.app_color)));
            }else{
                CompoundButtonCompat.setButtonTintList(binding.cb4, ColorStateList.valueOf(mActivityItem.getColor(R.color.box_color)));
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun setTopBar(){
        binding.rlTopBar.tvText.text = "Request Refund"
        binding.rlSubmit.tvNext.text = "Submit"
        binding.rlTopBar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}