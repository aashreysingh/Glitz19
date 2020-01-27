package com.bizarre.lol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Sponsors extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView title, sponsortv, tagline;
    ImageView kalam;

    Animation txtanim, imganim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        navigationView = findViewById(R.id.nav_drawer_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.glitz_toolbar);
        title = findViewById(R.id.actionbar_title);
        tagline = findViewById(R.id.tagline);

        sponsortv = findViewById(R.id.sponsoredbytv);

        kalam = findViewById(R.id.sponsorimg);

        txtanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        imganim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        sponsortv.startAnimation(txtanim);
        kalam.startAnimation(imganim);
        tagline.setText(getResources().getString(R.string.tag_line)+",");


        title.setText(R.string.app_title);
        Typeface face = ResourcesCompat.getFont(getApplicationContext(),R.font.grindanddeath);
        title.setTypeface(face);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.sponsors);

        final Intent[] i = new Intent[1];

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        switch (menuItem.getItemId()) {

                            case R.id.home:
                                i[0] = new Intent(Sponsors.this, MainActivity.class);
                                startActivity(i[0]);
                                break;


                            case R.id.reg_now:

                                i[0] = new Intent(Sponsors.this, RegisterNow.class);
                                startActivity(i[0]);
                                break;

                            case R.id.instructions:

                                i[0] = new Intent(Sponsors.this, Instructions.class);
                                startActivity(i[0]);
                                break;


                            case R.id.schedule:

                                i[0] = new Intent(Sponsors.this, Schedule.class);
                                startActivity(i[0]);
                                break;


                            case R.id.sponsors:
                                break;

                            case R.id.faqs:

                                i[0] = new Intent(Sponsors.this, FAQs.class);
                                startActivity(i[0]);
                                break;


                            case R.id.about_us:

                                i[0] = new Intent(Sponsors.this, AboutUs.class);
                                startActivity(i[0]);
                                break;


                            case R.id.team:

                                i[0] = new Intent(Sponsors.this, Team.class);
                                startActivity(i[0]);
                                break;


                            case R.id.developer:

                                i[0] = new Intent(Sponsors.this, Developers.class);
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
        navigationView.setCheckedItem(R.id.sponsors);
    }
}