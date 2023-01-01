package com.dev.spread.fragments.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.spread.fragments.auth.RegisterAsFragment.Companion.registerAs
import com.dev.spread.fragments.auth.SignUpFragment.Companion.checkOTP
import com.dev.spread.R
import com.dev.spread.databinding.FragOtpBinding


class OtpFragment : Fragment() {
    private var _binding: FragOtpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btn.tvNext.text = "Verify account"
        binding.btn.tvNext.setOnClickListener {
            when (registerAs) {
                "Owner" -> {
                    when (checkOTP) {
                        "FromSignUp" -> {
                            binding.tvLAbel2.text =
                                "A verification code has been sent to your email address."
//                            binding.btn.tvNext.setOnClickListener {
                            val action = R.id.actionOtpToWelcome
                            findNavController().navigate(action)
//                            }
                        }

                        "FromForgotPassword" -> {
//                            binding.btn.tvNext.setOnClickListener {
//                                val action = R.id.actionOtpToAddPassword
                            val action = R.id.actionToVerified
                            findNavController().navigate(action)
//                            }
                        }
                    }
                }

                "Influencer" -> {
                    when (checkOTP) {
                        "FromSignUp" -> {
                            binding.tvLAbel2.text =
                                "We have sent a verification code to your email address."
//                            binding.btn.tvNext.setOnClickListener {
                            val action = R.id.actionOtpToSnapchat
                            findNavController().navigate(action)
//                            }
                        }

                        "FromForgotPassword" -> {
                            binding.tvLAbel2.text =
                                "We have sent the verification code to your emaIl address."
//                            binding.btn.tvNext.setOnClickListener {
//                            val action = R.id.actionOtpToAddPassword
//                            findNavController().navigate(action)
//                            }
                        }
                    }
                }
                else -> {
//                    val action = R.id.actionOtpToAddPassword
//                    findNavController().navigate(action)
                    val action = R.id.actionToVerified
                    findNavController().navigate(action)
                }
            }
        }

        binding?.ivBackArrow?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}