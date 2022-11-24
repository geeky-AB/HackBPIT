package com.example.hackbpit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView navigationDrawer;
    TextView landingText;
    TextView landingTextNft;
    TextView landingHead;
    Button createNFT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.navDrawer);
        navigationView = findViewById(R.id.navView);
        navigationDrawer = findViewById(R.id.navigationMenuImg);
        landingText = findViewById(R.id.landingTxt);
        landingTextNft =findViewById(R.id.landingTxtNft);
        landingHead = findViewById(R.id.landingHead);
        createNFT = findViewById(R.id.buttonCreateNFT);

        Typeface typeface = getResources().getFont(R.font.myfont);
        landingText.setTypeface(typeface);
        landingTextNft.setTypeface(typeface);
        landingHead.setTypeface(typeface);

        createNFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, CreateNFT.class);
                startActivity(intent);
            }
        });

        navigationDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationDrawerMenu();

    }

    @Override
    public void onBackPressed() {
        HomePage.super.onBackPressed();

    }

    private void navigationDrawerMenu() {
        navigationView.bringToFront();
        Log.d("navigate", "navigationDrawerMenu: opened");
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:{
                break;
            }
            case R.id.nav_nft:{
                createNft();
                break;
            }
            case R.id.nav_owner:{
                claimOwner();
                break;
            }
            case R.id.nav_dev:{
                aboutTeam();
                break;
            }


            default: break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void claimOwner(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.claim_ownership,null);
        builder.setView(view);
        EditText token = view.findViewById(R.id.tokenNumber);
        TextView result = view.findViewById(R.id.result);
        Button submit = view.findViewById(R.id.submit);
        result.setTextSize(20);
        AlertDialog dialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tokenNo = token.getText().toString();
                if (tokenNo.equals("1")){
                    result.setText("You are the owner");
                }
                else result.setText("You are not the owner");
            }
        });

        dialog.show();

    }

    public void createNft() {
//        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePage.this, CreateNFT.class);
        startActivity(intent);
    }

    public void aboutTeam(){
//        Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.about_team,null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}