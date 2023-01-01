package com.dev.spread.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragmentPaymentInfoBinding

class PaymentInfo : Fragment() {

    private var _binding: FragmentPaymentInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        asRegisterSelection()


        binding.topBar.tvText.visibility = View.GONE
        binding.topBar.ivBackArrow?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.paypal?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("flag","paypal")
          findNavController().navigate(R.id.action_paymentInfo_to_selectedPaymentMethod,bundle)
        }
        binding?.applepay?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("flag","applepay")
          findNavController().navigate(R.id.action_paymentInfo_to_selectedPaymentMethod,bundle)
        }
        binding?.mastercard?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("flag","mastercard")
          findNavController().navigate(R.id.action_paymentInfo_to_selectedPaymentMethod,bundle)
        }



    }

    fun asRegisterSelection(){
        binding.llYes.setOnClickListener {
            binding.llYes.background = ContextCompat.getDrawable(requireContext(),R.drawable.btn_background_appcolor)
            binding.tvYes.setTextColor(requireContext().getColor(R.color.white))
            binding.visa.setColorFilter(requireContext().getColor(R.color.white))
            binding.llNo.background = ContextCompat.getDrawable(requireContext(),R.drawable.btn_background)
            binding.tvNo.setTextColor(requireContext().getColor(R.color.black))
        }
        binding.llNo.setOnClickListener {
            binding.llNo.background = ContextCompat.getDrawable(requireContext(),R.drawable.btn_background_appcolor)
            binding.llYes.background = ContextCompat.getDrawable(requireContext(),R.drawable.btn_background)
            binding.tvNo.setTextColor(requireContext().getColor(R.color.white))
            binding.tvYes.setTextColor(requireContext().getColor(R.color.black))
            binding.visa.setColorFilter(requireContext().getColor(R.color.app_color))

        }
    }


}