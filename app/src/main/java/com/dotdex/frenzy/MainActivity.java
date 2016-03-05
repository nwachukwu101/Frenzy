package com.dotdex.frenzy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private NavigationView navigationView;

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
    }

}
