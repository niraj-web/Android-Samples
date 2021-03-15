package com.example.resizeimage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    ImageView ivImage;
    public static final int PICK_IMAGE = 1;
    Bitmap yourBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivImage = findViewById(R.id.ivImage);
    }
    public void uploadImage(View view)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }
    public void resizeImage(View view)
    {
        Bitmap resized = Bitmap.createScaledBitmap(yourBitmap, 400, 400, true);
        ivImage.setImageBitmap(resized);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode , data);
        if(requestCode==PICK_IMAGE && resultCode == Activity.RESULT_OK)
        {
            if (data == null)
            {
                return;
            }
            try
            {
                Uri imageUri = data.getData();
                yourBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                ivImage.setImageBitmap(yourBitmap);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }
}