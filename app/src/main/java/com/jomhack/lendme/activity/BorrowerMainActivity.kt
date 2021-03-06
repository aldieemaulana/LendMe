package com.jomhack.lendme.activity

import android.databinding.DataBindingUtil.setContentView
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.view.View
import com.jomhack.lendme.R
import com.jomhack.lendme.R.id.bottomNavigation
import com.jomhack.lendme.R.id.buttonBackToolbar
import com.jomhack.lendme.base.BaseActivity
import com.jomhack.lendme.fragment.*
import com.jomhack.lendme.fragment.borrower.*
import com.jomhack.lendme.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_primary.*

class BorrowerMainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
        BorrowerBoardFragment.OnFragmentInteractionListener,
        BorrowerInvestFragment.OnFragmentInteractionListener,
        BorrowerHistoryFragment.OnFragmentInteractionListener,
        BorrowerSettingFragment.OnFragmentInteractionListener{


    private val boardFragment = BorrowerBoardFragment()
    private val investsFragment = BorrowerInvestFragment()
    private val historyFragment = BorrowerHistoryFragment()
    private val settingFragment = BorrowerSettingFragment()
    private val fragmentManager = supportFragmentManager
    private var activeFragment : Fragment = boardFragment

    private fun initFragment() {
        setTitle(getString(R.string.title_board))

        fragmentManager.beginTransaction().add(R.id.frameLayout, settingFragment, Constants.FRAGMENT.SETTINGS.NAME).hide(settingFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frameLayout, historyFragment, Constants.FRAGMENT.HISTORY.NAME).hide(historyFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frameLayout, investsFragment, Constants.FRAGMENT.INVESTS.NAME).hide(investsFragment).commit()
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



            R.id.navigation_invests-> {
                setTitle(getString(R.string.title_borrow))
                fragmentManager.beginTransaction().hide(activeFragment).show(investsFragment).commit()
                activeFragment = investsFragment

                return true
            }

            R.id.navigation_history -> {
                setTitle(getString(R.string.title_history))
                fragmentManager.beginTransaction().hide(activeFragment).show(historyFragment).commit()
                activeFragment = historyFragment

                return true
            }

            R.id.navigation_settings -> {
                setTitle(getString(R.string.title_profile))
                fragmentManager.beginTransaction().hide(activeFragment).show(settingFragment).commit()
                activeFragment = settingFragment

                return true
            }
        }

        return false
    }

    private fun setListener() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initView() {
        buttonBackToolbar.visibility = View.GONE


        initFragment()
        setListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_borrower)

        initView()
    }

    override fun onFragmentInteraction(uri: Uri?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
