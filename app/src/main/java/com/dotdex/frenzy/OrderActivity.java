package com.dotdex.frenzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotdex.frenzy.model.Menu;
import com.dotdex.frenzy.model.Order;
import com.dotdex.frenzy.util.MenuBuilder;

public class OrderActivity extends AppCompatActivity {

    private TextView orderName;
    private TextView orderDesc;
    private TextView orderBasicAmount;
    private ImageButton plusBtn;
    private ImageButton minusBtn;
    private TextView qty;
    private TextView totalPriceTv;
    private TextView addBtn;
    private Menu menu;
    private int qtyCount;
    private double totalPrice;
    private int adaptPos;
    private ImageView oderImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //once the activity is created, i shall grab the intent
        //start getting the action values
        qtyCount = 1;
        totalPrice = 0.0;
        if (this.getIntent()!=null)
        {
            Bundle bundle  = getIntent().getExtras();
            menu = MenuBuilder.build(bundle.getInt("menuId"));
            adaptPos = bundle.getInt("adaptPosition");
//            menu = bundle.getParcelable("com.dotdex.frenzy.model.Menu");
        }else {
            menu = null;
            //take the user back this can not be.
        }

        oderImage = (ImageView) findViewById(R.id.menu_image);
        orderName = (TextView) findViewById(R.id.order_name_tv);
        orderDesc = (TextView) findViewById(R.id.order_desc_tv);
        orderBasicAmount = (TextView) findViewById(R.id.order_basic_amt);
        plusBtn = (ImageButton) findViewById(R.id.plus_btn);
        minusBtn = (ImageButton) findViewById(R.id.minus_btn);
        qty = (TextView)findViewById(R.id.qty_tv);
        totalPriceTv = (TextView) findViewById(R.id.total_price_tv);
        addBtn = (TextView) findViewById(R.id.add_button);


        if (menu != null) {
            oderImage.setImageDrawable(ContextCompat.getDrawable(this,menu.getDrawableId()));
            orderName.setText(menu.getMenuName());
            orderDesc.setText(menu.getMenuDescription());
            orderBasicAmount.setText(String.format(getString(R.string.format_naira), menu.getMenuPrice()));
            qty.setText(String.format(getString(R.string.format_quantitiy), qtyCount));
            totalPrice = menu.getMenuPrice();
            totalPriceTv.setText(String.format(getString(R.string.format_naira), totalPrice));
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //here the user must have made order and the
                //default order is one

                Order mOrder = Order.fromMenu(menu,qtyCount,totalPrice);
                Bundle bundle = new Bundle();
                bundle.putParcelable("order", mOrder);
                bundle.putInt("menuId",menu.getMenuId());
                bundle.putInt("adaptPos", adaptPos);
                Intent rIntent = new Intent();
                rIntent.putExtras(bundle);
                setResult(Activity.RESULT_OK,rIntent);
                finish();


            }
        });

        //set the onclick listener for the plus and minus btn
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qtyCount++;
                qty.setText(String.format(getString(R.string.format_quantitiy), qtyCount));
                totalPrice += menu.getMenuPrice();
                totalPriceTv.setText(String.format(getString(R.string.format_naira), totalPrice));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qtyCount!=1)
                {
                    qtyCount--;
                    qty.setText(String.format(getString(R.string.format_quantitiy), qtyCount));
                    totalPrice -= menu.getMenuPrice();
                    totalPriceTv.setText(String.format(getString(R.string.format_naira), totalPrice));
                }

            }
        });

    }

}
