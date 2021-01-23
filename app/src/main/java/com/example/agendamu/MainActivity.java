package com.example.agendamu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.agendamu.model.EventModel;
import com.example.agendamu.model.UserModel;
import com.example.agendamu.retrofit.ApiEndpoints;
import com.example.agendamu.retrofit.RetrofitService;
import com.example.agendamu.utilities.ActivityHelper;
import com.example.agendamu.utilities.DummyHelper;
import com.example.agendamu.utilities.LoginHelper;
import com.example.agendamu.utilities.StoreHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // testing date
//        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
//        System.out.println(DateFormat.getTimeInstance().format(new Date()));
        // testing date

        // cara pakai StoreHelper
        System.out.println("TEST PRINT RETROFIT");
        StoreHelper.fetchEvents();
        LoginHelper.login("evalda@agendamu.com","test123123");
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserModel currentUser = LoginHelper.getCurrentUser();
                System.out.println(currentUser);
                // retrofit events
                List<EventModel> events = StoreHelper.getEvents();
                for (EventModel event: events) {
                    System.out.println(event.toString());
                }
            }
        }, 1000);
        // cara pakai StoreHelper

        setContentView(R.layout.activity_main);

        ActivityHelper.setMainActivity(this);

        if (!getInitialLoginStatus()) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    private void doAfterGetLoginStatus() {
        System.out.println(LoginHelper.getCurrentUser());
        setupFragments();
    }

    private boolean getInitialLoginStatus() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            System.out.println("NOT LOGGED IN");
            return false;
        } else {
//            System.out.println("LOGGED IN AS " + mAuth.getCurrentUser().getEmail());
            FirebaseDatabase.getInstance().getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot loggedInUser = snapshot.child(mAuth.getCurrentUser().getUid());
//                    System.out.println(mAuth.getCurrentUser().getUid());
                    UserModel userModel = loggedInUser.getValue(UserModel.class);
                    userModel.setUid(mAuth.getCurrentUser().getUid());
                    userModel.setEmail(mAuth.getCurrentUser().getEmail());
                    LoginHelper.setCurrentUser(userModel);
                    doAfterGetLoginStatus();
//                    System.out.println(LoginHelper.getCurrentUser());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("Sign in cancelled");
                }
            });
            return true;
        }
    }

    public void setupFragments(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBottom);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.scheduleFragment, R.id.profileFragment).build();

//        navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        navController = navHostFragment.getNavController();

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.homeFragment || destination.getId() == R.id.scheduleFragment || destination.getId() == R.id.profileFragment) {
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else {
                bottomNavigationView.setVisibility(View.GONE);
            }
        });

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}