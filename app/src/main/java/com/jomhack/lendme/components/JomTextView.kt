package com.jomhack.lendme.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.View
import com.jomhack.lendme.R
import com.jomhack.lendme.logger.JomHackLog
import com.jomhack.lendme.utils.Utils


/**
 * Created by Al on 06/10/2018 for JomHack
 */

class JomTextView : AppCompatTextView {

    private var mFont: String = "R"
    private var mPath: String = "fonts/Montserrat-"
    private var mType: String = ".ttf"
    private var mEditRes: Int = 0
    private lateinit var mEdit: View
    private lateinit var mError: JomTextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setValues(attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setOnClickFocus()
    }

    fun setFont(font: String) {
        mFont = font
    }

    fun getFont(): String {
        return mFont
    }

    private fun init() {
        setFont(mFont)
        setNewTypeFace()
    }

    private fun setNewTypeFace() {
        val font = Typeface.createFromAsset(context.assets, mPath + mFont + mType)
        setTypeface(font, Typeface.NORMAL)
    }

    private fun setOnClickFocus() {
        if(mEditRes > 0 && mEditRes != -1) {
            mEdit = (parent as View).findViewById(mEditRes)!!

            setOnClickListener{

                if(mEdit.isEnabled) {
                    mEdit.requestFocus()
                    Utils().showKeyboard(context)
                }
            }

        }
    }

    @SuppressLint("CustomViewStyleable")
    private fun setValues(attrs: AttributeSet?) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.JomView)
        try {
            val n = attr.indexCount
            for (i in 0 until n) {
                val attribute = attr.getIndex(i)
                when (attribute) {
                    R.styleable.JomView_JomFont -> mFont = attr.getString(attribute)
                    R.styleable.JomView_JomEdit -> mEditRes = attr.getResourceId(R.styleable.JomView_JomEdit, -1)
                    else -> JomHackLog.d("Unknown attribute for " + javaClass.toString() + ": " + attribute)
                }
            }
        } finally {
            attr.recycle()
        }
    }



}