package com.jomhack.lendme.fragment.borrower;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jomhack.lendme.R;
import com.jomhack.lendme.components.JomTextView;
import com.jomhack.lendme.utils.PopUpUtils;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link BorrowerInvestFragment.OnFragmentInteractionListener} interface to handle interaction
 * events. Use the {@link BorrowerInvestFragment#newInstance} factory method to create an instance
 * of this fragment.
 */
public class BorrowerInvestFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnFragmentInteractionListener mListener;
  SeekBar seekBarAmount, seekBarInterest, seekBarMonths;
  private JomTextView tvEmi;

  public BorrowerInvestFragment() {
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
  public static BorrowerInvestFragment newInstance(String param1, String param2) {
    BorrowerInvestFragment fragment = new BorrowerInvestFragment();
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
    View view = inflater.inflate(R.layout.fragment_borrower_invest, container, false);

    tvEmi = (JomTextView) view.findViewById(R.id.tvEmi);

    seekBarAmount = (SeekBar) view.findViewById(R.id.seekBarAmount);
    seekBarAmount
        .getProgressDrawable()
        .setColorFilter(
            new PorterDuffColorFilter(
                getActivity().getColor(R.color.startblue), PorterDuff.Mode.SRC_IN));
    seekBarAmount.setMin(1);
    seekBarAmount.setProgress(1);
    seekBarAmount.setMax(10000);
    final JomTextView tvAmount = (JomTextView) view.findViewById(R.id.tvAmount);
    tvAmount.setText("RM " + String.valueOf(seekBarAmount.getProgress()));
    seekBarAmount.setOnSeekBarChangeListener(
        new SeekBar.OnSeekBarChangeListener() {

          @Override
          public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            tvAmount.setText("RM " + String.valueOf(i));
            calculateEmi();
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {}

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    seekBarInterest = (SeekBar) view.findViewById(R.id.seekBarInterest);
    final JomTextView tvInterest = (JomTextView) view.findViewById(R.id.tvInterest);
    seekBarInterest.setMin(1);
    seekBarInterest.setMax(30);
    seekBarInterest.setProgress(1);
    tvInterest.setText(String.valueOf(seekBarInterest.getProgress()) + " p.a");
    seekBarInterest.setOnSeekBarChangeListener(
        new SeekBar.OnSeekBarChangeListener() {

          @Override
          public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            tvInterest.setText(String.valueOf(i) + " p.a");
            calculateEmi();
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {}

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    seekBarMonths = (SeekBar) view.findViewById(R.id.seekBarMonths);
    seekBarMonths.setMin(1);
    seekBarMonths.setMax(24);
    seekBarMonths.setProgress(1);

    final JomTextView tvMonths = (JomTextView) view.findViewById(R.id.tvMonths);
    tvMonths.setText(String.valueOf(seekBarMonths.getProgress()) + " months");
    seekBarMonths.setOnSeekBarChangeListener(
        new SeekBar.OnSeekBarChangeListener() {

          @Override
          public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            tvMonths.setText(String.valueOf(i) + " months");
            calculateEmi();
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {}

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    final Button buttonSubmit = (Button) view.findViewById(R.id.btnSubmit);

    buttonSubmit.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            resetProgress();
              PopUpUtils.showPopup(getActivity(),getString(R.string.text_sucessfully_updated));
          }
        });

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

  public void resetProgress() {
    seekBarAmount.setProgress(1);
    seekBarMonths.setProgress(1);
    seekBarInterest.setProgress(1);
      tvEmi.setText("MYR " + 0);
  }

  public float calculateEmi() {

    int principal = seekBarAmount.getProgress()==0?1:seekBarAmount.getProgress();
    float interest = (seekBarInterest.getProgress()==0? 1:seekBarInterest.getProgress());
    float years = (seekBarMonths.getProgress()==0?1:seekBarMonths.getProgress()) ;

    if(interest==0) interest=1;
    if(years == 0) years=1;

    float total = (principal + (principal * interest * years)/100*12) / years * 12;

    tvEmi.setText("MYR " + total);

    return total;
  }
}
