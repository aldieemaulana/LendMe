package com.jomhack.lendme.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.Gson
import com.jomhack.lendme.R
import com.jomhack.lendme.activity.MainActivity
import com.jomhack.lendme.adapter.ApplicationListAdapter
import com.jomhack.lendme.model.Audit
import kotlinx.android.synthetic.main.fragment_invest.*

/**
 * Created by Al on 06/10/2018 for JomHack
 */

class InvestsFragment : Fragment() {

    private lateinit var mActivity : MainActivity

    private var adapterApplication : ApplicationListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = (activity as MainActivity)

        setUserData()
    }

    private fun initList(applications: List<Audit>) {
        adapterApplication = ApplicationListAdapter(applications, context!!)

        recyclerViewApplication.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerViewApplication.adapter = adapterApplication
    }

    private fun setUserData() {
        val jsonAudit = "[{\"audit_id\":\"ADT001\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID001\",\"to_customer_id\":\"CID005\",\"bankin_type\":\"RENT\",\"amount\":4700,\"point_of_interest\":4,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT001\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID001\",\"to_customer_id\":\"CID005\",\"bankin_type\":\"RENT\",\"amount\":4700,\"point_of_interest\":4,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null}]"
        val audits = Gson().fromJson(jsonAudit, Array<Audit>::class.java).asList()
        val filter : MutableList<Audit>? = mutableListOf()

        audits.forEach {
            if(it.bankinType.equals("RENT")) {
                filter?.add(it)
            }
        }

        if (filter != null) {
            initList(filter)
        }


    }

}