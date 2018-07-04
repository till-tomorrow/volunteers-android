package vola.systers.com.android.ui.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vola.systers.com.android.R;
import vola.systers.com.android.ui.main.events.EventsListFragment;
import vola.systers.com.android.ui.main.map.EventsMapFragment;
import vola.systers.com.android.ui.main.scheduled.ScheduleFragment;
import vola.systers.com.android.ui.main.stared.StarredFragment;
import vola.systers.com.android.ui.profile.ProfileActivity;
import vola.systers.com.android.ui.signin.SignInActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.frame)
    FrameLayout frameLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private Context context;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main);
        ButterKnife.bind(this);

        context = this;
        fragmentManager  = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        bottomNavigationSetup();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, new EventsListFragment(), "Events").commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Events");
        }
    }

    private void bottomNavigationSetup() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.btm_nav_events:
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame, new EventsListFragment(), "Events").commit();
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setTitle("Events");
                        }
                        floatingActionButton.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.btm_nav_stared:
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame, new StarredFragment(), "Stared").commit();
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setTitle("Stared events");
                        }
                        floatingActionButton.setVisibility(View.GONE);
                        return true;
                    case R.id.btm_nav_schedule:
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame, new ScheduleFragment(), "Schedule").commit();
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setTitle("Schedule");
                        }
                        floatingActionButton.setVisibility(View.GONE);
                        return true;
                    case R.id.btm_nav_profile:
                        Intent intent = new Intent(context, ProfileActivity.class);
                        startActivity(intent);
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_share:
                Snackbar.make(findViewById(android.R.id.content), "Share app",
                        Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.action_about:
                Snackbar.make(findViewById(android.R.id.content), "About app",
                        Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.action_logout:
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.fab)
    public void showMaps() {
        String currentTag = getSupportFragmentManager().findFragmentById(R.id.frame).getTag();
        if ("Map".equals(currentTag)) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, new EventsListFragment(), "Events").commit();
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Events");
            }
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, new EventsMapFragment(), "Maps").commit();
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Maps");
            }
        }
    }

    @Override
    public void onBackPressed() {
        String currentTag = getSupportFragmentManager().findFragmentById(R.id.frame).getTag();
        if (!"Events".equals(currentTag)) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, new EventsListFragment(), "Events").commit();
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Events");
            }
        } else {
            super.onBackPressed();
        }
    }
}