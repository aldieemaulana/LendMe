package com.jomhack.lendme.logger

import android.util.Log
import com.jomhack.lendme.BuildConfig


/**
 * Created by JomHack on 06/10/2018 for JomHack
 */

object JomHackLog {
    fun e(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e("JomHack error", "JomHackMsg: $msg")
        }
    }

    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d("JomHack debug", "JomHackMsg: $msg")
        }
    }

    fun v(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.v("JomHack verbose","JomHackMsg: $msg")
        }
    }

    fun i(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.i("JomHack info","JomHackMsg: $msg")
        }
    }

    fun w(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.w("JomHack warn","JomHackMsg: $msg")
        }
    }

    fun log(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e("JomHack","JomHackMsg: $msg")
        }
    }
}