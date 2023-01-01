package com.dev.spread.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.spread.R
import com.dev.spread.base.BaseFragment
import com.dev.spread.databinding.FragmentDashboardBinding
import com.dev.spread.fragments.dialouge.JobOfferDialouge
import com.dev.spread.fragments.dialouge.joblistfilter
import com.dev.spread.fragments.dialouge.swipelimitation
import com.mindinventory.circularcardsstackview.data.CardModel
import com.mindinventory.circularcardsstackview.listener.OnPageChangeListener


class Dashboard : BaseFragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        init()
//        setHeader()

        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         oncreate = "oncreate"
        val manualDialogue = swipelimitation()
        manualDialogue.show(mActivityItem.supportFragmentManager, "MANUAL")
    }


//    private fun setHeader() {
//        _binding?.topBar?.messages?.setOnClickListener {
//            findNavController().navigate(R.id.action_dashboard_to_message2)
//        }
//    }

    private fun init() {
        binding.topBar.clMain.visibility = View.GONE
        binding.topBar.dashboard.visibility = View.VISIBLE
        binding.topBar.view.visibility = View.GONE
        setupCardActions()
        setCardItems()
        _binding?.btnOptionSecond?.setOnClickListener {
            val manualDialogu = JobOfferDialouge()
            manualDialogu.show(mActivityItem.supportFragmentManager, "MANUAL")
        }
    }
    private fun setCardItems() {
        _binding?.cardStack?.setUpCardStack(getItemList(), object : OnPageChangeListener {
            override fun onPageScrolled(position: Int) {
                if(position==3){
                        val manualDialogue = swipelimitation()
                        manualDialogue.show(mActivityItem.supportFragmentManager, "MANUAL")
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        }

        )
        _binding?.cardStack?.callBackForDummyForNow={


            val dialogue = joblistfilter()
            dialogue.show(childFragmentManager, dialogue.tag.toString())

        }
    }

    /**
     * Uses for card actions options setup
     */
    private fun setupCardActions() {

        _binding?.btnOptionFirst?.setImageResource(R.drawable.cross)
        _binding?.btnOptionSecond?.setImageResource(R.drawable.tick)


//        binding.cardStack.setActionOptions(
//            firstButtonResourceId = R.drawable.cross,
//            secondButtonResourceId = R.drawable.tick,
//            object : CardActionListener {
//                override fun onFirstButtonOptionClick(position: Int) {
//
//                }
//
//                override fun onSecondButtonOptionClick(position: Int) {
//
//                }
//            }
//        )
    }
    /**
     * Uses for prepare dummy list
     */
    private fun getItemList(): ArrayList<CardModel> {
        return ArrayList<CardModel>().apply {
            add(
                CardModel(
                    primaryTitle = "Promotion restaurant",
                    secondaryTitle = "10,000 DH",
                    image = R.drawable.backgroundimage
                )
            )
            add(
                CardModel(
                    primaryTitle = "Promotion restaurant",
                    secondaryTitle = "10,000 DH",
                    image = R.drawable.backgroundimage
                )
            )
            add(
                CardModel(
                    primaryTitle = "Promotion restaurant",
                    secondaryTitle = "10,000 DH",
                    image = R.drawable.backgroundimage
                )
            )
            add(
                CardModel(
                    primaryTitle = "Promotion restaurant",
                    secondaryTitle = "10,000 DH",
                    image = R.drawable.backgroundimage
                )
            )
            add(
                CardModel(
                    primaryTitle = "Promotion restaurant",
                    secondaryTitle = "10,000 DH",
                    image = R.drawable.backgroundimage
                )
            )
            add(
                CardModel(
                    primaryTitle = "Promotion restaurant",
                    secondaryTitle = "10,000 DH",
                    image = R.drawable.backgroundimage
                )
            )
        }
    }


    companion object{
        var oncreate = ""
    }

}