package com.dotdex.frenzy;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.dotdex.frenzy.adapters.MenuAdapter;
import com.dotdex.frenzy.adapters.OverFlowAdapter;
import com.dotdex.frenzy.model.Menu;
import com.dotdex.frenzy.model.MenuOption;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuAdapter.MenuItemInteractionListener {

    private static final int GRID_COUNT = 1;
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    private RecyclerView recycler;
    private MenuAdapter adapter;
    private ArrayList<Menu> menusList;
    private ArrayList options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                return false;
            }
        });


        //get the adapter
        recycler = (RecyclerView) findViewById(R.id.menu_recycler);

        //start creating the menu
        menusList = new ArrayList<>();
        menusList.add(new Menu(101, "Fried Rice", "One Plate of fried Rice, well garnished and super delicious....sure you will bite your fingers.",
                ContextCompat.getDrawable(this, R.drawable.f_rice), 200));

        menusList.add(new Menu(102, "Jeloff Rice ", "One Plate of Jealoff Rice, from the best cook ever, your neighbours will salivate when they percieve the aroma. ",
                ContextCompat.getDrawable(this, R.drawable.f_rice), 200));

        menusList.add(new Menu(103, "Egusi Soup", "One Plate of Egusi Soup with one Sant. Trust me.. you can always order more santa to your need.",
                ContextCompat.getDrawable(this, R.drawable.egusi_soup), 200));

        menusList.add(new Menu(104, "Ogbono Soup", "One Plate of Ogbono soup with Santa. You can always order more santa.. i trust you.",
                ContextCompat.getDrawable(this, R.drawable.ogbono_soup), 200));

        menusList.add(new Menu(105, "Frenzy Moi Moi", "One Measure of moi super delicious and carefully prepared and well packed",
                ContextCompat.getDrawable(this, R.drawable.moi_moi), 50));

        menusList.add(new Menu(106, "Frenzy Salad", "A measure of Frenzy delicious Salad. You can enjoy your Rice with frenzy salad at a cheap price",
                ContextCompat.getDrawable(this, R.drawable.f_rice), 50));

        menusList.add(new Menu(107, "Frenzy Fish", "One Slice of fish for your frenzy order. Well fried and prepared for u. Please don't eat without",
                ContextCompat.getDrawable(this, R.drawable.fish), 50));

        menusList.add(new Menu(108, "Frenzy Meat", "One slice of Frenzy meat to supplement your choice of fish still at affordable price.",
                ContextCompat.getDrawable(this, R.drawable.beaf), 50));

        menusList.add(new Menu(109, "Frenzy Coca Cola Beverages", "Enjoy your food with coke and don't forget to share with friends. Choose from Fanta, Coke, Sprite,Sweeps...",
                ContextCompat.getDrawable(this, R.drawable.food), 70));

        menusList.add(new Menu(201, "Frenzy Bottled Water", "Water is important so make your choice of bottled water.",
                ContextCompat.getDrawable(this, R.drawable.f_rice), 200));

//        menusList.add(new Menu(202, "Egusi Soup", "One Plate of Egusi Soup and Santa without meat. With Bitter Leave",
//                ContextCompat.getDrawable(this, R.drawable.food), 200));

        adapter = new MenuAdapter(this, menusList);

        //get the Layout manager for the Recycler view
        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(GRID_COUNT, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(mStaggeredLayoutManager);

        recycler.setAdapter(adapter);
    }

    @Override
    public void moreBtnClicked(int position, Menu menu) {

        //show the more menu dialog.
        //here the overflow button is clicked
        OverFlowAdapter adapter = new OverFlowAdapter(this, options);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("");
//            builder.setCancelable(false);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //what will happen here
                // TODO: 07-Mar-16 make something happen/\.

            }
        });
        builder.create();
        builder.show();
    }

}
