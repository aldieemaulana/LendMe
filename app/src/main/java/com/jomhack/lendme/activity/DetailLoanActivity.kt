package com.jomhack.lendme.activity

import android.os.Bundle
import android.view.View
import com.jomhack.lendme.R
import com.jomhack.lendme.base.BaseActivity
import com.jomhack.lendme.model.Audit
import kotlinx.android.synthetic.main.toolbar_primary.*

class DetailLoanActivity : BaseActivity() {

    private fun initView() {
        val audit = intent.getParcelableExtra<Audit>("audit")

        textTitleToolbar.text = audit.status
        buttonBackToolbar.visibility = View.VISIBLE
        buttonBackToolbar.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initData(context)
        initView()
    }

}
