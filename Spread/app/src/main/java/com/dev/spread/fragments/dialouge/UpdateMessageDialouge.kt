package com.dev.spread.fragments.dialouge

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.dev.spread.base.BaseDialogueFragment
import com.dev.spread.base.DeleteVideoDialog
import com.dev.spread.databinding.FragmentUpdateMessageDialougeBinding


class UpdateMessageDialouge(val callback: ((Boolean, Boolean) -> Unit)) : BaseDialogueFragment() {

    private var _binding: FragmentUpdateMessageDialougeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateMessageDialougeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width =
                Resources.getSystem().displayMetrics.widthPixels.toDouble() * (0.999).toDouble()//ViewGroup.LayoutParams.MATCH_PARENT
            val height =
                Resources.getSystem().displayMetrics.heightPixels.toDouble() * (0.999).toDouble()//ViewGroup.LayoutParams.MATCH_PARENT//ViewGroup.LayoutParams.WRAP_CONTENT
//            dialog.setCanceledOnTouchOutside(false)
//            dialog.setCancelable(false)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )//height.toInt()
            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideSystemBars()
//        mActivityItem.hideSystemBars()

        binding?.dele?.setOnClickListener {
            val dialog = DeleteVideoDialog() {
                if (it) {
                    dismiss()
                    callback.invoke(true, false)
                }
            }
            dialog.flag = "hide"
            dialog.show(childFragmentManager, dialog.toString())
        }
        binding.copytext.setOnClickListener {
//                    this.dismiss()
            dismiss()
            callback.invoke(false, true)
        }
        binding.quot.setOnClickListener {
//                    this.dismiss()
            dismiss()
            callback.invoke(false, true)
        }

    }

    fun hideStatusBar() {
//        val uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                View.SYSTEM_UI_FLAG_FULLSCREEN
//        dialog?.window?.decorView?.systemUiVisibility = uiOptions
//        dialog?.window?.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
    }

    fun hideSystemBars() {
        hideStatusBar()
        val windowInsetsController =
            requireDialog().window?.let {
                view?.let { it1 ->
                    WindowCompat.getInsetsController(
                        it,
                        it1
                    )
                }
            }
//            dialog?.window?.let { WindowCompat.getInsetsController(it, it.decorView) } ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController?.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController?.hide(WindowInsetsCompat.Type.systemBars())

    }
}

