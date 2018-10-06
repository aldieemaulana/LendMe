package com.jomhack.lendme

import android.app.Application
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Al on 06/10/2018 for JomHack
 */

class App : Application() {

    companion object {
        open lateinit var fbAuth : FirebaseAuth

        fun getFirebaseAuth(): FirebaseAuth {
            return fbAuth
        }

        var TOKEN  = "dd4be2f7a7a256a69354fb8afcf02184"
        var API = "http://api"
        var URL = "http://api/"
    }

    override fun onCreate() {
        super.onCreate()
        fbAuth = FirebaseAuth.getInstance()
    }

}