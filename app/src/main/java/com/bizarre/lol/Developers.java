package com.bizarre.lol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class Developers extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView title, dev_name, dev_ContactNo, dev_email;
    FloatingActionButton Contact, Facebook, Email;
    CardView cardView;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        navigationView = findViewById(R.id.nav_drawer_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.glitz_toolbar);
        title = findViewById(R.id.actionbar_title);

        dev_name = findViewById(R.id.dev_name);
        dev_ContactNo = findViewById(R.id.dev_call_number);
        dev_email = findViewById(R.id.dev_email_id);

        Contact = findViewById(R.id.dev_contact_fab);
        Facebook = findViewById(R.id.dev_facebook_fab);
        Email = findViewById(R.id.dev_gmail_fab);

        cardView = findViewById(R.id.cardView);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        cardView.startAnimation(animation);

        title.setText(R.string.developer);

        dev_name.setText(R.string.name);
        dev_ContactNo.setText(R.string.personal_contact);
        dev_email.setText(R.string.email_id);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        dev_ContactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.personal_contact)));
                Developers.this.startActivity(intent);
            }
        });

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.personal_contact)));
                Developers.this.startActivity(intent);
            }
        });

        dev_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","aashreytemp002@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RE: From Glitz App");
                emailIntent.putExtra(Intent.EXTRA_TEXT   , "Hello Aashrey,\n");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Developers.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","aashreytemp002@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RE: From Glitz App");
                emailIntent.putExtra(Intent.EXTRA_TEXT   , "Hello Aashrey,\n");
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Developers.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {final String urlFb = "fb://profile/"+"100007804336963";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlFb));

                // If a Facebook app is installed, use it. Otherwise, launch
                // a browser
                final PackageManager packageManager = getPackageManager();
                List<ResolveInfo> list =
                        packageManager.queryIntentActivities(intent,
                                PackageManager.MATCH_DEFAULT_ONLY);
                if (list.size() == 0) {
                    final String urlBrowser = "https://www.facebook.com/"+"Aashrey.Singh911";
                    intent.setData(Uri.parse(urlBrowser));
                }

                startActivity(intent);
            }
        });


        navigationView.setCheckedItem(R.id.developer);
        final Intent[] i = new Intent[1];

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        switch (menuItem.getItemId()) {

                            case R.id.home:
                                i[0] = new Intent(Developers.this, MainActivity.class);
                                startActivity(i[0]);
                                break;


                            case R.id.reg_now:

                                i[0] = new Intent(Developers.this, RegisterNow.class);
                                startActivity(i[0]);
                                break;


                            case R.id.instructions:

                                i[0] = new Intent(Developers.this, Instructions.class);
                                startActivity(i[0]);
                                break;


                            case R.id.schedule:

                                i[0] = new Intent(Developers.this, Schedule.class);
                                startActivity(i[0]);
                                break;


                            case R.id.sponsors:

                                i[0] = new Intent(Developers.this, Sponsors.class);
                                startActivity(i[0]);
                                break;


                            case R.id.faqs:

                                i[0] = new Intent(Developers.this, FAQs.class);
                                startActivity(i[0]);
                                break;


                            case R.id.about_us:

                                i[0] = new Intent(Developers.this, AboutUs.class);
                                startActivity(i[0]);
                                break;


                            case R.id.team:

                                i[0] = new Intent(Developers.this, Team.class);
                                startActivity(i[0]);
                                break;


                            case R.id.developer:
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
        navigationView.setCheckedItem(R.id.developer);
    }
}
