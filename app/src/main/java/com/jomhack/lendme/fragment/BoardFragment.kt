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
import com.jomhack.lendme.adapter.UserCardAdapter
import com.jomhack.lendme.model.Audit
import com.jomhack.lendme.model.User
import kotlinx.android.synthetic.main.fragment_board.*

/**
 * Created by Al on 06/10/2018 for JomHack
 */

class BoardFragment : Fragment() {

    private lateinit var mActivity : MainActivity

    private var adapterFriend : UserCardAdapter? = null
    private var adapterApplication : ApplicationListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = (activity as MainActivity)

        setUserData()
    }

    private fun initList(users: List<User>, applications: List<Audit>) {
        adapterFriend = UserCardAdapter(users)

        recyclerViewFriend.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerViewFriend.adapter = adapterFriend

        adapterApplication = ApplicationListAdapter(applications, context!!)

        recyclerViewApplication.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerViewApplication.adapter = adapterApplication
    }

    private fun setUserData() {
        val jsonUser = "[{\"customer_id\":\"CID001\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"first_name\":\"Ahmad\",\"last_name\":\"Bin Ismail\",\"ic_no\":\"900218-1039-0192\",\"date_of_birth\":\"1990-02-17T16:00:00.000Z\",\"gender\":\"M\",\"profile_rate\":4,\"address1\":\"No 15.\",\"address2\":\"Block 6 Taman Putra\",\"address3\":\"\",\"postal_code\":\"43000\",\"city\":\"Kajang\",\"state\":\"Selangor\",\"country\":\"Malaysia\",\"mobile_no\":\"+60182909212\",\"email\":\"ahmad@gmail.com\",\"emergency_contact_no\":\"+60192321290\",\"remark\":null},{\"customer_id\":\"CID002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"first_name\":\"Mark\",\"last_name\":\"Anthony\",\"ic_no\":\"B12678191\",\"date_of_birth\":\"1980-12-18T16:00:00.000Z\",\"gender\":\"M\",\"profile_rate\":4,\"address1\":\"15-1. Block B\",\"address2\":\"Damansara City\",\"address3\":\"\",\"postal_code\":\"56280\",\"city\":\"Petaling Jaya\",\"state\":\"Selangor\",\"country\":\"Malaysia\",\"mobile_no\":\"+60178192301\",\"email\":\"markanthony@gmail.com\",\"emergency_contact_no\":\"+60111823901\",\"remark\":null},{\"customer_id\":\"CID003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"first_name\":\"Siti\",\"last_name\":\"Jamal\",\"ic_no\":\"780128-0909-1293\",\"date_of_birth\":\"1978-01-27T16:00:00.000Z\",\"gender\":\"F\",\"profile_rate\":3,\"address1\":\"Block F. No 12\",\"address2\":\"Jalan Taman Desa\",\"address3\":\"Presint 14\",\"postal_code\":\"52019\",\"city\":\"Putrajaya\",\"state\":\"Putrajaya\",\"country\":\"Malaysia\",\"mobile_no\":\"+60168123812\",\"email\":\"sitijamal@gmail.com\",\"emergency_contact_no\":\"+60129899011\",\"remark\":null},{\"customer_id\":\"CID004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"first_name\":\"Sri\",\"last_name\":\"Rahman\",\"ic_no\":\"851006-1892-0912\",\"date_of_birth\":\"1985-10-05T16:00:00.000Z\",\"gender\":\"F\",\"profile_rate\":5,\"address1\":\"No 29\",\"address2\":\"Jalan Patani Impian\",\"address3\":\"Desa Patani\",\"postal_code\":\"89720\",\"city\":\"Kota Baharu\",\"state\":\"Kelantan\",\"country\":\"Malaysia\",\"mobile_no\":\"+60182139123\",\"email\":\"srirahman@yahoo.com\",\"emergency_contact_no\":\"+60189128192\",\"remark\":null},{\"customer_id\":\"CID005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"first_name\":\"Muhammad\",\"last_name\":\"Abrar\",\"ic_no\":\"A01293321\",\"date_of_birth\":\"1981-04-19T16:00:00.000Z\",\"gender\":\"M\",\"profile_rate\":4,\"address1\":\"No 18\",\"address2\":\"12/5 Block C\",\"address3\":\"Taman Tropika 3\",\"postal_code\":\"43600\",\"city\":\"Bangi\",\"state\":\"Selangor\",\"country\":\"Malaysia\",\"mobile_no\":\"+60121231310\",\"email\":\"muhammadabrar@yahoo.com\",\"emergency_contact_no\":\"+60192183101\",\"remark\":null}]"
        val users = Gson().fromJson(jsonUser, Array<User>::class.java).asList()

        val jsonAudit = "[{\"audit_id\":\"ADT001\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID001\",\"customer\":{\"customer_id\":\"CID001\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06T13:53:56.000Z\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06T13:53:56.000Z\",\"first_name\":\"Ahmad\",\"last_name\":\"Bin Ismail\",\"ic_no\":\"900218-1039-0192\",\"date_of_birth\":\"1990-02-17T16:00:00.000Z\",\"gender\":\"M\",\"profile_rate\":4,\"address1\":\"No 15.\",\"address2\":\"Block 6 Taman Putra\",\"address3\":\"\",\"postal_code\":\"43000\",\"city\":\"Kajang\",\"state\":\"Selangor\",\"country\":\"Malaysia\",\"mobile_no\":\"+60182909212\",\"email\":\"ahmad@gmail.com\",\"emergency_contact_no\":\"+60192321290\",\"remark\":null},\"to_customer_id\":\"CID005\",\"bankin_type\":\"RENT\",\"amount\":4700,\"point_of_interest\":4,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT001\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID001\",\"to_customer_id\":\"CID005\",\"bankin_type\":\"RENT\",\"amount\":4700,\"point_of_interest\":4,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null}]"
        val audits = Gson().fromJson(jsonAudit, Array<Audit>::class.java).asList()

        initList(users, audits)


    }
}