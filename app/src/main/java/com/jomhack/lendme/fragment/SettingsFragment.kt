package com.jomhack.lendme.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.jomhack.lendme.App
import com.jomhack.lendme.R
import com.jomhack.lendme.activity.LoginActivity
import com.jomhack.lendme.activity.MainActivity
import com.jomhack.lendme.base.BaseActivity
import com.jomhack.lendme.model.User
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * Created by Al on 06/10/2018 for JomHack
 */

class SettingsFragment : Fragment() {

    private lateinit var mActivity : MainActivity
    private lateinit var fbAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = (activity as MainActivity)

        setUserData()
        setListener()
    }

    private fun setListener() {
        BaseActivity.initData(mActivity)
        fbAuth = App.getFirebaseAuth()

        logOutButton.setOnClickListener {
            BaseActivity.showProgress()
            fbAuth.signOut()
            BaseActivity.hideProgress()

            startActivity(Intent(mActivity, LoginActivity::class.java))
            mActivity.finish()
        }

        changePlan.setOnClickListener {
            BaseActivity.showProgress()

            val mHandlerOther = Handler()
            val mRunnableOther = Runnable {
                BaseActivity.hideProgress()

                textSubscription.text = "Premium User"
            }

            mHandlerOther.postDelayed(mRunnableOther, 1750)

        }
    }

    private fun setUserData() {
        val jsonAudit = "{\"customer_id\":\"CID001\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06T13:53:56.000Z\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06T13:53:56.000Z\",\"first_name\":\"Ahmad\",\"last_name\":\"Bin Ismail\",\"ic_no\":\"900218-1039-0192\",\"date_of_birth\":\"1990-02-17T16:00:00.000Z\",\"gender\":\"M\",\"profile_rate\":4,\"address1\":\"No 15.\",\"address2\":\"Block 6 Taman Putra\",\"address3\":\"\",\"postal_code\":\"43000\",\"city\":\"Kajang\",\"state\":\"Selangor\",\"country\":\"Malaysia\",\"mobile_no\":\"+60182909212\",\"email\":\"ahmad@gmail.com\",\"emergency_contact_no\":\"+60192321290\",\"remark\":null}"
        val user = Gson().fromJson(jsonAudit, User::class.java)

        textName.text = "${user?.firstName} ${user?.lastName}"
        textMobileNumber.text = "${user?.mobileNo} | ${user?.email}"


    }

}
