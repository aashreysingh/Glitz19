package com.bizarre.lol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class AboutUs extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView title;
    TextView About_UsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        navigationView = findViewById(R.id.nav_drawer_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.glitz_toolbar);
        title = findViewById(R.id.actionbar_title);
        About_UsTV = findViewById(R.id.about_usTV);

        title.setText(R.string.about_us);
        setSupportActionBar(toolbar);

        About_UsTV.setText(R.string.aboutusNote);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.about_us);
        final Intent[] i = new Intent[1];

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        switch (menuItem.getItemId()) {

                            case R.id.home:
                                i[0] = new Intent(AboutUs.this, MainActivity.class);
                                startActivity(i[0]);
                                break;

                            case R.id.reg_now:

                                i[0] = new Intent(AboutUs.this, RegisterNow.class);
                                startActivity(i[0]);
                                break;

                            case R.id.instructions:

                                i[0] = new Intent(AboutUs.this, Instructions.class);
                                startActivity(i[0]);
                                break;

                            case R.id.schedule:

                                i[0] = new Intent(AboutUs.this, Schedule.class);
                                startActivity(i[0]);
                                break;

                            case R.id.sponsors:

                                i[0] = new Intent(AboutUs.this, Sponsors.class);
                                startActivity(i[0]);
                                break;

                            case R.id.faqs:

                                i[0] = new Intent(AboutUs.this, FAQs.class);
                                startActivity(i[0]);
                                break;

                            case R.id.about_us:
                                break;

                            case R.id.team:

                                i[0] = new Intent(AboutUs.this, Team.class);
                                startActivity(i[0]);
                                break;

                            case R.id.developer:

                                i[0] = new Intent(AboutUs.this, Developers.class);
                                startActivity(i[0]);
                                break;


                        }
                    }

                }, 250);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });

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
        navigationView.setCheckedItem(R.id.about_us);
    }
}
