package com.dev.spread.fragments.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.fragments.craeteads.RecordVideoFragment.Companion.checkVideo
import com.dev.spread.fragments.craeteads.SelectPriceFragment.Companion.toWelcome
import com.dev.spread.R
import com.dev.spread.databinding.FragWelcomeBinding
import com.dev.spread.fragments.influencer.Influencer
import com.dev.spread.fragments.influencer.Influencer.Companion.toContracts
import com.google.android.material.bottomsheet.BottomSheetDialog

class WelcomeFragment : Fragment() {
    private var _binding: FragWelcomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        when(toWelcome){
            "PostAds"->{
                fromWelcomeToContract = ""
                binding.rlCreateAdvertise.tvNext.text = "Next"
                binding.tvWelcome.text = "Advertisement Posted!"
                binding.tvDesc.text = "Influencers will appear soon to help you\nspread your business."
                binding.rlCreateAdvertise.tvNext.setOnClickListener {
//                    findNavController().navigate(
//                        R.id.actionWelcomeToInfluencerDashboard, null,
//                        NavOptions.Builder().setPopUpTo(R.id.influencer, true).build()
//                    )

                    val action = R.id.actionWelcomeToInfluencerDashboard
                    findNavController().navigate(action)
                }
            }
            "RePostAds"->{
                fromWelcomeToContract = ""
                binding.rlCreateAdvertise.tvNext.text = "Next"
                binding.tvWelcome.text = "Advertisement Reposted!"
                binding.tvDesc.text = "Influencers will appear soon to help you\nspread your business."
                binding.rlCreateAdvertise.tvNext.setOnClickListener {
                    checkVideo = ""
                    repostAds= ""
                    val action = R.id.actionWelcomeToInfluencerDashboard
                    findNavController().navigate(action)
                }
            }

            "FromCurrentAds"->{
                fromWelcomeToContract = ""
                binding.rlCreateAdvertise.tvNext.text = "Home"
                binding.tvWelcome.text = "Deleted"
                binding.tvDesc.text = "Advertisement deleted successfully."
                binding.rlCreateAdvertise.tvNext.setOnClickListener {
                    repostAds = "RepostAds"
                    checkVideo = ""
                    toContracts = "ForHome"
                    fromRate = "FromDeleted"
                    val action = R.id.actionWelcomeToContracts
                    findNavController().navigate(action)
                }
            }
            "FromContracts"->{
                fromWelcomeToContract = "paymentReleased"
                binding.rlCreateAdvertise.tvNext.text = "Rate influencer"
                binding.tvWelcome.text = "Payment Released!"
                binding.tvDesc.text = "Thank you for using Spread."
                binding.rlCreateAdvertise.tvNext.setOnClickListener {
                    rateBottomSheetDialog()
                }
            }
            "RequestRefund"->{
                fromWelcomeToContract = "requestRefunded"
                binding.rlCreateAdvertise.tvNext.text = "Home"
                binding.tvWelcome.text = "Refund Request Submitted"
                binding.tvDesc.text = "The influencer will be notified."
                binding.rlCreateAdvertise.tvNext.setOnClickListener {
                    fromRate = "AfterRequest"
                    val action = R.id.actionWelcomeToContracts
                    findNavController().navigate(action)
                }
            }
            "fromInfluencer"->{
                fromWelcomeToContract = ""
                binding.rlCreateAdvertise.tvNext.text = "Start your first job"
                binding.tvWelcome.text = "You’re In!"
                binding.tvDesc.text = "Welcome to Spread."
                binding.rlCreateAdvertise.tvNext.setOnClickListener {
//                    val action = R.id.actionWelcomeToContracts
//                    findNavController().navigate(action)
                }
            }

            else->{
                fromWelcomeToContract = ""
                binding.rlCreateAdvertise.tvNext.text = "Create your first advertisement"
                binding.rlCreateAdvertise.tvNext.setOnClickListener {
                    val action = R.id.actionWelcomeToCreateAds
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun rateBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        val layoutDialog =
            LayoutInflater.from(requireContext()).inflate(R.layout.rate_doalog, null, false)
        dialog.setContentView(layoutDialog)
        dialog.setCancelable(true)
        dialog.show()

        var etFeedback = layoutDialog.findViewById<EditText>(R.id.etFeedback)
        var tvRating = layoutDialog.findViewById<RatingBar>(R.id.tvRating)
        var tvNext = layoutDialog.findViewById<TextView>(R.id.tvNext)

        tvNext.isEnabled = false

        tvRating.setOnRatingBarChangeListener(RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->

            if (rating > 2) {
                tvNext.setBackgroundResource(R.color.app_color)
                tvNext.isEnabled = true
            } else {
                tvNext.setBackgroundResource(R.color.view_bar)
                tvNext.isEnabled = false
            }

        })

        tvNext.setOnClickListener {
            dialog.dismiss()
            fromRate = "AfterPayment"
            val action = R.id.actionWelcomeToContracts
            findNavController().navigate(action)
        }

        }

    companion object{
        var repostAds = ""
        var fromRate = ""
        var fromWelcomeToContract = ""
    }


}