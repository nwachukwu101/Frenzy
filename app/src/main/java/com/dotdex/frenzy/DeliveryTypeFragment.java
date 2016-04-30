package com.dotdex.frenzy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeliveryTypeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeliveryTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliveryTypeFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param";

    private Bundle mParam;

    private OnFragmentInteractionListener mListener;
    private double amount;

    public DeliveryTypeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter 1.
     * @return A new instance of fragment DeliveryTypeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeliveryTypeFragment newInstance(Bundle param) {
        DeliveryTypeFragment fragment = new DeliveryTypeFragment();
        Bundle args = new Bundle();
        args.putBundle(ARG_PARAM1, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getBundle(ARG_PARAM1);
            amount = mParam.getDouble("d_charge");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_delivery_type, container, false);

        TextView amountTv = (TextView) rootView.findViewById(R.id.d_charge_tv);
        amountTv.setText(String.format(getString(R.string.format_naira), amount));
        Button nextBtn = (Button) rootView.findViewById(R.id.next_button1);
        final RadioGroup gr1 = (RadioGroup) rootView.findViewById(R.id.radioGroup1);
        gr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                onDeliveryChoiceMade(radioGroup);

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the radio button is clicked
                if (gr1.getCheckedRadioButtonId()!=-1) {
                    onDeliveryChoiceBtnPressed();
                }else {
                Toast.makeText(getContext(), "Please Choose delivery type", Toast.LENGTH_LONG).show();
            }

            }
        });
        return rootView;
    }

    private void onDeliveryChoiceMade(RadioGroup radioGroup) {
        int choice;
        if (radioGroup.getId()==R.id.home_choice)
        {
            choice = 0;
        }else
        {
            choice = 1;
        }
        if (mListener != null) {
            mListener.onDeliveryAddressChoiceMade(choice);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onDeliveryChoiceBtnPressed() {
        if (mListener != null) {
            mListener.onDeliveryAddressChoiceBtnPressed();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDeliveryAddressChoiceBtnPressed();

        void onDeliveryAddressChoiceMade(int choice);
    }
}
