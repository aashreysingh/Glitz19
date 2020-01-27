package com.bizarre.lol;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpandedScreen extends AppCompatActivity {
    FloatingActionButton navigation, contact;

    Toolbar toolbar;

    TextView toolbartxt, time, venue, rord, description, coordinator1, coordinator2;

    Animation fade_in, slide_down, move_rtl, move_venue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_screen);


        toolbar = findViewById(R.id.glitz_toolbar);


        final int pos = Integer.parseInt(getIntent().getStringExtra("pos"));
        final int day = Integer.parseInt(getIntent().getStringExtra("day"));


        toolbartxt = findViewById(R.id.actionbar_title);
        time = findViewById(R.id.time_tv);
        venue = findViewById(R.id.venue_tv);
        rord = findViewById(R.id.ruleordescript);
        description = findViewById(R.id.description_tv);
        coordinator1 = findViewById(R.id.coordinator_tv1);
        coordinator2 = findViewById(R.id.coordinator_tv2);

        navigation = findViewById(R.id.venue_fab);
        contact = findViewById(R.id.contact_fab);

        fade_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        move_rtl = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_rtl);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        move_venue = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_rtl_venue);


        final Resources source = getResources();

        switch (day) {

            case 1:
                this.toolbartxt.setText(source.getStringArray(R.array.EventNamesDay1)[pos]);
                this.time.setText(source.getStringArray(R.array.EventTimesDay1)[pos]);
                time.startAnimation(move_rtl);
                this.venue.setText(source.getStringArray(R.array.EventVenuesDay1)[pos]);
                venue.startAnimation(move_venue);
                this.description.setText(source.getTextArray(R.array.EventDetailsDay1)[pos]);
                description.startAnimation(slide_down);
                this.coordinator1.setText(source.getTextArray(R.array.EventCoordinatorsNames1Day1)[pos]);
                coordinator1.startAnimation(fade_in);
                this.coordinator2.setText(source.getTextArray(R.array.EventCoordinatorsNames2Day1)[pos]);
                coordinator2.startAnimation(fade_in);
                if (pos == 2 || pos == 8)
                    this.rord.setText(source.getString(R.string.descript));
                else
                    this.rord.setText(source.getString(R.string.rules));

                contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("android.intent.action.DIAL");
                        intent.setData(Uri.parse("tel:+91" + source.getStringArray(R.array.EventCoordinatorsNumbersDay1)[pos]));
                        ExpandedScreen.this.startActivity(intent);
                    }
                });

                break;

            case 2:

                this.toolbartxt.setText(source.getStringArray(R.array.EventNamesDay2)[pos]);
                this.time.setText(source.getStringArray(R.array.EventTimesDay2)[pos]);
                time.startAnimation(move_rtl);
                this.venue.setText(source.getStringArray(R.array.EventVenuesDay2)[pos]);
                venue.startAnimation(move_venue);
                this.description.setText(source.getTextArray(R.array.EventDetailsDay2)[pos]);
                description.startAnimation(slide_down);
                this.coordinator1.setText(source.getTextArray(R.array.EventCoordinatorsNames1Day2)[pos]);
                coordinator1.startAnimation(fade_in);
                this.coordinator2.setText(source.getTextArray(R.array.EventCoordinatorsNames2Day2)[pos]);
                coordinator2.startAnimation(fade_in);
                if (pos == 7)
                    this.rord.setText(source.getString(R.string.descript));
                else
                    this.rord.setText(source.getString(R.string.rules));

                contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("android.intent.action.DIAL");
                        intent.setData(Uri.parse("tel:+91" + source.getStringArray(R.array.EventCoordinatorsNumbersDay2)[pos]));
                        ExpandedScreen.this.startActivity(intent);
                    }
                });

                break;


        }

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 8) {
                    ExpandedScreen.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(ExpandedScreen.this.getResources().getString(R.string.admin_location))));
                } else {
                    ExpandedScreen.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(ExpandedScreen.this.getResources().getString(R.string.audi_location))));
                }

            }
        });


        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}

