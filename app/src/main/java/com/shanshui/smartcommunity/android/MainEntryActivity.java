package com.shanshui.smartcommunity.android;

import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.shanshui.smartcommunity.android.util.WindowHelper;

import java.util.ArrayList;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.BACKGROUND_STYLE_STATIC;
import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED;

public class MainEntryActivity extends AppCompatActivity
        implements BottomNavigationBar.OnTabSelectedListener, NeighbourhoodFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener,
        PropertyFragment.OnFragmentInteractionListener, MeFragment.OnFragmentInteractionListener, ShoppingFragment.OnFragmentInteractionListener,
        ImageSwitcherFragment.OnFragmentInteractionListener {
    private BottomNavigationBar bottomNavigationBar;
    private ArrayList<Fragment> fragments;
    private ConstraintLayout frameLayout;
    private int mainPageSystemUiVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
//
//        fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        frameLayout = findViewById(R.id.fragment);

//        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "floatingActionButton", Snackbar.LENGTH_LONG).setActionTextColor(Color.BLUE).setAction("ok", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                }).show();
//            }
//        });
        prepareFragments();
        initBottomNavigationBar();
    }

    private void prepareFragments() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        HomeFragment hf = HomeFragment.newInstance("Home");
        transaction.add(R.id.fragment, hf).show(hf).commit();

        this.fragments = new ArrayList<>();
        fragments.add(hf);
        fragments.add(PropertyFragment.newInstance("Property"));
        fragments.add(NeighbourhoodFragment.newInstance("Neighbourhood"));
        fragments.add(ShoppingFragment.newInstance("Shopping"));
        fragments.add(MeFragment.newInstance("Me"));

    }

    private void initBottomNavigationBar() {
        //TextBadgeItem badgeItem = new TextBadgeItem().setBorderWidth(1).setBackgroundColorResource(R.color.textBadge).setText("2").setHideOnSelect(true);
        bottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        //bottomNavigationBar.setFab(fab);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBackgroundResource(R.color.bottomBackground);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_homepage, R.string.bottom_tab_home).setActiveColorResource(R.color.bottomItemSelected))
                .addItem(new BottomNavigationItem(R.drawable.ic_propertymanagement, R.string.bottom_tab_property).setActiveColorResource(R.color.bgTitleLeft))
                .addItem(new BottomNavigationItem(R.drawable.ic_neighbourhood, R.string.bottom_tab_neighbourhood).setActiveColorResource(R.color.chocolate))
                .addItem(new BottomNavigationItem(R.drawable.ic_shopping, R.string.bottom_tab_shopping).setActiveColorResource(R.color.chartreuse))
                .addItem(new BottomNavigationItem(R.drawable.ic_me, R.string.bottom_tab_me).setActiveColorResource(R.color.bottomItemSelected))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setAutoHideEnabled(false);
        bottomNavigationBar.setBackgroundStyle(BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment from = fm.findFragmentById(R.id.fragment);
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.hide(from).show(fragment);
                } else {
                    ft.hide(from).add(R.id.fragment, fragment);
                    if (fragment.isHidden()) {
                        ft.show(fragment);
                    }
                }
                if (0 == position) {
                    //mainPageSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
                    //getWindow().getDecorView().setSystemUiVisibility(mainPageSystemUiVisibility);
                    HomeFragment home = (HomeFragment) fragments.get(0);
                    //TODO: extract bar into a class for better encapslation
                    if (HomeFragment.CollapsingToolbarLayoutState.COLLAPSED.equals(home.getState())) {
                        WindowHelper.setStatusBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
                        //home.resetStatusBarTranslucent();
                    } else {
                        home.resetStatusBarLight();
                    }
                } else {
                    //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    WindowHelper.setStatusBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
                    //WindowHelper.translucentStatusBar(this);
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) { // avoid overlay
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .hide(fragments.get(position))
                        .commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
