package com.dev.spread.fragments.setting.contracts

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.dev.spread.fragments.craeteads.SelectPriceFragment.Companion.toWelcome
import com.dev.spread.fragments.influencer.Influencer.Companion.toContracts
import com.dev.spread.fragments.setting.contracts.ContractsFragment.Companion.fromContracts
import com.dev.spread.R
import com.dev.spread.base.BaseFragment
import com.dev.spread.base.CantPayDialog
import com.dev.spread.base.PayDialog
import com.dev.spread.databinding.FragContractDertailsBinding
import com.dev.spread.fragments.auth.WelcomeFragment
import com.dev.spread.fragments.messages.MessageDetail
import com.dev.spread.fragments.messages.MessageDetail.Companion.toContractsRequested

class ContractDetailsFragment : BaseFragment() {
    private var _binding: FragContractDertailsBinding? = null
    private val binding get() = _binding!!
    private var clicked = false
    var urlSn = "https://snapchat.com/add/{snapchat_page_name}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(requireActivity(), object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
//                    mActivityItem.onBackPressed()
                            if (isEnabled) {
                                isEnabled = false
                            try {
                                findNavController().navigateUp()
                            } catch (_: IllegalStateException) {
                                mActivityItem.onBackPressed()

                                }
                            }
                }
            }
            )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragContractDertailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopBar()
        showHideEditDelete()

        binding.ivPay.setOnClickListener {
            if (toContracts == "ForHome"){
                showPayDialog()
            }else{
                showCantPayDialog()
            }
        }

        binding.ivRequest.setOnClickListener {
            val action = R.id.actionContractToRequestRefund
            findNavController().navigate(action)
        }

        binding.ivMessage.setOnClickListener {
            val action = R.id.actionContractDetailsToMessagesDetails
            findNavController().navigate(action)
        }

        binding.ivSnap.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "*/*"
                intent.setPackage("com.snapchat.android")
                startActivity(Intent.createChooser(intent, "Open Snapchat"))
            } catch (anfe: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlSn)))
            }
        }

        when (fromContracts) {
            "Refunded" -> {
                binding.tvRefunded.visibility = View.VISIBLE
                binding.tvRefunded.text = "Refunded"
                binding.cvPalyer.visibility = View.GONE
                binding.rlPay.visibility = View.VISIBLE
                binding.ivDialog.visibility = View.GONE
                binding.tvEndDate.visibility = View.VISIBLE

            }
            "Disputed" -> {
                binding.tvRefunded.visibility = View.VISIBLE
                binding.tvRefunded.text = "Disputed"
                binding.cvPalyer.visibility = View.GONE
                binding.rlPay.visibility = View.VISIBLE
                binding.ivDialog.visibility = View.GONE
                binding.tvEndDate.visibility = View.VISIBLE
            }
            "Running" -> {
                binding.tvRefunded.visibility = View.GONE
                binding.ivDialog.visibility = View.VISIBLE
                binding.tvEndDate.visibility = View.GONE
            }
            else ->{
                binding.tvRefunded.visibility = View.GONE
                binding.cvPalyer.visibility = View.GONE
                binding.rlPay.visibility = View.VISIBLE
                binding.ivDialog.visibility = View.GONE
                binding.tvEndDate.visibility = View.VISIBLE
            }
        }

        if (toContracts == "ForHome"){
            if(toContractsRequested == "FromPaymentRequested"){
                toContractsRequested = ""
                binding.rlIcon.visibility = View.VISIBLE
                showPayDialog()
                binding.ivDialog.visibility = View.VISIBLE
            }else{
                binding.rlIcon.visibility = View.GONE
            }
        }

    }

    override fun onResume() {
        super.onResume()
        setTopBar()
    }

    private fun showHideEditDelete() {
        binding.ivDialog.setOnClickListener {
            when (clicked) {
                false -> {
                    binding.rlIcon.visibility = View.VISIBLE
                    clicked = true
                }
                true -> {
                    binding.rlIcon.visibility = View.GONE
                    clicked = false
                }
            }
        }
    }

    private fun showPayDialogFromRequest() {
        val frag = PayDialog()
        frag.onCallBackForRedirection = {
            toWelcome = "FromContracts"
            val action = R.id.actionContractToWelcome
            findNavController().navigate(action)
        }
        frag.show(childFragmentManager, frag.toString())
    }

    private fun showPayDialog() {
        val frag = PayDialog()
        frag.onCallBackForRedirection = {
            toWelcome = "FromContracts"
            val action = R.id.actionContractToWelcome
            findNavController().navigate(action)
        }
        frag.show(childFragmentManager, frag.toString())
    }

    private fun showCantPayDialog() {
        val frag = CantPayDialog()
        frag.onCallBackForRedirection = {
            binding.rlIcon.visibility = View.GONE
            binding.cvPalyer.visibility = View.GONE
            binding.rlPay.visibility = View.VISIBLE
        }
        frag.show(childFragmentManager, frag.toString())
    }

    @SuppressLint("SetTextI18n")
    private fun setTopBar() {
        binding.rlTopBar.tvText.text = "Contract"
        binding.rlTopBar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}