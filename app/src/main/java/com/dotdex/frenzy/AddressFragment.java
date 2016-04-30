package com.dotdex.frenzy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.dotdex.frenzy.data.model.Address;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int ADDRESS_REQUEST_CODE = 303;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView addressHolder;
    private EditText phone;
    private String loc;
    private Address address;

    public AddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();
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
        address = new Select()
             .from(Address.class)
             .limit(1)
             .executeSingle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_address, container, false);


        Button nextBtn = (Button) rootView.findViewById(R.id.next_button2);
        addressHolder = (TextView) rootView.findViewById(R.id.address_holder);
        phone = (EditText) rootView.findViewById(R.id.phone);

        //set the result
        if (address!=null) {
            addressHolder.setText(address.address);
            phone.setText(address.phone);
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!addressHolder.getText().toString().trim().isEmpty()) {
                    onPaymentChoiceBtnPressed();
                }else {
                    Toast.makeText(getContext(),"Please Choose delivery loc",Toast.LENGTH_LONG).show();
                }
            }
        });





        TextView toAddressTv = (TextView) rootView.findViewById(R.id.to_address_tv);
        toAddressTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), AddressActivity.class), ADDRESS_REQUEST_CODE);
            }
        });
        return rootView;
    }

    public void onPaymentChoiceBtnPressed() {

        if (!addressHolder.getText().toString().trim().isEmpty()) {
            if (mListener != null) {
                mListener.onAddressChosen(addressHolder.getText().toString().trim(), phone.getText().toString().trim());
                mListener.onPaymentChoiceBtnPressed();
            }

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
        void onPaymentChoiceBtnPressed();

        void onAddressChosen(String trim, String add);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK && requestCode == ADDRESS_REQUEST_CODE)
        {
            Bundle bundle = data.getExtras();
            String address = bundle.getString("address");
            String phoneNo = bundle.getString("phone");
            addressHolder.setText(address);
            phone.setText(phoneNo);
        }
    }
}
