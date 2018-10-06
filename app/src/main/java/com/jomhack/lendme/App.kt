package com.jomhack.lendme

import android.app.Application

/**
 * Created by Al on 06/10/2018 for JomHack
 */

class App : Application() {

    companion object {
        var TOKEN  = "dd4be2f7a7a256a69354fb8afcf02184"
        var API = "http://api"
        var URL = "http://api/"
    }

    override fun onCreate() {
        super.onCreate()
    }

}