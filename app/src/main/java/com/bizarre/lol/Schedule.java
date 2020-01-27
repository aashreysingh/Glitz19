package com.bizarre.lol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.sql.Time;

public class Schedule extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView title;

    RecyclerView day1, day2;
    String[] Event1, Time1, Audi1, Event2, Time2, Audi2, temp1, temp2, temp3;

    ScheduleAdapter day1Adapter, day2Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        navigationView = findViewById(R.id.nav_drawer_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.glitz_toolbar);
        title = findViewById(R.id.actionbar_title);
        day1 = findViewById(R.id.scheduleList1);
        day2 = findViewById(R.id.scheduleList2);

        title.setText(R.string.schedule);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.schedule);

        final Intent[] i = new Intent[1];

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        switch (menuItem.getItemId()) {

                            case R.id.home:
                                i[0] = new Intent(Schedule.this, MainActivity.class);
                                startActivity(i[0]);
                                break;

                            case R.id.reg_now:

                                i[0] = new Intent(Schedule.this, RegisterNow.class);
                                startActivity(i[0]);
                                break;

                            case R.id.instructions:

                                i[0] = new Intent(Schedule.this, Instructions.class);
                                startActivity(i[0]);
                                break;

                            case R.id.schedule:
                                break;

                            case R.id.sponsors:

                                i[0] = new Intent(Schedule.this, Sponsors.class);
                                startActivity(i[0]);
                                break;

                            case R.id.faqs:

                                i[0] = new Intent(Schedule.this, FAQs.class);
                                startActivity(i[0]);
                                break;

                            case R.id.about_us:

                                i[0] = new Intent(Schedule.this, AboutUs.class);
                                startActivity(i[0]);
                                break;

                            case R.id.team:

                                i[0] = new Intent(Schedule.this, Team.class);
                                startActivity(i[0]);
                                break;

                            case R.id.developer:

                                i[0] = new Intent(Schedule.this, Developers.class);
                                startActivity(i[0]);
                                break;
                        }
                    }

                }, 250);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });

        Resources resources = getResources();

        this.Event1 = resources.getStringArray(R.array.EventNamesDay1);
        this.Time1 = resources.getStringArray(R.array.EventTimesDay1);
        this.Audi1 = resources.getStringArray(R.array.EventVenuesDay1);

        day1.setHasFixedSize(true);
        day1Adapter = new ScheduleAdapter(this, this.Event1, this.Time1, this.Audi1);
        day1.setAdapter(day1Adapter);
        day1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        day1.setNestedScrollingEnabled(false);

        this.Event2 = resources.getStringArray(R.array.EventNamesDay2);
        this.Time2 = resources.getStringArray(R.array.EventTimesDay2);
        this.Audi2 = resources.getStringArray(R.array.EventVenuesDay2);

        day2.setHasFixedSize(true);
        day2Adapter = new ScheduleAdapter(this, this.Event2, this.Time2, this.Audi2);
        day2.setAdapter(day2Adapter);
        day2.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        day2.setNestedScrollingEnabled(false);


        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.schedule);
    }
}

