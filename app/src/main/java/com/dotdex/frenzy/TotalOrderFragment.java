package com.dotdex.frenzy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dotdex.frenzy.adapters.OrderItemsAdapter;
import com.dotdex.frenzy.model.Basket;
import com.dotdex.frenzy.model.Order;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TotalOrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TotalOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TotalOrderFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "bundle";
    private static final int GRID_COUNT = 1;

    private Bundle myBundle;

    private OnFragmentInteractionListener mListener;
    private double total;
    private double subTotal;
    private double sCharge;
    private double dCharge;
    private double vat;
    private List<Order> orders;
    private Basket myBasket;
    private TextView totalTv,subTotalTv,sChargeTv,dChrageTv,vatTv;

    public TotalOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param bundle Parameter 1.
     * @return A new instance of fragment TotalOrderFragment.
     */
     public static TotalOrderFragment newInstance(Bundle bundle) {
        TotalOrderFragment fragment = new TotalOrderFragment();
        Bundle args = new Bundle();
        args.putBundle(ARG_PARAM1, bundle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            myBundle = getArguments().getBundle(ARG_PARAM1);
            //extract all from teh bundle

            myBasket  = myBundle.getParcelable("basket");


            total = myBasket.getBasketTotal();
            subTotal = myBasket.getSubTotal();
            sCharge = myBasket.getServiceCharge();
            dCharge = myBasket.getDeliveryCharge();
            vat = myBasket.getVat();
            orders = myBasket.getOrders();


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_total_order, container, false);

        //get the recycler
        RecyclerView oRec = (RecyclerView) rootView.findViewById(R.id.check_recycler);
        //set the layout manager
        StaggeredGridLayoutManager stgmg = new StaggeredGridLayoutManager(GRID_COUNT, StaggeredGridLayoutManager.VERTICAL);

        oRec.setLayoutManager(stgmg);

        //get the recycler view adapter
        OrderItemsAdapter adapter = new OrderItemsAdapter(getContext(),orders);

        oRec.setAdapter(adapter);

        //get the text Views


        totalTv = (TextView)rootView.findViewById(R.id.total_tv);
        subTotalTv = (TextView)rootView.findViewById(R.id.subtotal_tv);
        sChargeTv = (TextView) rootView.findViewById(R.id.s_charge_tv);
        dChrageTv = (TextView) rootView.findViewById(R.id.d_charge_tv);
        vatTv = (TextView) rootView.findViewById(R.id.vat_tv);


        if (myBasket!=null)
        {
            totalTv.setText(String.format(getContext().getString(R.string.format_naira),total));
            subTotalTv.setText(String.format(getContext().getString(R.string.format_naira), subTotal));
            sChargeTv.setText(String.format(getContext().getString(R.string.format_naira), sCharge));
            dChrageTv.setText(String.format(getContext().getString(R.string.format_naira), dCharge));
            vatTv.setText(String.format(getContext().getString(R.string.format_naira), vat));
        }

        Button nextBtn = (Button) rootView.findViewById(R.id.next_button);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeliveryTypeBtnPressed();
            }
        });

//        YoYo.with(Techniques.SlideInLeft).playOn(rootView);

        return rootView;
    }

    public void onDeliveryTypeBtnPressed() {
        if (mListener != null) {
            mListener.onDeliveryTypeBtnPressed();
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
        void onDeliveryTypeBtnPressed();
    }
}
