package com.jomhack.lendme.base

import android.content.Context
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.jomhack.lendme.utils.Dialogs
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.toolbar_primary.*

/**
 * Created by Al on 06/10/2018 for JomHack
 */

open class BaseActivity : AppCompatActivity() {

    internal val context : Context = this

    companion object {
        var progress: KProgressHUD? = null

        fun showProgress() {
            progress!!.show()
        }

        fun hideProgress() {
            progress!!.dismiss()
        }

        fun initData(context : Context) {
            progress = Dialogs().initProgressDialog(context)
        }

    }

    /* for auto dismiss keyboard and clear focus when touch outside */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    internal fun setTitle(title: String) {
        textTitleToolbar.text = title
    }



}