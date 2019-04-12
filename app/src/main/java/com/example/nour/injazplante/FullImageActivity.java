package com.example.nour.injazplante;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FullImageActivity extends AppCompatActivity {


    ImageView fullImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        fullImage = (ImageView) findViewById(R.id.full_Image);
    /*    if(getIntent().hasExtra("img")){
            Bitmap bitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("img"),0,getIntent().getByteArrayExtra("img").length);
            fullImage.setImageBitmap(bitmap);
        }
      /*  String data = getIntent().getExtras().getString("img");
        fullImage.setImageDrawable(Uri.parse(data));*/
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            fullImage.setImageResource(bundle.getInt("img"));
        }
    }
}
