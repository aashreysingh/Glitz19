package com.bizarre.lol;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "First Time";

    ObjectAnimator animator1, animator2, animator3;

    NavigationView navigationView;

    HorizontalScrollView mhorizontalScrollView;
    Toolbar glitztoolbar;

    androidx.cardview.widget.CardView timerCard, registerCard, scheduleCard;

    androidx.constraintlayout.widget.ConstraintLayout timerLayout, registerLayout, scheduleLayout, card_day1, card_day2;

    DrawerLayout drawerLayout;

    TextView daystv, hourstv, minutestv, secondstv, reg1, reg2, checkschedule,titlebar;
    ImageView ticktock, imageday1, imageday2;

    Handler handler, handlerhs;
    Runnable runnable, runnablehs;

    RecyclerView recyclerday1, recyclerday2;
    String[] EventDay1, VenueDay1, EventDay2, VenueDay2;
    AdapterDay1 adapterDay1;
    AdapterDay2 adapterDay2;

    ImageView dashimg1, dashimg2, dashimg3, dashimg4, dashimg5, dashimg6, dashimg7, dashimg8;
    TextView dashText1, dashText2, dashText3, dashText4, dashText5, dashText6, dashText7, dashText8;

    ConstraintLayout dashitem1, dashitem2, dashitem3, dashitem4, dashitem5, dashitem6, dashitem7, dashitem8;

    FrameLayout topLevelLayout, rootLayout;

    boolean doubleBacktoExit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topLevelLayout = findViewById(R.id.overlay);
        rootLayout = findViewById(R.id.root);

        if(isFirstTime()){
            Log.i(TAG,"true is returned");
            topLevelLayout.setVisibility(View.INVISIBLE);
        }

        glitztoolbar = findViewById(R.id.glitz_toolbar);

        titlebar = findViewById(R.id.actionbar_title);

        titlebar.setAllCaps(true);
        titlebar.setText(R.string.app_title);


        reg1 = findViewById(R.id.not_reg);
        reg2 = findViewById(R.id.reg_now);

        checkschedule = findViewById(R.id.check_schedule);

        daystv = findViewById(R.id.txtdays);
        hourstv = findViewById(R.id.txthours);
        minutestv = findViewById(R.id.txtminutes);
        secondstv = findViewById(R.id.txtseconds);

        ticktock = findViewById(R.id.ticking_clock);
        imageday1 = findViewById(R.id.img1);
        imageday2 = findViewById(R.id.img2);



        mhorizontalScrollView = findViewById(R.id.horizontalScrollView);

        timerCard = findViewById(R.id.timer_card);
        registerCard = findViewById(R.id.register_card);
        scheduleCard = findViewById(R.id.schedule_card);

        timerLayout = findViewById(R.id.timer_layout);
        registerLayout = findViewById(R.id.register_layout);
        scheduleLayout = findViewById(R.id.schedule_layout);
        card_day1 = findViewById(R.id.day1_cardLayout);
        card_day2 = findViewById(R.id.day2_cardLayout);


        dashitem1 = findViewById(R.id.dashreg_now);
        dashitem2 = findViewById(R.id.dashmap);
        dashitem3 = findViewById(R.id.dashschedule);
        dashitem4 = findViewById(R.id.dashinstruct);
        dashitem5 = findViewById(R.id.dashsponsors);
        dashitem6 = findViewById(R.id.dashfaqs);
        dashitem7 = findViewById(R.id.dashabout_us);
        dashitem8 = findViewById(R.id.dashteam);

        dashimg1 = dashitem1.findViewById(R.id.dash_background);
        dashimg2 = dashitem2.findViewById(R.id.dash_background);
        dashimg3 = dashitem3.findViewById(R.id.dash_background);
        dashimg4 = dashitem4.findViewById(R.id.dash_background);
        dashimg5 = dashitem5.findViewById(R.id.dash_background);
        dashimg6 = dashitem6.findViewById(R.id.dash_background);
        dashimg7 = dashitem7.findViewById(R.id.dash_background);
        dashimg8 = dashitem8.findViewById(R.id.dash_background);

        dashText1 = dashitem1.findViewById(R.id.textfordash);
        dashText2 = dashitem2.findViewById(R.id.textfordash);
        dashText3 = dashitem3.findViewById(R.id.textfordash);
        dashText4 = dashitem4.findViewById(R.id.textfordash);
        dashText5 = dashitem5.findViewById(R.id.textfordash);
        dashText6 = dashitem6.findViewById(R.id.textfordash);
        dashText7 = dashitem7.findViewById(R.id.textfordash);
        dashText8 = dashitem8.findViewById(R.id.textfordash);

        setSupportActionBar(glitztoolbar);

        countDownStart();

        AutoScroll();

        Resources res = getResources();

        recyclerday1 = findViewById(R.id.recyclerview1);
        recyclerday1.setHasFixedSize(true);

        this.EventDay1 = res.getStringArray(R.array.EventNamesDay1);
        this.VenueDay1 = res.getStringArray(R.array.EventVenuesDay1);


        adapterDay1 = new AdapterDay1(this, this.EventDay1,this.VenueDay1);
        recyclerday1.setItemViewCacheSize(9);
        recyclerday1.setAdapter(adapterDay1);
        recyclerday1.setDrawingCacheEnabled(true);
        recyclerday1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerday1.isNestedScrollingEnabled();
        recyclerday1.addOnScrollListener(new CustomScrollListener(imageday1));
        new GravitySnapHelper(Gravity.START).attachToRecyclerView(recyclerday1);


        recyclerday2 = findViewById(R.id.recyclerview2);
        recyclerday2.setHasFixedSize(true);

        this.EventDay2 = res.getStringArray(R.array.EventNamesDay2);
        this.VenueDay2 = res.getStringArray(R.array.EventVenuesDay2);

        adapterDay2 = new AdapterDay2(this, this.EventDay2,this.VenueDay2);
        recyclerday2.setItemViewCacheSize(9);
        recyclerday2.setDrawingCacheEnabled(true);
        recyclerday2.setAdapter(adapterDay2);
        recyclerday2.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerday2.isNestedScrollingEnabled();
        recyclerday2.addOnScrollListener(new CustomScrollListener(imageday2));
        new GravitySnapHelper(Gravity.START).attachToRecyclerView(recyclerday2);

        drawerLayout = findViewById(R.id.drawer_layout_main);
        navigationView = findViewById(R.id.nav_drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,glitztoolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setCheckedItem(R.id.home);

        final Intent[] i = new Intent[1];

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (menuItem.getItemId()) {

                            case R.id.home:
                                break;

                            case R.id.reg_now:

                                i[0] = new Intent(MainActivity.this, RegisterNow.class);
                                startActivity(i[0]);
                                break;

                            case R.id.instructions:
                                i[0] = new Intent(MainActivity.this, Instructions.class);
                                startActivity(i[0]);
                                break;


                            case R.id.schedule:

                                i[0] = new Intent(MainActivity.this, Schedule.class);
                                startActivity(i[0]);
                                break;

                            case R.id.sponsors:

                                i[0] = new Intent(MainActivity.this, Sponsors.class);
                                startActivity(i[0]);
                                break;


                            case R.id.faqs:

                                i[0] = new Intent(MainActivity.this, FAQs.class);
                                startActivity(i[0]);
                                break;


                            case R.id.about_us:

                                i[0] = new Intent(MainActivity.this, AboutUs.class);
                                startActivity(i[0]);
                                break;


                            case R.id.team:

                                i[0] = new Intent(MainActivity.this, Team.class);
                                startActivity(i[0]);
                                break;

                            case R.id.developer:
                                i[0] = new Intent(MainActivity.this, Developers.class);
                                startActivity(i[0]);
                                break;

                        }
                    }

                }, 260);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

        }
        });

        //Coloring each dashboard item with custom solid colors
        GradientDrawable dashshape1 = (GradientDrawable) dashimg1.getBackground().getCurrent();
        dashshape1.setColor(getResources().getColor(R.color.dash1));

        GradientDrawable dashshape2 = (GradientDrawable) dashimg2.getBackground().getCurrent();
        dashshape2.setColor(getResources().getColor(R.color.dash2));

        GradientDrawable dashshape3 = (GradientDrawable) dashimg3.getBackground().getCurrent();
        dashshape3.setColor(getResources().getColor(R.color.dash3));

        GradientDrawable dashshape4 = (GradientDrawable) dashimg4.getBackground().getCurrent();
        dashshape4.setColor(getResources().getColor(R.color.dash4));

        GradientDrawable dashshape5 = (GradientDrawable) dashimg5.getBackground().getCurrent();
        dashshape5.setColor(getResources().getColor(R.color.dash5));

        GradientDrawable dashshape6 = (GradientDrawable) dashimg6.getBackground().getCurrent();
        dashshape6.setColor(getResources().getColor(R.color.dash6));

        GradientDrawable dashshape7 = (GradientDrawable) dashimg7.getBackground().getCurrent();
        dashshape7.setColor(getResources().getColor(R.color.dash7));

        GradientDrawable dashshape8 = (GradientDrawable) dashimg8.getBackground().getCurrent();
        dashshape8.setColor(getResources().getColor(R.color.dash8));

        //Coloring each dashboard item with custom gradient colors
        /*GradientDrawable dashshape2 = (GradientDrawable)dashimg2.getBackground().getCurrent();
        dashshape2.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        dashshape2.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        dashshape2.setColors(EventCardColor);*/


        dashimg1.setImageDrawable(getResources().getDrawable(R.drawable.reg_now));
        dashimg2.setImageDrawable(getResources().getDrawable(R.drawable.map));
        dashimg3.setImageDrawable(getResources().getDrawable(R.drawable.schedule));
        dashimg4.setImageDrawable(getResources().getDrawable(R.drawable.instruction));
        dashimg5.setImageDrawable(getResources().getDrawable(R.drawable.sponsor));
        dashimg6.setImageDrawable(getResources().getDrawable(R.drawable.faq));
        dashimg7.setImageDrawable(getResources().getDrawable(R.drawable.about_us));
        dashimg8.setImageDrawable(getResources().getDrawable(R.drawable.team_5));

        dashText1.setText(R.string.register_now);
        dashText2.setText(R.string.map);
        dashText3.setText(R.string.schedule);
        dashText4.setText(R.string.instructions);
        dashText5.setText(R.string.sponsors);
        dashText6.setText(R.string.faqs);
        dashText7.setText(R.string.about_us);
        dashText8.setText(R.string.team);


        registerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),RegisterNow.class);
                startActivity(intent1);
            }
        });

        scheduleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),Schedule.class);
                startActivity(intent1);
            }
        });

        dashitem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterNow.class);
                startActivity(intent);
            }
        });

        dashitem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CampusMap.class);
                startActivity(intent);
            }
        });

        dashitem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Schedule.class);
                startActivity(intent);
            }
        });

        dashitem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Instructions.class);
                startActivity(intent);
            }
        });

        dashitem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Sponsors.class);
                startActivity(intent);
            }
        });

        dashitem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FAQs.class);
                startActivity(intent);
            }
        });

        dashitem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(intent);
            }
        });

        dashitem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Team.class);
                startActivity(intent);
            }
        });


        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);


    }

    private void AutoScroll(){
        handlerhs = new Handler();
        runnablehs = new Runnable() {
            @Override
            public void run() {
                int value = timerCard.getMeasuredWidth() + 22;

                animator1 = ObjectAnimator.ofInt(mhorizontalScrollView, "scrollX", value);
                animator1.setStartDelay(2000);
                animator1.setDuration(750);
                animator1.start();


                value= value + registerCard.getMeasuredWidth()+22;


                animator2 = ObjectAnimator.ofInt(mhorizontalScrollView, "scrollX", value);
                animator2.setStartDelay(6500);
                animator2.setDuration(750);
                animator2.start();

                animator3 = ObjectAnimator.ofInt(mhorizontalScrollView, "scrollX", 0);
                animator3.setStartDelay(10000);
                animator3.setDuration(750);
                animator3.start();
            }

        };

        mhorizontalScrollView.postDelayed(runnablehs, 1500);


    }

    int flag = 0;

    private void countDownStart() {

        handler = new Handler();
        runnable = new Runnable() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date futureDate = dateFormat.parse("2019-09-21");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {

                        long diff = (futureDate.getTime() - currentDate.getTime());

                        diff = diff + 9*60*60*1000 + 30*60*1000;  //if Glitz starts at 09:30am


                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;

                        daystv.setText("" + String.format("%02d", days));
                        hourstv.setText("" + String.format("%02d", hours));
                        minutestv.setText("" + String.format("%02d", minutes));
                        secondstv.setText("" + String.format("%02d", seconds));

                        Animation rotation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotation);
                        ticktock.startAnimation(rotation);
                        flag=1;
                        reg2.setText(R.string.register_now);

                    }
                    else{
                        flag = 0;
                        LinearLayout counter = findViewById(R.id.countdown_timer);
                        counter.setVisibility(View.INVISIBLE);
                        TextView textView = findViewById(R.id.text1);
                        textView.setText(R.string.timeup);
                        ticktock.setVisibility(View.INVISIBLE);
                        reg1.setText(R.string.not_reg_timeup);
                        reg2.setText(R.string.reg_now_timeup);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
            this.doubleBacktoExit = false;
        }
        else {
            if (doubleBacktoExit) {
                super.onBackPressed();
                return;
            }
            this.doubleBacktoExit = true;
            Snackbar snackbar = Snackbar
                    .make(rootLayout,"Press BACK again to exit application", Snackbar.LENGTH_SHORT);

            snackbar.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBacktoExit = false;
                }
            }, 2000);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        navigationView.setCheckedItem(R.id.home);
    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean ranBefore = preferences.getBoolean("RanBefore",false);
        Log.i(TAG,""+ranBefore);
        if (!ranBefore) {
            Log.i(TAG,"Inside if Condition : "+ranBefore);

            topLevelLayout.setVisibility(View.VISIBLE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();

            Log.i(TAG,"AfterChanging : "+ranBefore);

            topLevelLayout.setOnTouchListener(new View.OnTouchListener(){

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    topLevelLayout.setVisibility(View.INVISIBLE);
                    return false;
                }

            });

        }
        return ranBefore;

    }


}
