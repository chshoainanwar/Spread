package com.dev.spread

import android.os.Bundle
import androidx.core.view.WindowCompat
import com.dev.spread.base.BaseActivity
import com.dev.spread.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor(getColor(R.color.app_color))
        WindowCompat.setDecorFitsSystemWindows(window, true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
