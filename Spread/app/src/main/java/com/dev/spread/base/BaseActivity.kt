package com.dev.spread.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import com.dev.amintopup.base.HideUtil
import com.dev.amintopup.base.LoaderFragment
import com.dev.amintopup.base.tooltip.SimpleTooltip
import com.dev.spread.R
import com.google.gson.Gson


@Suppress("unused")

abstract class BaseActivity : AppCompatActivity() {

    private var mViewGroup: ViewGroup? = null
    var dialogueLoader: LoaderFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogueLoader = LoaderFragment()
    }
    fun hideSystemBars() {
        val windowInsetsController =
            WindowCompat.getInsetsController(window,window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

    }
    fun showSystemBars() {
        val windowInsetsController =
            WindowCompat.getInsetsController(window,window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())

    }
    open fun openNotification() {

    }

    open fun openSideMenu() {

    }

    fun showLoader(text: String = "") {
        try {
            if (dialogueLoader?.isAdded == false) {
                dialogueLoader?.loadText = text
                dialogueLoader?.show(supportFragmentManager, LoaderFragment::class.java.toString())
            }
        } catch (e: IllegalStateException) {

        }


    }

    fun hideLoader() {
        try {
            dialogueLoader?.dismiss()
        } catch (e: IllegalStateException) {

        }

    }

    fun onSetupViewGroup(item: ViewGroup) {
        mViewGroup = item
        HideUtil.init(this, mViewGroup)
    }

    //val toolTip = showToolTip(menuView, Gravity.BOTTOM, R.layout.dashboard_menu_view, "Teom")
    @RequiresApi(Build.VERSION_CODES.M)
    fun showToolTip(
        v: View,
        position: Int,
        layout: Int,
        text: String,
        showArrow: Boolean = false,
        padding: Int = com.intuit.sdp.R.dimen._minus1sdp
    ): SimpleTooltip {
        val tooltip = SimpleTooltip.Builder(v.context)
            .anchorView(v)
            .focusable(true)
            .showArrow(showArrow)
            .backgroundColor(getColor(R.color.app_color))
            .text(text)
            .dismissOnOutsideTouch(true)
            .dismissOnInsideTouch(true)
            .modal(true)
            .gravity(position)
            .animated(false)
//            .contentView(layout, android.R.id.text1)
            .contentView(layout)
            .padding(padding)
            .focusable(true)
            .overlayMatchParent(true)
            .build()
        tooltip.show()
        return tooltip
    }

    fun <T> generateList(response: String, type: Class<Array<T>>): ArrayList<T> {

        if (response.isEmpty() || response == "null" || response == "\"[]\"") {
            return arrayListOf()
        }
//        arrayList.addAll(CollectionUtils.listOf(*Gson().fromJson(response, type)))
        val arrayList = ArrayList<T>()
        arrayList.addAll(Gson().fromJson(response, type).toList())
        return arrayList
    }

    fun showToast(toast : String){
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }

    fun statusBarColor(color: Int) {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

}