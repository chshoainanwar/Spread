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
import com.dev.spread.R
import com.dev.spread.base.BaseDialogueFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UpdateOtherMessageDialouge : BaseDialogueFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_other_message_dialouge, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideSystemBars()
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
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//height.toInt()
            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
    fun hideSystemBars() {

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