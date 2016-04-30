package com.dotdex.frenzy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dotdex.frenzy.model.Basket;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlaceOrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlaceOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceOrderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "basket";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Bundle bundle;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Basket myBasket;

    public PlaceOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param basket Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlaceOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceOrderFragment newInstance(Basket basket, String param2) {
        PlaceOrderFragment fragment = new PlaceOrderFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, basket);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            myBasket = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_place_order, container, false);

        TextView contactTv = (TextView) rootView.findViewById(R.id.p_contact_tv);
        TextView addressTv = (TextView) rootView.findViewById(R.id.p_address_tv);
        TextView orderNameTv = (TextView) rootView.findViewById(R.id.orders_name);
        TextView ordersAmountTv = (TextView) rootView.findViewById(R.id.orders_amount);
        TextView ordersBreakDownTv = (TextView) rootView.findViewById(R.id.break_down_amount);
        TextView totalTv = (TextView) rootView.findViewById(R.id.total_tv);

        if (myBasket !=null)
        {
            myBasket.calculateBasket();
            contactTv.setText(String.format(getString(R.string.format_contact), myBasket.getContact()));
            addressTv.setText(myBasket.getAddress());
            orderNameTv.setText(myBasket.getOrderNameString());
            ordersAmountTv.setText(myBasket.getOrderAmountString());
            ordersBreakDownTv.setText(myBasket.getBasketBreakDown());
            totalTv.setText(String.format(getString(R.string.format_naira),myBasket.getTotal()));
        }




        return rootView;
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
        void onFragmentInteraction(Uri uri);
    }
}
