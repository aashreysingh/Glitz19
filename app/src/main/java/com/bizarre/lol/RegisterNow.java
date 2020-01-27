package com.bizarre.lol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class RegisterNow extends AppCompatActivity {

    private static final String TAG = "Main";

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView title;
    ImageView close;

    ProgressBar pgTop;
    FrameLayout progressLayout;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_now);

        navigationView = findViewById(R.id.nav_drawer_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.glitz_toolbar);
        title = findViewById(R.id.actionbar_title);

        close = findViewById(R.id.close);

        pgTop = findViewById(R.id.progressBar2);
        webView = findViewById(R.id.webView);
        progressLayout = findViewById(R.id.progress_layout);

        pgTop.setMax(100);

        title.setText(R.string.register_now);

        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.reg_now);
        final Intent[] i = new Intent[1];

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                switch (menuItem.getItemId()){

                    case R.id.home:
                        i[0] = new Intent(RegisterNow.this,MainActivity.class);
                        startActivity(i[0]);
                        break;

                    case R.id.reg_now:
                        break;

                    case R.id.instructions:

                        i[0] = new Intent(RegisterNow.this,Instructions.class);
                        startActivity(i[0]);
                        break;


                    case R.id.schedule:

                        i[0] = new Intent(RegisterNow.this,Schedule.class);
                        startActivity(i[0]);
                        break;


                    case R.id.sponsors:

                        i[0] = new Intent(RegisterNow.this,Sponsors.class);
                        startActivity(i[0]);
                        break;

                    case R.id.faqs:

                        i[0] = new Intent(RegisterNow.this,FAQs.class);
                        startActivity(i[0]);
                        break;

                    case R.id.about_us:

                        i[0] = new Intent(RegisterNow.this,AboutUs.class);
                        startActivity(i[0]);
                        break;

                    case R.id.team:

                        i[0] = new Intent(RegisterNow.this,Team.class);
                        startActivity(i[0]);
                        break;

                    case R.id.developer:

                        i[0] = new Intent(RegisterNow.this,Developers.class);
                        startActivity(i[0]);
                        break;

                }
                    }

                }, 250);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterNow.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i(TAG, "Processing webview url click..."+url);
                super.onPageStarted(view, url, favicon);
                pgTop.setVisibility(View.VISIBLE);

            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                pgTop.setVisibility(View.INVISIBLE);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(RegisterNow.this, "Connectivity Problem\nTry again later!!!\n" + description, Toast.LENGTH_LONG).show();
                alertDialog.setTitle("Connectivity Problem");
                alertDialog.setMessage("\nMake sure that you have the access to the internet!!!\n\n"+description);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress){
                pgTop.setVisibility(View.VISIBLE);
                pgTop.setProgress(progress);

                super.onProgressChanged(view, progress);
            }


        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.setVerticalScrollBarEnabled(false);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setUserAgentString("Android");
        webView.loadUrl(getString(R.string.url));
        pgTop.setProgress(0);

        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);

    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        navigationView.setCheckedItem(R.id.reg_now);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
