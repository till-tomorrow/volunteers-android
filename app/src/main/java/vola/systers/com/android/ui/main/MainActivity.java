package vola.systers.com.android.ui.main;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import vola.systers.com.android.R;
import vola.systers.com.android.ui.main.events.EventsListFragment;
import vola.systers.com.android.ui.main.map.EventsMapFragment;
import vola.systers.com.android.ui.main.scheduled.ScheduleFragment;
import vola.systers.com.android.ui.main.stared.StarredFragment;
import vola.systers.com.android.ui.profile.ProfileActivity;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    public static int navItemIndex = 0;

    private static  String TAG_FAB ="list";
    private static final String TAG_EVENTS = "events";
    private static final String TAG_STARRED = "starred";
    private static final String TAG_SCHEDULE = "schedule";
    public static String CURRENT_TAG = TAG_EVENTS;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler menuHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        menuHandler = new Handler();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TAG_FAB == "list")
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.listicon, getApplicationContext().getTheme()));
                    } else {
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.listicon));
                    }
                    TAG_FAB = "map";
                    EventsMapFragment fragment = new EventsMapFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, fragment);
                    ft.commit();
                }
                else if(TAG_FAB=="map")
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.mapicon, getApplicationContext().getTheme()));
                    } else {
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.mapicon));
                    }
                    TAG_FAB = "list";
                    EventsListFragment fragment = new EventsListFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, fragment);
                    ft.commit();
                }
            }

        });

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_EVENTS;
            loadHomeFragment();
        }
    }

    /*
     * Returns respected fragment that user
     * selected from navigation menu
     */

    private void loadHomeFragment() {

        selectNavMenu();

        setToolbarTitle();

        // if user select the current navigation menu again, close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            toggleFab();
            return;
        }


        // The fragment is loaded with cross fade effect using runnable
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            menuHandler.post(mPendingRunnable);
        }

        toggleFab();

        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                if(TAG_FAB == "list")
                {
                    EventsListFragment listFragment = new EventsListFragment();
                    return listFragment;
                }
                else
                {
                    EventsMapFragment mapFragment = new EventsMapFragment();
                    return mapFragment;
                }

            case 1:
                StarredFragment starredFragment = new StarredFragment();
                return starredFragment;

            case 2:
                ScheduleFragment scheduleFragment = new ScheduleFragment();
                return scheduleFragment;

            default:
                return new EventsListFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_events:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_EVENTS;
                        break;
                    case R.id.nav_starred_events:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_STARRED;
                        break;
                    case R.id.nav_schedule:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SCHEDULE;
                        break;
                    case R.id.nav_profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_share:
                        // launch new intent instead of loading fragment
                        // TODO : Share APP

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        android.support.v7.app.ActionBarDrawerToggle actionBarDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_EVENTS;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return id == R.id.action_logout || super.onOptionsItemSelected(item);
    }

    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0) {
            fab.show();
        }

        else
            fab.hide();
    }
}

