package com.nomadlab.mymove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nomadlab.mymove.repository.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MoviesFragment moviesFragment;
    MyInfoFragment myInfoFragment;
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        addMoviesFragment();
        addEventListener();
    }


    private void addMoviesFragment() {
        if (moviesFragment == null) {
            moviesFragment = MoviesFragment.newInstance();
        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainContainer, moviesFragment);
        transaction.commit();
    }

    private void addMyInfoFragment() {
        if (myInfoFragment == null) {
            myInfoFragment = MyInfoFragment.newInstance();
        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainContainer, myInfoFragment);
        transaction.commit();
    }

    private void initData() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void addEventListener() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        addMoviesFragment();
                        break;
                    case R.id.page_2:
                        addMyInfoFragment();
                        break;
                }
                return true;
            }
        });
    }


}