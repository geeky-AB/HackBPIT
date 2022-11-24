package com.example.hackbpit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView generateNFT;
    ImageView imageNft;
    ImageView imageMalhaar;
    ImageView bgblank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         generateNFT = findViewById(R.id.generatingNFT);
         imageNft = findViewById(R.id.nftLogo);
         imageMalhaar = findViewById(R.id.malhaarLogo);
         bgblank = findViewById(R.id.background);

        bgblank.animate().translationY(-3000).setDuration(1000).setStartDelay(2000);
        imageMalhaar.animate().translationY(3000).setDuration(1000).setStartDelay(2000);
        imageNft.animate().translationY(3000).setDuration(1000).setStartDelay(2000);
        generateNFT.animate().translationY(3000).setDuration(1000).setStartDelay(2000);



        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                openActivity();


            }
        };
        handler.postDelayed(r,2900);

    }



    public void openActivity(){
        Intent intent = new Intent(MainActivity.this, HomePage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}