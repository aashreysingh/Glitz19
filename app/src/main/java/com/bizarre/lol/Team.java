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

public class Team extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView title;

    RecyclerView teamView4, teamView3, teamView2;
    TeamListAdapter teamListAdapter4, teamListAdapter3, teamListAdapter2;

    String[] Names, Details, Photo, Branch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        navigationView = findViewById(R.id.nav_drawer_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.glitz_toolbar);
        title = findViewById(R.id.actionbar_title);

        teamView4 = findViewById(R.id.teamList4year);
        teamView3 = findViewById(R.id.teamList3year);
        teamView2 = findViewById(R.id.teamList2year);

        title.setText(R.string.team);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.team);

        final Intent[] i = new Intent[1];

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        switch (menuItem.getItemId()) {

                            case R.id.home:
                                i[0] = new Intent(Team.this, MainActivity.class);
                                startActivity(i[0]);
                                break;

                            case R.id.reg_now:

                                i[0] = new Intent(Team.this, RegisterNow.class);
                                startActivity(i[0]);
                                break;

                            case R.id.instructions:

                                i[0] = new Intent(Team.this, Instructions.class);
                                startActivity(i[0]);
                                break;

                            case R.id.schedule:

                                i[0] = new Intent(Team.this, Schedule.class);
                                startActivity(i[0]);
                                break;

                            case R.id.sponsors:

                                i[0] = new Intent(Team.this, Sponsors.class);
                                startActivity(i[0]);
                                break;

                            case R.id.faqs:

                                i[0] = new Intent(Team.this, FAQs.class);
                                startActivity(i[0]);
                                break;

                            case R.id.about_us:

                                i[0] = new Intent(Team.this, AboutUs.class);
                                startActivity(i[0]);
                                break;

                            case R.id.team:
                                break;

                            case R.id.developer:

                                i[0] = new Intent(Team.this, Developers.class);
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
        this.Names = resources.getStringArray(R.array.Names4);
        this.Details = resources.getStringArray(R.array.Details4);
        this.Photo = resources.getStringArray(R.array.Photos4);
        this.Branch = resources.getStringArray(R.array.Branch4);

        teamView4.setHasFixedSize(true);
        teamListAdapter4 = new TeamListAdapter(this, this.Names, this.Details, this.Photo,this.Branch);
        teamView4.setAdapter(teamListAdapter4);
        teamView4.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        teamView4.setNestedScrollingEnabled(false);


        this.Names = resources.getStringArray(R.array.Names3);
        this.Details = resources.getStringArray(R.array.Details3);
        this.Photo = resources.getStringArray(R.array.Photos3);
        this.Branch = resources.getStringArray(R.array.Branch3);

        teamView3.setHasFixedSize(true);
        teamListAdapter3 = new TeamListAdapter(this, this.Names, this.Details, this.Photo, this.Branch);
        teamView3.setAdapter(teamListAdapter3);
        teamView3.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        teamView3.setNestedScrollingEnabled(false);

        this.Names = resources.getStringArray(R.array.Names2);
        this.Details = resources.getStringArray(R.array.Details2);
        this.Photo = resources.getStringArray(R.array.Photos2);
        this.Branch = resources.getStringArray(R.array.Branch2);

        teamView2.setHasFixedSize(true);
        teamListAdapter2 = new TeamListAdapter(this, this.Names, this.Details, this.Photo, this.Branch);
        teamView2.setAdapter(teamListAdapter2);
        teamView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        teamView2.setNestedScrollingEnabled(false);


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
        navigationView.setCheckedItem(R.id.team);
    }
}
