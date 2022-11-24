package com.example.hackbpit;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class CreateNFT extends AppCompatActivity {

    EditText transAdd;
    EditText productName;
    EditText productDesc;
    EditText warrantyPrd;
    EditText serialNo;
    Button scanBar;
    Button imageCap;
    ImageView imageProduct;
    private static final int Image_Capture_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_nft);

        transAdd = findViewById(R.id.transferAdd);
        productName = findViewById(R.id.productName);
        productDesc = findViewById(R.id.productDesc);
        warrantyPrd = findViewById(R.id.warrantyPrd);
        serialNo = findViewById(R.id.serialNo);
        scanBar = findViewById(R.id.scanProduct);
        imageCap = findViewById(R.id.imageCap);
        imageProduct = findViewById(R.id.productImage);

        scanBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanBarCode();
            }
        });

        imageCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCam();
            }
        });

    }

    public void scanBarCode(){
        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(true);
        scanOptions.setCaptureActivity(CaptureActi.class);
        barLaunch.launch(scanOptions);
    }

    ActivityResultLauncher<ScanOptions> barLaunch = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() != null){
            serialNo.setText(result.getContents());
        }
    });

    public void openCam(){
        Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cInt,Image_Capture_Code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imageProduct.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}