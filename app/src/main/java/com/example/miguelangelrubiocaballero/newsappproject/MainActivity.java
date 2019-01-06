package com.example.miguelangelrubiocaballero.newsappproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import static com.example.miguelangelrubiocaballero.newsappproject.ListNewsActivity.KEY_URL;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NewsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_news);
            //ListNewsActivity();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_news:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        //new NewsFragment()).commit();
                listNewsActivity();

                break;
            case R.id.nav_chat:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       // new NewsPapersFragment()).commit();
                listNewsPaper();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_send:
                //Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                sendFeedback();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void listNewsActivity(){

        Intent intent = new Intent(this, ListNewsActivity.class);
        startActivity(intent);
    }

    public void listNewsPaper(){

        Intent intent = new Intent(this, NewsPaper.class);
        startActivity(intent);
    }


    public void sendFeedback(){

        Intent intent = new Intent(this, SendFeedback.class);
        startActivity(intent);
    }



}