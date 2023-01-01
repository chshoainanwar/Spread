package com.dev.spread.fragments.setting.contracts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.amintopup.base.viewVisible
import com.dev.spread.R
import com.dev.spread.base.BaseFragment
import com.dev.spread.databinding.FragContractsBinding
import com.dev.spread.fragments.auth.WelcomeFragment.Companion.fromRate
import com.dev.spread.fragments.auth.WelcomeFragment.Companion.fromWelcomeToContract
import com.dev.spread.fragments.influencer.Influencer.Companion.toContracts
import com.dev.spread.fragments.setting.contracts.adapter.ContractsAdapter
import com.dev.spread.fragments.setting.contracts.model.ContractsModel
import com.dev.spread.utils.CountDownTimerViewDisplay
import com.google.android.material.bottomsheet.BottomSheetDialog

class ContractsFragment : BaseFragment() {
    private var _binding: FragContractsBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<ContractsModel>()
    private var mAdapter: ContractsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragContractsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val callBackRunnable = Runnable {
        _binding?.advertismentlayout?.visibility = View.VISIBLE
        tvTimerDisplay?.stopCountDown()
        tvTimerDisplay?.setPrefixText(" ")
        tvTimerDisplay?.setSuffixText(" ")
        tvTimerDisplay?.setTime(60000)
        tvTimerDisplay?.startCountDown()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(requireActivity(), object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
//                    mActivityItem.onBackPressed()

                    when (fromWelcomeToContract) {
                        "paymentReleased" -> {
                            mActivityItem.finish()
                        }
                        "requestRefunded" -> {
                            mActivityItem.finish()
//                            val action = R.id.actionContractToInfluencer
//                            findNavController().navigate(action)
                        }
                        "Same" ->{
                            fromWelcomeToContract = ""
                            toContracts = "ForHome"
                            checkWhichContractScreen()
                            mAdapter?.notifyDataSetChanged()
                        }
                        "notGoBack" -> {
                            mActivityItem.finish()
                        }
                        else -> {
                            if (isEnabled) {
                                isEnabled = false
                                try {
                                    mActivityItem.onBackPressed()
                                } catch (_: IllegalStateException) {

                                }
                            }
                        }
                    }

                }
            }
            )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createBtn.tvNext.text = "Create an Advertisement"
        binding.createBtn.tvNext.setOnClickListener {
            val action = R.id.action_contractsFragment_to_createAdsFragment
            findNavController().navigate(action)
        }

        setTopBar()
        setHomeContractsTopBar()
        setRecyclerView()

        handler.removeCallbacks(callBackRunnable)
        handler.postDelayed(callBackRunnable, 2000)

        tvTimerDisplay = CountDownTimerViewDisplay(requireContext())
        tvTimerDisplay?.setOnTimerListener(object : CountDownTimerViewDisplay.TimerListener {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                _binding?.advertismentlayout?.visibility = View.VISIBLE
            }

        })

        binding.rlRunningContracts.setOnClickListener {
            fromContracts = "Running"
            val action = R.id.actionContractToDetails
            findNavController().navigate(action)
        }

        _binding?.advertismentlayout?.viewVisible()

        checkWhichContractScreen()

        tvTimerDisplay = CountDownTimerViewDisplay(requireContext())
        tvTimerDisplay?.setOnTimerListener(object : CountDownTimerViewDisplay.TimerListener {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                _binding?.advertismentlayout?.viewVisible()
            }

        })
        tvTimerDisplay?.stopCountDown()
        tvTimerDisplay?.setPrefixText(" ")
        tvTimerDisplay?.setSuffixText(" ")
        tvTimerDisplay?.setTime(30000)
        tvTimerDisplay?.startCountDown()

    }

    private fun checkWhichContractScreen() {
        if (toContracts == "ForHome") {
            when (fromRate) {
                "AfterPayment" -> {
                    binding.rlContractHomeBar.visibility = View.VISIBLE
                    binding.rlTop.visibility = View.GONE
                    binding.tvLabel.visibility = View.GONE
                    binding.tvLabel2.visibility = View.GONE
                    binding.tvTotalAmount.visibility = View.GONE
                    binding.tvHome.visibility = View.VISIBLE
                    binding.tvHomeRunning.visibility = View.GONE
                    binding.tvCompletedLabel.visibility = View.VISIBLE
                    binding.rlRunningContracts.visibility = View.GONE
                    binding.llBtmView.visibility = View.VISIBLE
                    setHomeDummyData()
//                    rateBottomSheetDialog()
                }
                "AfterRequest" -> {
                    binding.rlContractHomeBar.visibility = View.VISIBLE
                    binding.rlTop.visibility = View.GONE
                    binding.tvLabel.visibility = View.GONE
                    binding.tvLabel2.visibility = View.GONE
                    binding.tvTotalAmount.visibility = View.GONE
                    binding.tvHome.visibility = View.VISIBLE
                    binding.tvHomeRunning.visibility = View.GONE
                    binding.tvCompletedLabel.visibility = View.VISIBLE
                    binding.rlRunningContracts.visibility = View.GONE
                    binding.llBtmView.visibility = View.VISIBLE
                    setHomeDummyData()
//                    rateBottomSheetDialogForRequest()
                }
                "FromDeleted" -> {
                    binding.rlContractHomeBar.visibility = View.VISIBLE
                    binding.rlTop.visibility = View.GONE
                    binding.tvLabel.visibility = View.GONE
                    binding.tvLabel2.visibility = View.GONE
                    binding.tvTotalAmount.visibility = View.GONE
                    binding.tvHome.visibility = View.VISIBLE
                    binding.tvHomeRunning.visibility = View.GONE
                    binding.tvCompletedLabel.visibility = View.VISIBLE
                    binding.rlRunningContracts.visibility = View.GONE
                    binding.llBtmView.visibility = View.VISIBLE
                    setHomeDummyData()
//                    rateBottomSheetDialogForRequest()
                }
                else -> {
                    binding.rlContractHomeBar.visibility = View.VISIBLE
                    binding.rlTop.visibility = View.GONE
                    binding.tvLabel.visibility = View.GONE
                    binding.tvLabel2.visibility = View.GONE
                    binding.tvTotalAmount.visibility = View.GONE
                    binding.tvHome.visibility = View.VISIBLE
                    binding.tvHomeRunning.visibility = View.VISIBLE
                    binding.rlRunningContracts.visibility = View.GONE
                    binding.tvCompletedLabel.visibility = View.VISIBLE
                    binding.llBtmView.visibility = View.VISIBLE
                    setHomeDummyData()
                }
            }
        } else {
            setDummyData()
        }    }

    private fun setHomeContractsTopBar() {
        binding.rlSettings.setOnClickListener {
            val action = R.id.actionContractToSettings
            findNavController().navigate(action)
        }

        binding.rlMessages.setOnClickListener {
            val action = R.id.actionContractToMessages
            findNavController().navigate(action)
        }

        binding.rlContracts.setOnClickListener {
            toContracts = ""
            fromWelcomeToContract = "Same"
            val action = R.id.actionContractToContracts
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        setTopBar()
    }

    private fun setRecyclerView() {
        list.clear()
        mAdapter = ContractsAdapter(this, list)
        binding.rvContracts.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvContracts.adapter = mAdapter
    }

    private fun setDummyData() {
        list.clear()
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.iv2))
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.men1))
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.iv2))
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.iv2))
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.men1))
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.iv2))
    }

    private fun setHomeDummyData() {
        list.clear()
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.iv2))
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.men1))
        list.add(ContractsModel("Cameron W.", "27 Aug 2022", "$10,000", R.drawable.iv2))
    }

    @SuppressLint("SetTextI18n")
    private fun setTopBar() {
        binding.rlTopBar.tvText.text = "Contracts"
        binding.rlTopBar.ivBackArrow.setOnClickListener {
            if (fromWelcomeToContract == "Same") {
                fromWelcomeToContract = ""
                toContracts = "ForHome"
                checkWhichContractScreen()
                mAdapter?.notifyDataSetChanged()
//                val action = R.id.actionContractToContracts
//                findNavController().navigate(action)
            } else {
                findNavController().popBackStack()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun rateBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        val layoutDialog =
            LayoutInflater.from(requireContext()).inflate(R.layout.rate_doalog, null, false)
        dialog.setContentView(layoutDialog)
        dialog.setCancelable(true)
        dialog.show()

//        var etFeedback = layoutDialog.findViewById<EditText>(R.id.etFeedback)
        val tvRating = layoutDialog.findViewById<RatingBar>(R.id.tvRating)
        val tvNext = layoutDialog.findViewById<TextView>(R.id.tvNext)
        tvNext.isEnabled = false

        tvRating.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->

                if (rating > 2) {
                    tvNext.setBackgroundResource(R.color.app_color)
                    tvNext.isEnabled = true
                } else {
                    tvNext.setBackgroundResource(R.color.view_bar)
                    tvNext.isEnabled = false
                }

            }

        tvNext.setOnClickListener {
            toReview = "FromRating"
            dialog.dismiss()
            val action = R.id.actionContractToReviews
            findNavController().navigate(action)

        }

    }

    @SuppressLint("SetTextI18n", "InflateParams")
    private fun rateBottomSheetDialogForRequest() {
        val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        val layoutDialog =
            LayoutInflater.from(requireContext()).inflate(R.layout.rate_doalog, null, false)
        dialog.setContentView(layoutDialog)
        dialog.setCancelable(true)
        dialog.show()

//        var etFeedback = layoutDialog.findViewById<EditText>(R.id.etFeedback)
        val tvRating = layoutDialog.findViewById<RatingBar>(R.id.tvRating)
        val tvNext = layoutDialog.findViewById<TextView>(R.id.tvNext)
        val tvlabel1 = layoutDialog.findViewById<TextView>(R.id.tvlabel1)
        tvlabel1.text = "Rate Influenced"
        tvNext.isEnabled = false

        tvRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            if (rating > 2) {
                tvNext.setBackgroundResource(R.color.app_color)
                tvNext.isEnabled = true
            } else {
                tvNext.setBackgroundResource(R.color.view_bar)
                tvNext.isEnabled = false
            }

        }

        tvNext.setOnClickListener {
            toReview = "FromRating"
            dialog.dismiss()
            val action = R.id.actionContractToReviews
            findNavController().navigate(action)
        }

    }

    companion object {
        var fromContracts = ""
        var toReview = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}