package com.dev.spread.fragments.messages

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.amintopup.base.tooltip.SimpleTooltip.Companion.TAG
import com.dev.spread.R
import com.dev.spread.base.BaseFragment
import com.dev.spread.databinding.FragmentMessageDetailBinding
import com.dev.spread.fragments.SelectedPaymentMethod.Companion.lol
import com.dev.spread.fragments.craeteads.SelectPriceFragment.Companion.toWelcome
import com.dev.spread.fragments.dialouge.UpdateMessageDialouge
import com.dev.spread.fragments.dialouge.UpdateOtherMessageDialouge
import com.dev.spread.fragments.influencer.Influencer.Companion.toContracts
import com.dev.spread.fragments.messages.adapter.ChatAdapter
import com.dev.spread.fragments.messages.model.RecentChat
import com.dev.spread.fragments.setting.contracts.ContractsFragment.Companion.toReview
import com.dev.spread.utils.CountDownTimerViewDisplay
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.robertlevonyan.components.picker.*


class MessageDetail : BaseFragment() {

    private var _binding: FragmentMessageDetailBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<RecentChat>()
    private var mAdapter: ChatAdapter? = null
    var urlSn = "https://snapchat.com/add/{snapchat_page_name}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMessageDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(TAG, "Fragment back pressed invoked")
                    // Do custom work here
                    if (lol == "lol") {
                        lol = ""
                        if(toWelcome == "PostAds"){
                            findNavController().navigate(R.id.action_messageDetail_to_influencserdashboared)}
                        else
                            toContracts = "ForHome"
                        findNavController().navigate(R.id.action_messageDetail_to_Contracts)
                    }
                    else if(toWelcome == "PostAds"){
                        findNavController().navigate(R.id.action_messageDetail_to_influencserdashboared)
                    }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sfChatMessages.setOnRefreshListener {
            binding.sfChatMessages.isRefreshing = false
        }
        val args = arguments
        val index = args?.getString("sendoffer")

        binding.topBar.ivVideoCall.visibility = View.VISIBLE
        binding.topBar.detail.visibility = View.VISIBLE
        binding.topBar.rlSearch.visibility = View.VISIBLE
        binding.topBar.ivBack.visibility = View.VISIBLE
        binding.topBar.ivSnapchat.visibility = View.VISIBLE
        binding.topBar.tvText.visibility = View.GONE

        binding.loca?.setOnClickListener {
            findNavController().navigate(R.id.action_messageDetail_to_shareLocation2)
        }
        binding.topBar?.rlSearch?.setOnClickListener {
            if(lol=="lol"){

            }else{
                findNavController().navigate(R.id.action_messageDetail_to_paymentInfo)
            }

        }
        binding.topBar.ivBack.setOnClickListener {
            if (lol == "lol") {
                lol = ""
                if(toWelcome == "PostAds"){
                    findNavController().navigate(R.id.action_messageDetail_to_influencserdashboared)}
                else
                    toContracts = "ForHome"
                findNavController().navigate(R.id.action_messageDetail_to_Contracts)
            }
            else if(toWelcome == "PostAds"){
                findNavController().navigate(R.id.action_messageDetail_to_influencserdashboared)
            }
            else {
                // if you want onBackPressed() to be called as normal afterwards
                findNavController().popBackStack()
            }
        }

        binding.topBar.ivVideoCall.setOnClickListener {
            findNavController().navigate(R.id.action_messageDetail_to_RecordVideo)
        }
        binding.topBar.llRating.setOnClickListener {
            toReview = "FromRating"
            findNavController().navigate(R.id.actionMessageDetailToRating)
        }
        binding.topBar.ivSnapchat.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "*/*"
                intent.setPackage("com.snapchat.android")
                startActivity(Intent.createChooser(intent, "Open Snapchat"))
            } catch (anfe: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlSn)))
            }
        }



        tvTimerDisplay = CountDownTimerViewDisplay(requireContext())
        tvTimerDisplay?.setOnTimerListener(object : CountDownTimerViewDisplay.TimerListener {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("iti", "tit $millisUntilFinished")
            }

            override fun onFinish() {
                val indexOfItem =
                    list.indexOfFirst { item -> item.chat_image == R.drawable.advertispng }
                if (indexOfItem > -1) {
                    list.removeAt(indexOfItem)
                    mAdapter?.notifyItemRemoved(indexOfItem)
                }
            }

        })

        if (index.equals("send")) {
            binding?.topBar?.sendmsg?.text = "Job Offer Sent"
            setData(index)
            setRecycler()
        } else {

            setData(null)
            setRecycler()
        }
        binding.copymsglayout.card.setBackgroundResource(R.drawable.custom_corner)

        binding.copymsglayout?.sendcopy?.setOnClickListener {
            list.add(RecentChat(null, null, "Cameron W.", "10:51 PM", true))
//            mAdapter?.notifyDataSetChanged()
            mAdapter?.notifyItemInserted(list.count() - 1)
            binding.copymsglayout.message2.visibility = View.GONE
            binding.layout.visibility = View.VISIBLE
            binding.rvChatMessages.post {
                mAdapter?.itemCount?.let { binding.rvChatMessages.smoothScrollToPosition(it) }
            }
        }

        binding.clip.setOnClickListener {

            openSharefileOptions()


//            val documentsPath = File(mActivityItem.getFilesDir(), "documents")
//            val file = File(documentsPath, "sample.pdf")
//
//            val uri: Uri = FileProvider.getUriForFile(mActivityItem, "com.dev.spread", file)
//
//            val intent: Intent = ShareCompat.IntentBuilder.from(mActivityItem)
//                .setType("application/pdf")
//                .setStream(uri)
//                .setChooserTitle("Choose bar")
//                .createChooserIntent()
//                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//
//            mActivityItem.startActivity(intent)
//            rateBottomSheetDialog()
        }

    }

    private fun openSharefileOptions() {
        pickerDialog {
        setTitle(R.string.app_name)
        setTitleTextBold(true)
        setTitleTextSize(22f)
        setTitleGravity(Gravity.START)
        setItems(
          setOf(
            ItemModel(ItemType.Camera),
            ItemModel(ItemType.Video),
            ItemModel(ItemType.ImageGallery(MimeType.Image.Png)),
            ItemModel(ItemType.VideoGallery()),
            ItemModel(ItemType.AudioGallery(MimeType.Audio.Mp3)),
            ItemModel(ItemType.Files()),
          )
        )
        setListType(PickerDialog.ListType.TYPE_GRID)
      }.setPickerCloseListener { type, uris ->
//        val ivPreview = findViewById<ImageView>(R.id.ivPreview)
//        when (type) {
//          ItemType.Camera -> ivPreview.load(uris.first())
//          ItemType.Video -> {
//            ivPreview.load(uris.first()) {
//              fetcher(VideoFrameUriFetcher(mActivityItem))
//            }
////            ivPreview.setOnClickListener {
////              Intent(Intent.ACTION_VIEW)
////                .apply {
////                  setDataAndType(uris.first(), "video/mp4")
////                  addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION)
////                }
////                .let { startActivity(it) }
////            }
//          }
//          is ItemType.ImageGallery -> {
//            println(uris.toTypedArray().contentToString())
////            ivPreview.load(uris.first())
//          }
//          is ItemType.AudioGallery -> {
//            println(uris.toTypedArray().contentToString())
//          }
//          is ItemType.VideoGallery -> {
//            println(uris.toTypedArray().contentToString())
////            ivPreview.load(uris.first()) {
////              fetcher(VideoFrameUriFetcher(mActivityItem))
////            }
////            ivPreview.setOnClickListener {
////              Intent(Intent.ACTION_VIEW)
////                .apply {
////                  val mimeTypes = type.mimeTypes
////                  if (mimeTypes.size == 1) {
////                    setDataAndType(uris.first(), mimeTypes.first().type)
////                  } else {
////                    setType("*/*")
////                    putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes.map { it.type }.toTypedArray())
////                  }
////                  addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION)
////                }
////                .let { startActivity(it) }
////            }
//          }
//          is ItemType.Files -> println(uris.toTypedArray().contentToString())
//        }
      }.show()

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


        tvRating.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->

                if (rating > 2) {
                    tvNext.setBackgroundResource(R.color.app_color)

                } else {
                    tvNext.setBackgroundResource(R.color.view_bar)
                }

            }


        tvNext.setOnClickListener {
            dialog.dismiss()

        }

    }

    private fun setRecycler() {
        mAdapter = ChatAdapter(mActivityItem, list) { it, flag ->
            if (flag) {
//            mActivityItem.hideSystemBars()
                val abc = list.get(it)
                if (abc.is_sender == false) {
                    val dialogue = UpdateOtherMessageDialouge()
                    dialogue.show(childFragmentManager, dialogue.tag.toString())
                } else {
                    val dialogue = UpdateMessageDialouge { delete, copy ->
                        if (delete) {
                            abc.is_deleted = true
                            mAdapter?.notifyDataSetChanged()
                        } else if (copy) {
                            abc.is_copy = true
                            mAdapter?.notifyDataSetChanged()
                            binding.copymsglayout.message2.visibility = View.VISIBLE
                            binding.layout.visibility = View.GONE
                        }
                    }
                    dialogue.show(mActivityItem.supportFragmentManager, "")

                }
            } else {
                val abc = list.get(it)
                if (abc.chat_body?.contains("Refund requested") == true) {
                    val dialog =
                        BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
                    val layoutDialog = LayoutInflater.from(requireContext())
                        .inflate(R.layout.rate_doalog_for_refund, null, false)
                    dialog.setContentView(layoutDialog)
                    dialog.setCancelable(true)
                    dialog.show()

//                var etFeedback = layoutDialog.findViewById<EditText>(R.id.etFeedback)
//                var tvRating = layoutDialog.findViewById<RatingBar>(R.id.tvRating)
//                var tvNext = layoutDialog.findViewById<TextView>(R.id.tvNext)

//                tvNext.setOnClickListener {
//                    dialog.dismiss()
//                    WelcomeFragment.fromRate = "AfterPayment"
//                }
                }
                else if (abc.chat_body?.contains("Payment Requested") == true){
                    toContractsRequested = "FromPaymentRequested"
                    findNavController().navigate(R.id.action_messageDetail_to_ContractDetails)
                }
            }

        }
        binding.rvChatMessages.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChatMessages.adapter = mAdapter
        binding.rvChatMessages.post {
            mAdapter?.itemCount?.let { binding.rvChatMessages.smoothScrollToPosition(it) }
        }
    }

    private fun setData(offersend: String?) {
        list.clear()
//        list.add(Model(R.drawable.dwaynejohnson,"Cameron W.","133,000 M","(5.0)"))
        list.add(
            RecentChat(
                R.drawable.iv1,
                null,
                "Hey!  Look forward to meeting you Saturday night.  What time were you thinking?",
                "10:50 PM",
                false
            )
        )
        list.add(RecentChat(R.drawable.iv1, R.drawable.videopic, "", "10:51 PM", false))
        list.add(
            RecentChat(
                null,
                null,
                "Great! Super excited to check out E’s Bar. And my week is solid.  No fires to put out so grateful for that.",
                "10:52 PM",
                true
            )
        )
        list.add(RecentChat(null, R.drawable.locationpic, "", "10:51 PM", true))
        if (offersend != null) {
            list.add(RecentChat(null, null, "Job offer sent", "10:52 PM", true))
            list.add(RecentChat(null, null, "<u>Refund requested</u>", "10:52 PM", true))
            list.add(RecentChat(R.drawable.iv1, null, "Job declined", "10:50 PM", false))
            list.add(RecentChat(R.drawable.iv1, null, "Job accepted", "10:50 PM", false))
            list.add(RecentChat(null, R.drawable.advertispng, "advertisment", null, false))

            tvTimerDisplay?.stopCountDown()
            tvTimerDisplay?.setPrefixText(" ")
            tvTimerDisplay?.setSuffixText(" ")
            tvTimerDisplay?.setTime(30000)
            tvTimerDisplay?.startCountDown()
            //Cell For Advertisement

            list.add(
                RecentChat(
                    R.drawable.iv1,
                    null,
                    "<u>Payment Requested</u> <br></br> Check your advertisement on influencer’s account and release the payment after 24 hours.",
                    "10:50 PM",
                    false
                )
            )
        }

    }

    companion object{
        var toContractsRequested = ""
    }

}

