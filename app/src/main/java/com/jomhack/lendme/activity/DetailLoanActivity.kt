package com.jomhack.lendme.activity

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.View
import com.jomhack.lendme.App
import com.jomhack.lendme.R
import com.jomhack.lendme.base.BaseActivity
import com.jomhack.lendme.model.Audit
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_primary.*

class DetailLoanActivity : BaseActivity() {
    private var audit : Audit? = null

    private fun initView() {
        audit = intent.getParcelableExtra<Audit>("audit")

        if (audit != null) {
            textName.text = "${audit?.fromCustomerId?.firstName} ${audit?.fromCustomerId?.lastName}"
            textAmount.text = audit?.amount.toString()
            textTenure.text = audit?.numberOfMonth.toString() + " Month (s)"
            textCreditScore.text = audit?.fromCustomerId?.profileRate.toString()
            textPostalCode.text = audit?.fromCustomerId?.postalCode.toString()
            textMobileNumber.text = audit?.fromCustomerId?.mobileNo.toString()
            textCity.text = audit?.fromCustomerId?.city.toString()
            textState.text = audit?.fromCustomerId?.state.toString()
            textGender.text = if(audit?.fromCustomerId?.gender.toString().equals("M")) "Male" else "Female"
            textAddress.text = "${audit?.fromCustomerId?.address1} \n${audit?.fromCustomerId?.address2}\n${audit?.fromCustomerId?.address3}"
            textInterest.setText(audit?.pointOfInterest.toString())

            textTitleToolbar.text = audit?.status
            buttonBackToolbar.visibility = View.VISIBLE
            buttonBackToolbar.setOnClickListener {
                onBackPressed()
            }

            if(audit?.status.equals("Waiting")) {
                textInterest.isEnabled = true
                buttonPanel.visibility = View.VISIBLE
            }else {
                textInterest.isEnabled = false
                buttonPanel.visibility = View.GONE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initData(context)
        initView()
        setListener()
    }

    private fun setListener() {


        acceptButton.setOnClickListener {
            val fbAuth = App.getFirebaseAuth()

            val snackbar = Snackbar.make(
                    container,
                    "The transaction number ${audit?.auditId} has been accepted by ${fbAuth.currentUser!!.phoneNumber}" ,
                    Snackbar.LENGTH_SHORT
            )

            snackbar.show()

            val mHandler = Handler()
            val mRunnable = Runnable {
                snackbar.dismiss()
                finish()
            }

            mHandler.postDelayed(mRunnable, 1750)
        }

        rejectButton.setOnClickListener {
            val fbAuth = App.getFirebaseAuth()

            val snackbar = Snackbar.make(
                    container,
                    "The transaction number ${audit?.auditId} has been rejected by ${fbAuth.currentUser!!.phoneNumber}" ,
                    Snackbar.LENGTH_SHORT
            )

            snackbar.show()

            val mHandler = Handler()
            val mRunnable = Runnable {
                snackbar.dismiss()
                finish()
            }

            mHandler.postDelayed(mRunnable, 1750)
        }
    }

}
