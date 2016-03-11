package com.dotdex.frenzy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
        AddressFragement.OnFragmentInteractionListener,
        PaymentFragment.OnFragmentInteractionListener{

    private static final int ORDER_REQUEST_CODE = 306;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Basket myBasket;
    private List<Order> orders;
    private TextView totalTv;
    private boolean orderTotalled,deliveryTypeChoosen,deliveryAddressChoosen,paymentTypechoosen,orderPlaced;
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
            totalTv.setText(String.format(getString(R.string.format_naira), myBasket.getBasketTotal()));

            adapter.addFragment(TotalOrderFragment.newInstance(bundle), "Total");
            adapter.addFragment(new DeliveryTypeFragment(), "Total");
            adapter.addFragment(new AddressFragement(), "Total");
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
                if (adapterInitPos == 0)//slide moving in
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

                            p3.setBackground(null);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).playOn(p3);
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
    public void onPlaceOrderBtnPressed() {

        //here i shall process the order
        //check if the user is loged in
        //AND ask them to authenticate\
        Intent orderIntent = new Intent(this,OrderDealActivity.class);
        Bundle bundle = new Bundle();

        //add the bundle
        orderIntent.putExtras(bundle);
        startActivityForResult(orderIntent, ORDER_REQUEST_CODE);
    }

    @Override
    public void onDeliveryAddressChoiceBtnPressed() {

        viewPager.setCurrentItem(2, true);
    }

    @Override
    public void onDeliveryTypeBtnPressed() {

        viewPager.setCurrentItem(1,true);

    }


    //get the resluts

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
