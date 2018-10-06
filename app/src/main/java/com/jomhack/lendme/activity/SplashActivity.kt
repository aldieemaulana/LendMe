package com.jomhack.lendme.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.jomhack.lendme.App
import com.jomhack.lendme.R
import com.jomhack.lendme.base.BaseActivity

/**
 * Created by Al on 06/10/2018 for JomHack
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler(500)

    }

    private fun handler(duration: Long) {
        val mHandler = Handler()
        val mRunnable = Runnable {
            checkLogin()
        }

        mHandler.postDelayed(mRunnable, duration)
    }

    private fun checkLogin() {

        val fbAuth = App.getFirebaseAuth();

        if (fbAuth.currentUser != null && fbAuth.currentUser!!.phoneNumber != null) {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra("phone", fbAuth.currentUser!!.phoneNumber)
            startActivity(intent)

        } else {
            startActivity(Intent(context, LoginActivity::class.java))
        }
        finish()
    }
}