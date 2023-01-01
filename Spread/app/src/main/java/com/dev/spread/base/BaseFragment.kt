package com.dev.spread.base


//import com.dev.amintopup.fragments.home.HomeActivity
//import id.zelory.compressor.Compressor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dev.amintopup.base.HideUtil
import com.dev.spread.utils.CountDownTimerViewDisplay
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.net.Socket


open class BaseFragment : Fragment() {
    var mSocket: Socket? = null
    var tvTimerDisplay: CountDownTimerViewDisplay? = null
    val handler = Handler(Looper.getMainLooper())
    val mActivityItem: BaseActivity
        get() {
            return activity as? BaseActivity ?: requireActivity() as BaseActivity
        }
//    val mActivityDashboard: HomeActivity?
//        get() {
//            return mActivityItem as? HomeActivity
//        }


    val scopeIO: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    val scopeMain: CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view is ViewGroup) {
            HideUtil.init(mActivityItem, view)
        }
    }


    fun showLoader(text: String = "") {
        mActivityItem.runOnUiThread {
            mActivityItem.showLoader(text)
        }
    }

    fun showToast(msg: Any?) {
        mActivityItem.showToast(msg.toString())
    }

    fun getPhoneDeviceName(): String {
        val model = Build.BRAND // returns brand name
        return model;
    }

    fun hideLoader() {
        mActivityItem.runOnUiThread {
            mActivityItem.hideLoader()
        }
    }

    fun getColoredSpanned(text: String, color: String): String {
        return "<font color=$color>$text</font>"
    }

}

open class BasesheetFragment : BottomSheetDialogFragment() {
    var mSocket: Socket? = null
    val mActivityItem: BaseActivity
        get() {
            return activity as? BaseActivity ?: requireActivity() as BaseActivity
        }

    //    val mActivityDashboard: HomeActivity?
//        get() {
//            return mActivityItem as? HomeActivity
//        }
    override fun onStart() {
        super.onStart()
        requireDialog().window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    val scopeIO: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    val scopeMain: CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view is ViewGroup) {
            HideUtil.init(mActivityItem, view)
        }
    }


    fun showLoader(text: String = "") {
        mActivityItem.runOnUiThread {
            mActivityItem.showLoader(text)
        }
    }

    fun showToast(msg: Any?) {
        mActivityItem.showToast(msg.toString())
    }

    fun getPhoneDeviceName(): String {
        val model = Build.BRAND // returns brand name
        return model;
    }

    fun hideLoader() {
        mActivityItem.runOnUiThread {
            mActivityItem.hideLoader()
        }
    }

    fun getColoredSpanned(text: String, color: String): String {
        return "<font color=$color>$text</font>"
    }

//    override fun onStart() {
//        super.onStart()
//        mActivityItem.hideSystemBars()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mActivityItem.hideSystemBars()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mActivityItem.showSystemBars()
//    }

}


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })

    fun showToast(toast: String) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }


}