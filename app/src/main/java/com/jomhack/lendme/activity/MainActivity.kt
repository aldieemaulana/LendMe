package com.jomhack.lendme.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.view.View
import com.jomhack.lendme.R
import com.jomhack.lendme.base.BaseActivity
import com.jomhack.lendme.fragment.BoardFragment
import com.jomhack.lendme.utils.Constants
import kotlinx.android.synthetic.main.toolbar_primary.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val boardFragment = BoardFragment()
    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = boardFragment

    private fun initFragment() {
        setTitle(getString(R.string.title_board))
        fragmentManager.beginTransaction().add(R.id.frameLayout, boardFragment, Constants.FRAGMENT.BOARD.NAME).commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {
            R.id.navigation_board -> {
                setTitle(getString(R.string.title_board))
                fragmentManager.beginTransaction().hide(activeFragment).show(boardFragment).commit()
                activeFragment = boardFragment

                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private fun initView() {
        buttonBackToolbar.visibility = View.GONE

        initFragment()
    }

}
