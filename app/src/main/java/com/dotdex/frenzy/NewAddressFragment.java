package com.dotdex.frenzy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dotdex.frenzy.adapters.HallAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewAddressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewAddressFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewAddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewAddressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewAddressFragment newInstance(String param1, String param2) {
        NewAddressFragment fragment = new NewAddressFragment();
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

        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_address, container, false);


        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final Spinner spinner1 = (Spinner) rootView.findViewById(R.id.sp);
        spinner1.setAdapter(new HallAdapter(getContext()));

        final TextInputLayout inputLayout = (TextInputLayout) rootView.findViewById(R.id.text_wrap);
        final TextInputLayout inputLayout1 = (TextInputLayout) rootView.findViewById(R.id.text_wrap1);

        final EditText roomTv = (EditText) rootView.findViewById(R.id.room_tv);
        final EditText phone = (EditText) rootView.findViewById(R.id.number_tv);


        Button addBtn = (Button) rootView.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = roomTv.getText().toString().trim();
                String p = phone.getText().toString().trim();
                String hall = spinner1.getSelectedItem().toString();
                if (!hall.isEmpty()) {
                    if (r.length() == 0 | r.length() < 3) {
                        inputLayout.setError("You room number must be 3 digits");

                    } else if (p.length() == 0 | p.length() < 3) {

                        inputLayout1.setError("You must enter a phone number");
                    } else if(p.length()>11){

                        inputLayout1.setError("You phone number is too long");
                    }else {

                        String address = hall + " Hall, Room " + r;
                        String phoneText = phone.getText().toString().trim();
                        onAddBtnPressed(address, phoneText);
                        dismiss();

                    }
                }else {
                    Toast.makeText(getContext(),"Please choose a hall from the oprions",Toast.LENGTH_LONG).show();
                }
            }
        });




        return rootView;
    }

    public void onAddBtnPressed(String address, String phoneText) {
        if (mListener != null) {
            mListener.onAddBtnPressed(address,phoneText);
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
        void onAddBtnPressed(String s, String phone);
    }
}
