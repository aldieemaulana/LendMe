package com.jomhack.lendme.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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

        /*if(isLogin)
            startActivity(Intent(context, MainActivity::class.java))
        else
            startActivity(Intent(context, MainActivity::class.java))*/

        startActivity(Intent(context, MainActivity::class.java))

        finish()
    }
}