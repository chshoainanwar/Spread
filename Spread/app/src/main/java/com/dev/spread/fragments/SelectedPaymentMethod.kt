package com.dev.spread.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.databinding.FragmentSelectedPaymentMethodBinding


class SelectedPaymentMethod : Fragment() {


    private var _binding: FragmentSelectedPaymentMethodBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectedPaymentMethodBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvText.visibility = View.GONE

        var args = getArguments()
        var index = args?.getString("flag")
        when (index) {

            "paypal" -> {
                binding.topBar.tvText.visibility = View.GONE
                binding?.image?.setImageResource(R.drawable.paypal)
            }
            "applepay" -> {
                binding?.image?.setImageResource(R.drawable.applepay)
                binding.topBar.tvText.visibility = View.GONE
            }
            "mastercard" -> {binding?.addnewcard?.visibility = View.VISIBLE
                binding?.image?.setImageResource(R.drawable.ic_card_pattern)
            }
            else -> {

            }
        }

        binding.topBar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
        binding?.sendoffer?.setOnClickListener {
            lol = "lol"
            findNavController().navigate(R.id.action_selectedPaymentMethod_to_jobofferSent)
        }
        binding?.addnewcard?.setOnClickListener {
              val bundle = Bundle()
             bundle.putString("card", "card")
            fromMessages = "card"
            findNavController().navigate(R.id.action_selectedPaymentMethod_to_paymentFragment,bundle)
        }
    }
    companion object{
        var lol = ""
        var fromMessages = ""
    }
}