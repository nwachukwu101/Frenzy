package com.dotdex.frenzy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dotdex.frenzy.adapters.MenuAdapter;
import com.dotdex.frenzy.model.Basket;
import com.dotdex.frenzy.model.Menu;
import com.dotdex.frenzy.model.MenuOption;
import com.dotdex.frenzy.model.Order;
import com.dotdex.frenzy.util.BadgeDrawable;
import com.dotdex.frenzy.util.MenuBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuAdapter.MenuItemInteractionListener {

    private static final int GRID_COUNT = 1;
    private static final int ORDER_REQUEST_CODE = 301;
    private static final int CHECK_OUT_REQUEST_CODE = 302;
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    private RecyclerView recycler;
    private MenuAdapter adapter;
    private ArrayList<Menu> menusList;
    private ArrayList options;
    private Basket myBasket;
    private FloatingActionButton checkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //instantiate the basket
        myBasket = new Basket("");

        checkoutBtn= (FloatingActionButton) findViewById(R.id.fab);
        checkoutBtn.setBackgroundColor(Color.TRANSPARENT);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!myBasket.getOrders().isEmpty()) {
                    //here the usr can now forward to check out
                    Intent cIntent = new Intent(MainActivity.this, CheckOutActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("basket", myBasket);
                    cIntent.putExtras(bundle);
                    startActivityForResult(cIntent, CHECK_OUT_REQUEST_CODE);
                } else {
                    Snackbar.make(view, "Please add MyOrder(s) to basket before checkout.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });


        //the array
        options = new ArrayList<>();
        options.add(new MenuOption(R.drawable.ic_local_restaurant, getString(R.string.order), getString(R.string.order_sub)));
        options.add(new MenuOption(R.drawable.ic_share, getString(R.string.share), getString(R.string.share_sub)));
//        options.add(new MenuOption(R.drawable.ic_flag, getString(R.string.flag_rr), getString(R.string.click_frr)));


        //initialize the drawer layout
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        //action bar drawer Toggle
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerlayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //TransitionManager.beginDelayedTransition(drawerlayout, new Fade());
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //TransitionManager.beginDelayedTransition(drawerlayout, new Fade());
            }

        };

        //set the drawerLayout Listener
        drawerlayout.setDrawerListener(drawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        drawerToggle.syncState();


        //initialise the Navigation view
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //make all the menu icons to appear as required
//       navigationView.setItemIconTintList(null);
        //set the item text color
//        navigationView.setItemTextColor(getResources().getColorStateList(R.color.colorPrimary));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_address:
                        startActivity(new Intent(MainActivity.this,AddressActivity.class));
                        return true;
                    case R.id.action_deals:
                        startActivity(new Intent(MainActivity.this,DealsActivity.class));
                        return true;
                    case R.id.action_orders:
                        startActivity(new Intent(MainActivity.this,MyOrdersActivity.class));
                        return true;
                }
                return false;
            }
        });


        //get the adapter
        recycler = (RecyclerView) findViewById(R.id.menu_recycler);

        //start creating the menu
        menusList =buildAllMenu();
//        menusList.add(new Menu(202, "Egusi Soup", "One Plate of Egusi Soup and Santa without meat. With Bitter Leave",
//                ContextCompat.getDrawableId(this, R.drawable.food), 200));

        adapter = new MenuAdapter(this, menusList);

        //get the Layout manager for the Recycler view
        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(GRID_COUNT, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(mStaggeredLayoutManager);

        recycler.setAdapter(adapter);

        createCartBadge(0);
    }

    private ArrayList<Menu> buildAllMenu() {
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(MenuBuilder.build(101));
        arrayList.add(MenuBuilder.build(102));
        arrayList.add(MenuBuilder.build(103));
        arrayList.add(MenuBuilder.build(104));
        arrayList.add(MenuBuilder.build(105));
        arrayList.add(MenuBuilder.build(106));
        arrayList.add(MenuBuilder.build(107));
        arrayList.add(MenuBuilder.build(108));
        arrayList.add(MenuBuilder.build(109));
        arrayList.add(MenuBuilder.build(201));

        return arrayList;
    }

    @Override
    public void shareBtnClicked(final int position, final int menuId) {
//
//        //show the more menu dialog.
//        //here the overflow button is clicked
//        OverFlowAdapter adapter = new OverFlowAdapter(this, options);
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
////            builder.setTitle("");
////            builder.setCancelable(false);
//        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //what will happen here
//                // TODO: 07-Mar-16 make something happen/\.
//                switch (i)
//                {
//                    case 0:
//
//                        break;
//                    case 1:
//                        break;
//                }
//
//            }
//        });
//        builder.create();
//        builder.show();
    }

    @Override
    public void toOrder(int position, int menuId) {
        //here why the intent is passed i should parcel the
        //order class and pass it along with it
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("adaptPosition", position);
        bundle.putInt("menuId", menuId);
        orderIntent.putExtras(bundle);
        startActivityForResult(orderIntent, ORDER_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==ORDER_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            if (bundle!=null)
            {
                Order order = bundle.getParcelable("order");
                int pos = bundle.getInt("adaptPos");
                Menu menu = MenuBuilder.build(bundle.getInt("menuId"));
                if (order!=null) {
                    menu.setCurrentPrice(String.format(getString(R.string.format_pay),
                            order.getOrderUnitPrice(),order.getOrderQty(), order.getOrderTotalPrice()));
                }
                adapter.updateMenu(menu, pos);

                //animate basket and update its count
                YoYo.with(Techniques.Bounce)
                        .playOn(checkoutBtn);
                //also add order to basket
                //add the order to the basket
                myBasket.addOrder(order);
                createCartBadge(myBasket.getAllCount());


            }
        }
        if (resultCode==Activity.RESULT_CANCELED && requestCode ==CHECK_OUT_REQUEST_CODE)
        {
            //user wishes to edit the order
            //so do not rest

        }

        if (resultCode == Activity.RESULT_OK && requestCode == CHECK_OUT_REQUEST_CODE) {
            emptyBasket();

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void emptyBasket() {
        myBasket.empty();
        menusList = buildAllMenu();
        adapter.swapMenu(menusList);
        createCartBadge(0);
    }


    private void createCartBadge(int paramInt) {
        if (Build.VERSION.SDK_INT <= 15) {
            return;
        }
        LayerDrawable localLayerDrawable = (LayerDrawable) ContextCompat.getDrawable(this,R.drawable.basket_badge_icon);
        Drawable cartBadgeDrawable = localLayerDrawable.findDrawableByLayerId(R.id.ic_badge);
        BadgeDrawable badgeDrawable;
        if ((cartBadgeDrawable != null)
                && ((cartBadgeDrawable instanceof BadgeDrawable))
                && (paramInt < 10)) {
            badgeDrawable = (BadgeDrawable) cartBadgeDrawable;
        } else {
            badgeDrawable = new BadgeDrawable(this);
        }
        badgeDrawable.setCount(paramInt);
        localLayerDrawable.mutate();
        localLayerDrawable.setDrawableByLayerId(R.id.ic_badge, badgeDrawable);
        checkoutBtn.setImageDrawable(localLayerDrawable);
    }



}
