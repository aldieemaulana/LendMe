package com.jomhack.lendme.fragment.borrower;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jomhack.lendme.R;
import com.jomhack.lendme.adapter.ApplicationListAdapter;
import com.jomhack.lendme.adapter.BorrowerApplicationListAdapter;
import com.jomhack.lendme.model.Audit;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link BorrowerHistoryFragment.OnFragmentInteractionListener} interface to handle interaction
 * events. Use the {@link BorrowerHistoryFragment#newInstance} factory method to create an instance
 * of this fragment.
 */
public class BorrowerHistoryFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;
  private BorrowerApplicationListAdapter adapterApplication;
  private RecyclerView recyclerViewApplication;

  public BorrowerHistoryFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment BorrowerBoardFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static BorrowerHistoryFragment newInstance(String param1, String param2) {
    BorrowerHistoryFragment fragment = new BorrowerHistoryFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_borrower_board, container, false);
    recyclerViewApplication = view.findViewById(R.id.recyclerViewApplication);
    setUserData();
    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this fragment to allow an
   * interaction in this fragment to be communicated to the activity and potentially other fragments
   * contained in that activity.
   *
   * <p>See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html" >Communicating with
   * Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }

  private void setUserData() {
    String jsonAudit =
        "[{\"audit_id\":\"ADT001\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\","
            + "\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID001\",\"to_customer_id\":\"CID005\",\"bankin_type\":\"Paid\","
            + "\"amount\":4700,\"point_of_interest\":4,\"status\":\"Paid\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\","
            + "\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\","
            + "\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5," +
                "\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":" +
                "\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\"," +
                "\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"Paid\",\"amount\":3000,\"point_of_interest\":4," +
                "\"status\":\"Paid\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\"," +
                "\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\"," +
                "\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000," +
                "\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":" +
                "\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\"," +
                "\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\"," +
                "\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2," +
                "\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\"," +
                "\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\"," +
                "\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5," +
                "\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\"," +
                "\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\"," +
                "\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4," +
                "\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\"," +
                "\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\"," +
                "\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000," +
                "\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\"," +
                "\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\"," +
                "\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\"," +
                "\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\"," +
                "\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT001\",\"insert_by\":\"SYSADMIN\"," +
                "\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\"," +
                "\"from_customer_id\":\"CID001\",\"to_customer_id\":\"CID005\",\"bankin_type\":\"RENT\",\"amount\":4700," +
                "\"point_of_interest\":4,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\"," +
                "\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":" +
                "\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\"," +
                "\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null}," +
                "{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":" +
                "\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\"," +
                "\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null},{\"audit_id\":\"ADT002\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID002\",\"bankin_type\":\"RENT\",\"amount\":5500,\"point_of_interest\":5,\"status\":\"Waiting\",\"number_of_month\":6,\"remark\":null},{\"audit_id\":\"ADT003\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID003\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"LEND\",\"amount\":3000,\"point_of_interest\":4,\"status\":\"Approved\",\"number_of_month\":3,\"remark\":null},{\"audit_id\":\"ADT004\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID005\",\"to_customer_id\":\"CID003\",\"bankin_type\":\"LEND\",\"amount\":10000,\"point_of_interest\":6,\"status\":\"Waiting\",\"number_of_month\":5,\"remark\":null},{\"audit_id\":\"ADT005\",\"insert_by\":\"SYSADMIN\",\"insert_date\":\"2018-10-06 13:53:56\",\"update_by\":\"SYSADMIN\",\"update_date\":\"2018-10-06 13:53:56\",\"from_customer_id\":\"CID002\",\"to_customer_id\":\"CID004\",\"bankin_type\":\"RENT\",\"amount\":2000,\"point_of_interest\":3,\"status\":\"Waiting\",\"number_of_month\":2,\"remark\":null}]";

    Type listType = new TypeToken<ArrayList<Audit>>() {}.getType();

    ArrayList<Audit> audits = new Gson().fromJson(jsonAudit, listType);

    if (audits != null) {
      initList(audits);
    }
  }

  private void initList(List<Audit> auditList) {
    adapterApplication = new BorrowerApplicationListAdapter(auditList, getContext(),false);

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerViewApplication.setLayoutManager(layoutManager);
    recyclerViewApplication.setAdapter(adapterApplication);
  }
}
