package com.example.cameraandgallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int CAMERA_REQ_CODE = 1000;
    private final int GALLERY_REQ_CODE = 1;
    ImageView imageView1;
    Button btnCamera,btnGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView1 = findViewById(R.id.image1);
        btnCamera = findViewById(R.id.bt_Camera);
        btnGallery = findViewById(R.id.bt_Gallery);

        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera,CAMERA_REQ_CODE);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == CAMERA_REQ_CODE){

                Bitmap img = (Bitmap)(data.getExtras().get("data"));
                imageView1.setImageBitmap(img);

        }

        if(resultCode == RESULT_OK && requestCode == GALLERY_REQ_CODE){
            imageView1.setImageURI(data.getData());

        }

    }






}