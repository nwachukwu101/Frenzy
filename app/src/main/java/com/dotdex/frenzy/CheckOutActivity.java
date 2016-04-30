package com.dotdex.frenzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dotdex.frenzy.adapters.ViewPagerAdapter;
import com.dotdex.frenzy.model.Basket;
import com.dotdex.frenzy.model.Order;
import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity implements
        TotalOrderFragment.OnFragmentInteractionListener,
        DeliveryTypeFragment.OnFragmentInteractionListener,
        AddressFragment.OnFragmentInteractionListener,
        PaymentFragment.OnFragmentInteractionListener{

    private static final int ORDER_REQUEST_CODE = 306;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Basket myBasket;
    private List<Order> orders;
    private TextView totalTv;
    private boolean orderTotalled, deliveryTypeChosen,deliveryAddressChosen, paymentTypeChosen,orderPlaced;
    private View p1;
    private View p2;
    private View p3;
    private View p4;
    private int adapterInitPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init vars
        myBasket = null;
        orders = new ArrayList<>();

        //for my adapter animation
        adapterInitPos = 0;

        //get all the progress views
        p1 = findViewById(R.id.bg1);
        //slide p1
        YoYo.with(Techniques.SlideInLeft).playOn(p1);
        p2 = findViewById(R.id.bg2);
        p3 = findViewById(R.id.bg3);
        p4 = findViewById(R.id.bg4);

        //get the viewPager
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //get the total tv
        totalTv = (TextView)findViewById(R.id.top_total_tv);

        //get the view pager adapter
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //get the intent from the sender

        if (this.getIntent()!=null)
        {
            Bundle bundle = getIntent().getExtras();
            myBasket = bundle.getParcelable("basket");
            myBasket.calculateBasket();
            setOrderTotalled(true);
            totalTv.setText(String.format(getString(R.string.format_naira), myBasket.getTotal()));
            Bundle b = new Bundle();
            b.putDouble("d_charge",myBasket.getDeliveryCharge());
            adapter.addFragment(TotalOrderFragment.newInstance(bundle), "Total");
            adapter.addFragment(DeliveryTypeFragment.newInstance(b), "Total");
            adapter.addFragment(new AddressFragment(), "Total");
            adapter.addFragment(new PaymentFragment(), "Total");

        }

        viewPager.setAdapter(adapter);

        //set the swipe listener
       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               AnimateProgress(position);
               adapterInitPos = position;
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });


    }

    private void AnimateProgress(int position) {
        switch (position)
        {
            case 0:
                if (adapterInitPos == -1)//slide moving in
                {
                    p1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_bg));
                    YoYo.with(Techniques.SlideInLeft).playOn(p1);

                } else{
                    //slide moving out

                    YoYo.with(Techniques.SlideOutLeft).withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            p2.setBackground(null);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {


                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).playOn(p2);
                    p1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_bg));

                }
                break;
            case 1:
                //second position
                if (adapterInitPos == 0)
                //slide moving in
                {
                    p1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sea_bg));
                    p2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_bg));
                    YoYo.with(Techniques.SlideInLeft).playOn(p2);

                } else{
                    //slide moving out
                    YoYo.with(Techniques.SlideOutLeft).withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            p4.setBackground(null);
                            //after now play on p3
                            YoYo.with(Techniques.SlideOutLeft).withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {

                                    p3.setBackground(null);
                                    //after now play on p3
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).playOn(p3);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).playOn(p4);
                    //replace the current bg
                    p2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_bg));

                }
                break;
            case 2:

                if (adapterInitPos==1)//slide moving in
                {

                    p1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sea_bg));
                    p2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sea_bg));
                    p3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_bg));
                    YoYo.with(Techniques.SlideInLeft).playOn(p3);

                }else{
                    //slide moving out

                    YoYo.with(Techniques.SlideOutLeft).withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            p4.setBackground(null);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).playOn(p4);
                    p3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_bg));

                }

                break;
            case 3:
                if (adapterInitPos == 2) {
                    p1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sea_bg));
                    p2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sea_bg));
                    p3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sea_bg));
                    p4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.menu_bg));
                    YoYo.with(Techniques.SlideInLeft).playOn(p4);
                }
                break;

        }


    }

    @Override
    public void onPaymentChoiceBtnPressed() {

        viewPager.setCurrentItem(3, true);
    }

    @Override
    public void onAddressChosen(String add, String p) {
        myBasket.setAddress(add);
        myBasket.setContact(p);
        setDeliveryAddressChosen(true);
    }

    @Override
    public void onPlaceOrderBtnPressed() {

        //here i shall process the order
        //check if the user is logged in
        //AND ask them to authenticate\

        if (isOrderTotalled())
        {
            //check if the user has chosen a delivery type
            if (isDeliveryTypeChosen())
            {
                //check if the user has chosen a delivery address
                if(deliveryAddressChosen)
                {
                    //check if they have also chosen a payment type
                    if(isPaymentTypeChosen())
                    {
                        //send the order to the order deal activity
                        Intent orderIntent = new Intent(this, OrderDealActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("basket", myBasket);
                        //add the bundle
                        orderIntent.putExtras(bundle);
                        startActivity(orderIntent);
                        setResult(Activity.RESULT_OK,null);
                        finish();

                    }else {
                        //tell them to choose a payment type
                        Toast.makeText(this,"Please choose a payment type.",Toast.LENGTH_LONG).show();
                        viewPager.setCurrentItem(3,true);
                    }

                }else {
                    //tell them to choose a delivery address
                    Toast.makeText(this, "Please choose delivery address.", Toast.LENGTH_LONG).show();
                    viewPager.setCurrentItem(2, true);

                }

            }else {
                //tell them to choose a delivery type
                Toast.makeText(this, "Please choose delivery type.", Toast.LENGTH_LONG).show();
                viewPager.setCurrentItem(1, true);
            }

        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED, null);
        finish();
    }

    @Override
    public void onPaymentChoiceMade(int i) {
        myBasket.setPaymentType(i);
        setPaymentTypeChosen(true);
    }

    @Override
    public void onDeliveryAddressChoiceBtnPressed() {

        viewPager.setCurrentItem(2, true);
    }

    @Override
    public void onDeliveryAddressChoiceMade(int choice) {
        myBasket.setDeliveryType(choice);
        setDeliveryTypeChosen(true);
    }

    @Override
    public void onDeliveryTypeBtnPressed() {

        viewPager.setCurrentItem(1, true);

    }


    //get the resluts

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isOrderTotalled() {
        return orderTotalled;
    }

    public void setOrderTotalled(boolean orderTotalled) {
        this.orderTotalled = orderTotalled;
    }

    public boolean isDeliveryTypeChosen() {
        return deliveryTypeChosen;
    }

    public void setDeliveryTypeChosen(boolean deliveryTypeChosen) {
        this.deliveryTypeChosen = deliveryTypeChosen;
    }

    public boolean isDeliveryAddressChosen() {
        return deliveryAddressChosen;
    }

    public void setDeliveryAddressChosen(boolean deliveryAddressChosen) {
        this.deliveryAddressChosen = deliveryAddressChosen;
    }

    public boolean isPaymentTypeChosen() {
        return paymentTypeChosen;
    }

    public void setPaymentTypeChosen(boolean paymentTypeChosen) {
        this.paymentTypeChosen = paymentTypeChosen;
    }
}
