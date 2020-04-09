package com.nstg.cameraintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageShow extends AppCompatActivity {

    public static ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        Toast.makeText(ImageShow.this, intent.getStringExtra("caption"), Toast.LENGTH_SHORT).show();

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Drawable drawable1 = new BitmapDrawable(bmp);
        imageView.setBackgroundDrawable(drawable1);
    }
}