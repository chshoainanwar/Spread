package com.dev.spread.fragments.setting.payments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.spread.R
import com.dev.spread.base.CardDeleteDialogue
import com.dev.spread.databinding.FragPaymentBinding
import com.dev.spread.fragments.SelectedPaymentMethod
import com.dev.spread.fragments.SelectedPaymentMethod.Companion.fromMessages
import com.google.android.material.bottomsheet.BottomSheetDialog


class PaymentFragment : Fragment() {
    private var _binding: FragPaymentBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<PaymentModel>()
    private var Adapter: PaymentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topBar.tvText.text = "Payments"
        binding.topBar.ivBackArrow.setOnClickListener {
            fromMessages = ""
            findNavController().navigateUp()
        }

        binding.cvaddcard.setOnClickListener {
            assignBottomSheetDialog()
        }
        setRecycler()
        setData()

        if (fromMessages == "card") {
            binding.topBar.tvText.visibility = View.GONE
            binding.tvLabel.text = "Payment"
            binding.tvAddNewCard.visibility = View.VISIBLE
            binding.cvaddcard.visibility = View.GONE
            binding.cvaddcard2.visibility = View.GONE
            binding.tvAddNewCard.setOnClickListener {
                assignBottomSheetDialog()
            }
        }

    }

    private fun assignBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        val layoutDialog =
            LayoutInflater.from(requireContext()).inflate(R.layout.addnewcard_dialogue, null, false)
        dialog.setContentView(layoutDialog)
        dialog.setCancelable(true)
        dialog.show()
//        var tvUser=layoutDialog.findViewById<TextView>(R.id.tvConversateAdmin)
        var btn = layoutDialog.findViewById<CardView>(R.id.cv_btn)
        var visa = layoutDialog.findViewById<LinearLayoutCompat>(R.id.llYes)
        var visaimage = layoutDialog.findViewById<ImageView>(R.id.visaimage)
        var visatext = layoutDialog.findViewById<TextView>(R.id.tvYes)

        var master = layoutDialog.findViewById<LinearLayoutCompat>(R.id.llNo)
        var mastertext = layoutDialog.findViewById<TextView>(R.id.tvNo)

        visa.setOnClickListener {
            visa.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.btn_background_appcolor)
            visatext.setTextColor(requireContext().getColor(R.color.white))
            visaimage.setColorFilter(requireContext().getColor(R.color.white))
            master.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.btn_background)
            mastertext.setTextColor(requireContext().getColor(R.color.black))
        }
        master.setOnClickListener {
            master.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.btn_background_appcolor)
            visa.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_background)
            mastertext.setTextColor(requireContext().getColor(R.color.white))
            visatext.setTextColor(requireContext().getColor(R.color.black))
            visaimage.setColorFilter(requireContext().getColor(R.color.app_color))

        }

        
        btn.setOnClickListener {
            list.add(
                PaymentModel(
                    R.drawable.ic_card_pattern,
                    R.drawable.ic_vector_bin,
                    "Card cannot be deleted while you have a running contract."
                )
            )
            dialog.dismiss()
            Adapter?.notifyDataSetChanged()
        }

//        rlCancel.setOnClickListener {
//            dialog.dismiss()
//        }


    }

    private fun setRecycler() {
        Adapter = PaymentAdapter(requireContext() as AppCompatActivity, list) {
            val dialog = CardDeleteDialogue()
            dialog.onCallBackForRedirection = {
                list.removeAt(it)
                Adapter?.notifyDataSetChanged()
            }
            dialog.show(childFragmentManager, dialog.toString())
        }
        binding.rvpayment.layoutManager = LinearLayoutManager(requireContext())
        binding.rvpayment.adapter = Adapter
    }

    private fun setData() {
        list.clear()
        list.add(
            PaymentModel(
                R.drawable.ic_card_pattern,
                R.drawable.ic_vector_bin,
                "Card cannot be deleted while you have a running contract."
            )
        )


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}