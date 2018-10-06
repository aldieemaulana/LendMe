package com.jomhack.lendme.utils

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Al on 27/09/2018
 */
open class Utils {

    fun showKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    companion object {

        fun showSnackBar(result: String, rootLayout: View) {
            val snackBar = Snackbar.make(rootLayout, result, Snackbar.LENGTH_LONG)

            snackBar.setAction(
                    "Dismiss"
            ) { snackBar.dismiss() }
            snackBar.show()
        }

        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            if (netInfo != null && netInfo.isConnectedOrConnecting) {
                return true
            } else {

            }
            return false
        }

        fun hideKeyboard(activity: Activity) {
            val inputMethodManager = activity.getSystemService(
                    Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                    activity.currentFocus!!.windowToken, 0)
        }
    }

}