package com.dev.spread.fragments.messages


import android.graphics.Canvas
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.spread.fragments.messages.adapter.ChatListAdapter
import com.dev.spread.fragments.messages.model.MessageModel
import com.dev.spread.R
import com.dev.spread.base.BaseFragment
import com.dev.spread.databinding.FragmentMessagesBinding
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class Message : BaseFragment() {
    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<MessageModel>()
    private var Adapter: ChatListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.tvText.text = "Messages"
        binding.topBar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
        setData()
        setRecycler()
        setSearchBar()

        binding.input.etInput.setOnClickListener{
            findNavController().navigate(R.id.action_message2_to_emptyMessage)
        }

    }

    override fun onResume() {
        super.onResume()
        setSearchBar()
    }

    private fun setSearchBar(){
        binding.input.etInput.inputType = InputType.TYPE_CLASS_TEXT
        binding.input.etLabel.hint = "Search"
        binding.input.rlField.setBackgroundResource(R.drawable.bg_serach)
        binding.input.ivImage.setImageResource(R.drawable.iv_search)
    }

    private fun setRecycler() {
        Adapter = ChatListAdapter(mActivityItem, list) {
            findNavController().navigate(R.id.action_message2_to_messageDetail)
        }
        binding.rvInventoryItems.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val callback: ItemTouchHelper.SimpleCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
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
                                this@Message.requireActivity(),
                                android.R.color.transparent
                            )
                        )
                        .addActionIcon(R.drawable.ic_hide_message)
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
                        val item: MessageModel? = Adapter?.removeItem(position)
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
                        snackbar.show()
                    } catch (e: Exception) {
                        Log.e("MainActivity", e.message!!)
                    }
                }
            }

        binding.rvInventoryItems.adapter = Adapter
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvInventoryItems)
    }

    private fun setData() {
        list.clear()
//        list.add(Model(R.drawable.dwaynejohnson,"Cameron W.","133,000 M","(5.0)"))
        list.add(
            MessageModel(
                R.drawable.iv1,
                "Jane Cooper",
                "Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                true
            )
        )
        list.add(
            MessageModel(
                R.drawable.iv2,
                "Wade Warren",
                "Me: Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                false
            )
        )
        list.add(
            MessageModel(
                R.drawable.iv3,
                "Cameron Williamson",
                "Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                true
            )
        )
        list.add(
            MessageModel(
                R.drawable.iv1,
                "Brooklyn Simmons",
                "Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                false
            )
        )
        list.add(
            MessageModel(
                R.drawable.iv2,
                "Leslie Alexander",
                "Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                true
            )
        )
        list.add(
            MessageModel(
                R.drawable.iv3,
                "Leslie Alexander",
                "Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                false
            )
        )
        list.add(
            MessageModel(
                R.drawable.iv1,
                "Guy Hawkins",
                "Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                true
            )
        )
        list.add(
            MessageModel(
                R.drawable.iv2,
                "Jacob Jones",
                "Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                true
            )
        )
        list.add(
            MessageModel(
                R.drawable.iv2,
                "Robert Fox",
                "Sorry i’ii be lateabout 15 minutes",
                "08:40 AM",
                true
            )
        )


    }
}