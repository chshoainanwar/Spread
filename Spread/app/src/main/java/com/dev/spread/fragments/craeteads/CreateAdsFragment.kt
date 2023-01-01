package com.dev.spread.fragments.craeteads

import android.annotation.SuppressLint
import android.media.session.MediaSession
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.media3.common.*
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import androidx.navigation.fragment.findNavController
import com.dev.spread.fragments.craeteads.RecordVideoFragment.Companion.checkVideo
import com.dev.spread.fragments.craeteads.RecordVideoFragment.Companion.fileVideo
import com.dev.spread.fragments.currentads.CurrentAdsFragment.Companion.fromCurrentAds
import com.dev.spread.R
import com.dev.spread.base.DeleteVideoDialog
import com.dev.spread.databinding.FragCreateAdsBinding


open class CreateAdsFragment : Fragment() {
    private var _binding: FragCreateAdsBinding? = null
    private val binding get() = _binding!!
    private var player: ExoPlayer? = null
    private val session: MediaSession? = null
    private var uri: Uri? = null
    var videoURL =
        "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragCreateAdsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yesNoSelection()
        showDeleteDialog()
        selectPriceFromEdit()

        player = context?.let { ExoPlayer.Builder(it).build() }
        binding.cvRecordVideo.setOnClickListener {
            val action = R.id.actionCreateAdsToRecordVideo
            findNavController().navigate(action)
        }



        if (checkVideo == "FromVideo") {
            when (fromCurrentAds) {
                "ToEditAds" -> {
                    binding.tvCreate.text = "Edit"
                    binding.tvDescDone.text = "Current Advertisement"
                    binding.tvConfirm.text = "Confirm"
                    binding.rlPrices.visibility = View.VISIBLE

                    binding.ivBack.setOnClickListener {
                        findNavController().navigateUp()
                    }

                    binding.cvNext.setOnClickListener {
                        val action = R.id.actionCreateAdsTCurrentAds
                        findNavController().navigate(action)
                    }
                }

                "RepostAds" -> {
                    binding.tvCreate.text = "Repost Advertisement"
                    binding.tvDescDone.text = "Past Advertisement"
                    binding.tvConfirm.text = "Next"

                    binding.ivBack.setOnClickListener {
                        findNavController().navigateUp()
                    }

                    binding.cvNext.setOnClickListener {

                        val action = R.id.actionCreateAdsToSelectPrice
                        findNavController().navigate(action)
                    }
                }

                else -> {
                    binding.ivBack.setOnClickListener {
                        val action = R.id.actionCreateAdsToRecordVideo
                        findNavController().navigate(action)
                    }

                    binding.cvNext.setOnClickListener {
                        val action = R.id.actionCreateAdsToSelectPrice
                        findNavController().navigate(action)
                    }
                }
            }

            binding.tvDesc.visibility = View.GONE
            binding.tvDescDone.visibility = View.VISIBLE
            binding.rlVideo.visibility = View.VISIBLE
            binding.ivBack.visibility = View.VISIBLE
            binding.tvExplain.visibility = View.GONE
            binding.cvRecordVideo.visibility = View.GONE
            binding.cvNext.visibility = View.VISIBLE

            uri = Uri.parse(fileVideo)
            if (initializePlayer()) {
                binding.videoView.player = player
                val mediaItem = uri?.let { MediaItem.fromUri(it) }
                mediaItem?.let { player?.setMediaItem(it) }
                player?.prepare()
                player?.playWhenReady = true
            }


        } else {
            when (fromCurrentAds) {
                "ToEditAds" -> {
                    binding.tvCreate.text = "Edit"
                    binding.tvDescDone.text = "Current Advertisement"
                    binding.tvConfirm.text = "Confirm"
                    binding.tvConfirm.isEnabled = false
                    binding.tvConfirm.setBackgroundColor(requireContext().getColor(R.color.border_color))
                    val params = binding.rlPrices.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, 50, 0, 0)
                    binding.rlPrices.layoutParams = params

                    binding.tvDesc.visibility = View.GONE
                    binding.tvDescDone.visibility = View.VISIBLE
                    binding.rlVideo.visibility = View.GONE
                    binding.ivBack.visibility = View.VISIBLE
                    binding.tvExplain.visibility = View.VISIBLE
                    binding.cvRecordVideo.visibility = View.VISIBLE
                    binding.cvNext.visibility = View.VISIBLE
                    binding.rlPrices.visibility = View.VISIBLE
                    binding.tvVisit.visibility = View.VISIBLE
                    binding.rlYesNo.visibility = View.VISIBLE
                }

                "RepostAds" -> {
                    binding.tvDesc.text = "It only takes a minute to create an advertisement."
                    binding.tvDesc.visibility = View.VISIBLE
                    binding.tvDescDone.visibility = View.GONE
                    binding.rlVideo.visibility = View.GONE
                    binding.ivBack.visibility = View.GONE
                    binding.tvExplain.visibility = View.VISIBLE
                    binding.cvRecordVideo.visibility = View.VISIBLE
                    binding.cvRecordVideo.isEnabled = false
                    binding.rlRecord.setBackgroundResource(R.drawable.bg_record2)
                    binding.ivRecord.setColorFilter(requireContext().getColor(R.color.app_color))
                    binding.tvNext.setTextColor(requireContext().getColor(R.color.app_color))
                    binding.cvNext.visibility = View.GONE
                    binding.cvNextPast.visibility = View.VISIBLE
                    binding.cvNextPast.setOnClickListener {
                         rePostToPrice = "FromRePost"
                        val action = R.id.actionCreateAdsTPastVideos
                        findNavController().navigate(action)
                    }
                }

                else -> {
                    binding.tvDesc.visibility = View.VISIBLE
                    binding.tvDescDone.visibility = View.GONE
                    binding.rlVideo.visibility = View.GONE
                    binding.ivBack.visibility = View.GONE
                    binding.tvExplain.visibility = View.VISIBLE
                    binding.cvRecordVideo.visibility = View.VISIBLE
                    binding.cvNext.visibility = View.GONE
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        showDeleteDialog()
        if (checkVideo == "FromVideo") {
            if (player == null) {
                initializePlayer()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDeleteDialog() {
        binding.ivDelete.setOnClickListener {
            val dialog = DeleteVideoDialog(){

            }
            dialog.show(childFragmentManager, dialog.toString())
        }
    }

    private fun initializePlayer(): Boolean {
        if (player == null) {
            val playerBuilder = ExoPlayer.Builder(requireContext())
            player = playerBuilder.build()
            player!!.addAnalyticsListener(EventLogger())
            player!!.setAudioAttributes(AudioAttributes.DEFAULT,  /* handleAudioFocus= */true)
            binding.videoView.player = player
        }
        return true
    }


//    private fun initializePlayer() {
//        if (player == null) {
//            player = ExoPlayer.Builder(requireContext())
//                .build()
//                .also { exoPlayer ->
//                    val mediaItem = uri?.let { MediaItem.fromUri(it) }
//                    mediaItem?.let { exoPlayer.setMediaItem(it) }
//                }
//            binding.videoView.setPlayer(player)//.player = player
//        }
//    }


    //    @RequiresApi(Build.VERSION_CODES.M)
    private fun yesNoSelection() {
        binding.tvYes.setOnClickListener {
            binding.tvYes.setBackgroundResource(R.drawable.bg_yes)
            binding.tvYes.setTextColor(requireContext().getColor(R.color.app_color))
            binding.tvNo.setBackgroundResource(R.drawable.bg_no)
            binding.tvNo.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
        binding.tvNo.setOnClickListener {
            binding.tvNo.setBackgroundResource(R.drawable.bg_yes)
            binding.tvNo.setTextColor(requireContext().getColor(R.color.app_color))
            binding.tvYes.setBackgroundResource(R.drawable.bg_no)
            binding.tvYes.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
    }

    private fun selectPriceFromEdit() {
        binding.tv500.setOnClickListener {
            binding.tv500.setBackgroundResource(R.drawable.bg_yes)
            binding.tv500.setTextColor(requireContext().getColor(R.color.app_color))
            binding.tv1500.setBackgroundResource(R.drawable.bg_no)
            binding.tv1500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv3000.setBackgroundResource(R.drawable.bg_no)
            binding.tv3000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv5000.setBackgroundResource(R.drawable.bg_no)
            binding.tv5000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv10000.setBackgroundResource(R.drawable.bg_no)
            binding.tv10000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv20000.setBackgroundResource(R.drawable.bg_no)
            binding.tv20000.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
        binding.tv1500.setOnClickListener {
            binding.tv1500.setBackgroundResource(R.drawable.bg_yes)
            binding.tv1500.setTextColor(requireContext().getColor(R.color.app_color))
            binding.tv500.setBackgroundResource(R.drawable.bg_no)
            binding.tv500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv3000.setBackgroundResource(R.drawable.bg_no)
            binding.tv3000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv5000.setBackgroundResource(R.drawable.bg_no)
            binding.tv5000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv10000.setBackgroundResource(R.drawable.bg_no)
            binding.tv10000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv20000.setBackgroundResource(R.drawable.bg_no)
            binding.tv20000.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
        binding.tv3000.setOnClickListener {
            binding.tv3000.setBackgroundResource(R.drawable.bg_yes)
            binding.tv3000.setTextColor(requireContext().getColor(R.color.app_color))
            binding.tv500.setBackgroundResource(R.drawable.bg_no)
            binding.tv500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv1500.setBackgroundResource(R.drawable.bg_no)
            binding.tv1500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv5000.setBackgroundResource(R.drawable.bg_no)
            binding.tv5000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv10000.setBackgroundResource(R.drawable.bg_no)
            binding.tv10000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv20000.setBackgroundResource(R.drawable.bg_no)
            binding.tv20000.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
        binding.tv5000.setOnClickListener {
            binding.tv5000.setBackgroundResource(R.drawable.bg_yes)
            binding.tv5000.setTextColor(requireContext().getColor(R.color.app_color))
            binding.tv500.setBackgroundResource(R.drawable.bg_no)
            binding.tv500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv1500.setBackgroundResource(R.drawable.bg_no)
            binding.tv1500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv3000.setBackgroundResource(R.drawable.bg_no)
            binding.tv3000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv10000.setBackgroundResource(R.drawable.bg_no)
            binding.tv10000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv20000.setBackgroundResource(R.drawable.bg_no)
            binding.tv20000.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
        binding.tv10000.setOnClickListener {
            binding.tv10000.setBackgroundResource(R.drawable.bg_yes)
            binding.tv10000.setTextColor(requireContext().getColor(R.color.app_color))
            binding.tv500.setBackgroundResource(R.drawable.bg_no)
            binding.tv500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv1500.setBackgroundResource(R.drawable.bg_no)
            binding.tv1500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv3000.setBackgroundResource(R.drawable.bg_no)
            binding.tv3000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv5000.setBackgroundResource(R.drawable.bg_no)
            binding.tv5000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv20000.setBackgroundResource(R.drawable.bg_no)
            binding.tv20000.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
        binding.tv20000.setOnClickListener {
            binding.tv20000.setBackgroundResource(R.drawable.bg_yes)
            binding.tv20000.setTextColor(requireContext().getColor(R.color.app_color))
            binding.tv500.setBackgroundResource(R.drawable.bg_no)
            binding.tv500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv1500.setBackgroundResource(R.drawable.bg_no)
            binding.tv1500.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv3000.setBackgroundResource(R.drawable.bg_no)
            binding.tv3000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv5000.setBackgroundResource(R.drawable.bg_no)
            binding.tv5000.setTextColor(requireContext().getColor(R.color.text_hint_color))
            binding.tv10000.setBackgroundResource(R.drawable.bg_no)
            binding.tv10000.setTextColor(requireContext().getColor(R.color.text_hint_color))
        }
    }

    companion object{
        var rePostToPrice = ""
    }
}