package com.jomhack.lendme.activity

import android.os.Bundle
import android.view.View
import com.jomhack.lendme.R
import com.jomhack.lendme.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar_primary.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        buttonBackToolbar.visibility = View.GONE
    }

}
