package com.jomhack.lendme.activity

import android.os.Bundle
import android.view.View
import com.jomhack.lendme.R
import com.jomhack.lendme.base.BaseActivity
import com.jomhack.lendme.model.Audit
import com.jomhack.lendme.utils.Utils
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_primary.*

class DetailLoanActivity : BaseActivity() {

    private fun initView() {
        val audit = intent.getParcelableExtra<Audit>("audit")

        textName.text = "${audit.customer?.firstName} ${audit.customer?.lastName}"
        textAmount.text = audit.amount.toString()
        textTenure.text = audit.numberOfMonth.toString() + " Month (s)"
        textCreditScore.text = audit.customer?.profileRate.toString()
        textGender.text = if(audit.customer?.gender.toString().equals("M")) "Male" else "Female"
        textAddress.text = "${audit.customer?.address1} \n${audit.customer?.address2}\n${audit.customer?.address3}"
        textInterest.setText(audit.pointOfInterest.toString())

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
