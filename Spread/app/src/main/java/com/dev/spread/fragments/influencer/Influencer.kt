package com.dev.spread.fragments.influencer

import android.graphics.Canvas
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.amintopup.base.tooltip.SimpleTooltip
import com.dev.spread.fragments.auth.WelcomeFragment.Companion.fromRate
import com.dev.spread.fragments.auth.WelcomeFragment.Companion.repostAds
import com.dev.spread.fragments.currentads.CurrentAdsFragment.Companion.fromCurrentAds
import com.dev.spread.R
import com.dev.spread.base.BaseFragment
import com.dev.spread.databinding.FragInfluencerBinding
import com.dev.spread.fragments.SelectedPaymentMethod
import com.dev.spread.fragments.auth.WelcomeFragment
import com.dev.spread.fragments.auth.WelcomeFragment.Companion.fromWelcomeToContract
import com.dev.spread.fragments.craeteads.SelectPriceFragment
import com.dev.spread.fragments.messages.MessageDetail
import com.dev.spread.fragments.messages.MessageDetail.Companion.toContractsRequested
import com.dev.spread.utils.CountDownTimerViewDisplay
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class Influencer : BaseFragment() {
    private var _binding: FragInfluencerBinding? = null
    private val binding get() = _binding!!
    private var Adapter: Adapter? = null
    private var list = ArrayList<Model>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragInfluencerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(SimpleTooltip.TAG, "Fragment back pressed invoked")
                    // Do custom work here

                        if(SelectPriceFragment.toWelcome.equals("PostAds")){
                            findNavController().navigate(R.id.actionInfluencerToContracts)}

                    else {
                        // if you want onBackPressed() to be called as normal afterwards
                        if (isEnabled) {
                            isEnabled = false
                            try {
                                mActivityItem.onBackPressed()
                            } catch (_: IllegalStateException) {
                            }
                        }
                    }

                }
            })
    }

    private val callBackRunnable = Runnable {
        _binding?.tvBest?.visibility = View.VISIBLE
        _binding?.rvInfluencer?.visibility = View.VISIBLE
        _binding?.rlBottom?.visibility = View.VISIBLE
        _binding?.input?.visibility = View.VISIBLE
        _binding?.tvSoon?.visibility = View.GONE
        _binding?.rlWaiting?.visibility = View.GONE
        _binding?.rlBottom?.visibility = View.VISIBLE
        tvTimerDisplay?.stopCountDown()
        tvTimerDisplay?.setPrefixText(" ")
        tvTimerDisplay?.setSuffixText(" ")
        tvTimerDisplay?.setTime(60000)
        tvTimerDisplay?.startCountDown()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        setRecycler()

        binding.rlSettings.setOnClickListener {
            val action = R.id.action_influencer_to_settingFragment
            findNavController().navigate(action)
        }

        binding.rlAdvertisement.setOnClickListener {
            when (repostAds) {
                "RepostAds" -> {
                    fromCurrentAds = "RepostAds"
                    val action = R.id.actionInfluencerToCreateAds
                    findNavController().navigate(action)
                }
                else -> {
                    val action = R.id.actionInfluencerToCurrentAds
                    findNavController().navigate(action)
                }

            }
        }
        binding.rlMessages.setOnClickListener {
            val action = R.id.action_influencer_to_message2
            findNavController().navigate(action)
        }

        binding.rlContracts.setOnClickListener {
            fromWelcomeToContract = "Same"
            toContracts = ""
            toContractsRequested = ""
            fromRate = ""
            val action = R.id.actionInfluencerToContracts
            findNavController().navigate(action)
        }
        handler.removeCallbacks(callBackRunnable)
        handler.postDelayed(callBackRunnable, 2000)

        tvTimerDisplay = CountDownTimerViewDisplay(requireContext())
        tvTimerDisplay?.setOnTimerListener(object : CountDownTimerViewDisplay.TimerListener {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                _binding?.rlBottom?.visibility = View.GONE
            }

        })
        binding.input.setOnClickListener {
            val action = R.id.action_influencer_to_searchFragment2
            findNavController().navigate(action)
        }


    }

    override fun onResume() {
        super.onResume()

//        binding?.input?.etLabel?.hint = "Search"
//        binding?.input?.etInput?.inputType = InputType.TYPE_CLASS_TEXT
//        binding?.input?.ivImage?.setImageResource(R.drawable.ic_vector_search)
    }

    private fun setRecycler() {
        Adapter = Adapter(requireContext() as AppCompatActivity, list) {
            findNavController().navigate(R.id.action_influencerToDetails)
//            val dialog = RequestedNewPriceDialog()
//            dialog.show(childFragmentManager, dialog.toString())
        }
        binding.rvInfluencer.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val callback: ItemTouchHelper.SimpleCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//                fun onMove(
//                    recyclerView: RecyclerView?,
//                    viewHolder: RecyclerView.ViewHolder?,
//                    target: RecyclerView.ViewHolder?
//                ): Boolean {
//                    return false
//                }

                //                fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
//                    // Take action for the swiped item
//                }
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                        .addBackgroundColor(
                            ContextCompat.getColor(
                                requireActivity(),
                                android.R.color.transparent
                            )
                        )
                        .addActionIcon(R.drawable.ic_unmatched)
                        .create()
                        .decorate()
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    try {
                        val position = viewHolder.absoluteAdapterPosition
                        val item: Model? = Adapter?.removeItem(position)
                        val snackbar = Snackbar.make(
                            viewHolder.itemView,
                            "Item " + (if (direction == ItemTouchHelper.RIGHT) "deleted" else "archived") + ".",
                            Snackbar.LENGTH_LONG

                        )
                        snackbar.setAction(android.R.string.cancel) {
                            try {
                                if (item != null)
                                    Adapter?.addItem(item, position)
                            } catch (e: Exception) {
                                Log.e("MainActivity", e.message!!)
                            }
                        }
                        binding.rlBottom.visibility = View.GONE
                        snackbar.show()
                    } catch (e: Exception) {
                        Log.e("MainActivity", e.message!!)
                    }
                }
            }
        binding.rvInfluencer.adapter = Adapter
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvInfluencer)
    }

    private fun setData() {
        list.clear()
//        list.add(Model(R.drawable.dwaynejohnson,"Cameron W.","133,000 M","(5.0)"))
        list.add(Model(R.drawable.iv1, "Cameron W.", "133,000 M", "(5.0)"))
        list.add(Model(R.drawable.iv2, "Arlene M.", "133,000 M", "(5.0)"))
        list.add(Model(R.drawable.iv3, "Dianne R.", "133,000 M", "(5.0)"))


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var toContracts = ""
    }


}