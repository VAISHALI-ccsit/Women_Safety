package com.example.womansafety;

import static java.sql.DriverManager.println;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //for navigation drawer (1)
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    //For bottom navigation and fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //for navigation drawer (2)
        drawerLayout = findViewById(R.id.draw_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar_bar);

        navigationView.setItemIconTintList(null); //add this to show original color of menu icons in navigation drawer.

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //click listener on navigation drawer on menu items
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();


                if(id == R.id.profile){
                    Toast.makeText(MainActivity.this, "Clicked Profile", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.website){
                    Intent intent = new Intent(MainActivity.this, GovernmentHelp.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Clicked website", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.self_defence){
                    Intent intent = new Intent(MainActivity.this, SelfDefence.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Clicked self-defence", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.add_contact){
                    Intent intent = new Intent(getApplicationContext(), AccessContact.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });


        //set default home fragment at dashboard
        loadHomeFragmnet(new Home_Fragment());

        //for bottom navigation view
        bottomNavigationView=findViewById(R.id.bottom_navi);

        bottomNavigationView.setItemIconTintList(null); //add this to show original color of menu icons in bottom navigation.

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId()==R.id.home){

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Home_Fragment home_fragment = new Home_Fragment();
                    fragmentTransaction.replace(R.id.fragment, home_fragment).commit();


                    return true;
                }
                if(item.getItemId()==R.id.health){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Medical_Fragment medical_fragment = new Medical_Fragment();
                    fragmentTransaction.replace(R.id.fragment, medical_fragment).commit();


                    return true;
                }
                if(item.getItemId()==R.id.police){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Police_Fragment police_fragment = new Police_Fragment();
                    fragmentTransaction.replace(R.id.fragment, police_fragment).commit();

                    return true;
                }
                if(item.getItemId()==R.id.map){
                    sheetNavigation();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Map_Fragment map_fragment = new Map_Fragment();
                    fragmentTransaction.replace(R.id.fragment, map_fragment).commit();


                    return true;
                }
                if(item.getItemId()==R.id.guidelines){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Guidelines_Fragment guidelines_fragment = new Guidelines_Fragment();
                    fragmentTransaction.replace(R.id.fragment, guidelines_fragment).commit();

                    return true;
                }
                return false;
            }
        });

        //onBackButtonPressed();

    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_contact, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id1 = item.getItemId();
        if(id1 == R.id.add_contact){
            Intent intent = new Intent(MainActivity.this, AccessContact.class);
            startActivity(intent);
        }if(id1 == R.id.logout){
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadHomeFragmnet(Home_Fragment home_fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, home_fragment).commit();
    }



    private void sheetNavigation(){

        showDialog();
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//                startActivity(intent);
//            }
//        },800);
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_in_map);



        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    //Home fragment work
    public void medical_button(View view) {
        Toast.makeText(this, "Medical button clicked", Toast.LENGTH_SHORT).show();


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Medical_Fragment medical_fragment = new Medical_Fragment();
        fragmentTransaction.replace(R.id.fragment, medical_fragment).commit();

    }

    public void police_button(View view) {
        Toast.makeText(this, "Police button clicked", Toast.LENGTH_SHORT).show();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Police_Fragment police_fragment = new Police_Fragment();
        fragmentTransaction.replace(R.id.fragment, police_fragment).commit();
    }

    public void guidelines_button(View view) {
        Toast.makeText(this, "Guidelines button clicked", Toast.LENGTH_SHORT).show();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Guidelines_Fragment guidelines_fragment = new Guidelines_Fragment();
        fragmentTransaction.replace(R.id.fragment, guidelines_fragment).commit();

    }

    public void map_button(View view) {
        Toast.makeText(this, "map button clicked", Toast.LENGTH_SHORT).show();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Map_Fragment map_fragment = new Map_Fragment();
        fragmentTransaction.replace(R.id.fragment, map_fragment).commit();
    }

    public void Medical_help_button_in_fragment(View view) {


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}