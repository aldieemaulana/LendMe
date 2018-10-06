package com.jomhack.lendme.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jomhack.lendme.R
import com.jomhack.lendme.activity.MainActivity

/**
 * Created by Al on 06/10/2018 for JomHack
 */

class BoardFragment : Fragment() {

    private lateinit var mActivity : MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = (activity as MainActivity)
    }

}