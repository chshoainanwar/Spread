package com.dev.spread.fragments.currentads

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.spread.R
import com.dev.spread.base.DeleteVideoDialogCurrent
import com.dev.spread.databinding.FragCurrentAdsBinding

class CurrentAdsFragment : Fragment() {
    private var _binding: FragCurrentAdsBinding? = null
    private val binding get() = _binding!!
    private var clicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragCurrentAdsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTopBar()
        showHideEditDelete()

        binding.ivEdit.setOnClickListener {
            fromCurrentAds = "ToEditAds"
            val action = R.id.actionCurrentAdsToCreateAds
            findNavController().navigate(action)
        }

        binding.ivDelete.setOnClickListener {
            val dialog = DeleteVideoDialogCurrent()
            dialog.show(childFragmentManager, dialog.toString())
        }

    }

    override fun onResume() {
        super.onResume()
        setTopBar()
        showHideEditDelete()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.root
    }

    private fun showHideEditDelete(){
        binding.ivEditDelete.setOnClickListener {
            when(clicked){
                false->{
                    binding.rlIcon.visibility = View.VISIBLE
                    clicked = true
                }
                true->{
                    binding.rlIcon.visibility = View.GONE
                    clicked = false
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTopBar(){
        binding.rlTopBar.tvText.text = "Current Advertisement"
        binding.rlTopBar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object{
        var fromCurrentAds = ""
    }

}