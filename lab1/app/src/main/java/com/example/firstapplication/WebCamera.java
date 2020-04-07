package com.example.firstapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class WebCamera extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_camera);
        this.imageView = (ImageView)this.findViewById(R.id.imageView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openOnClick(View v){
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA},100);
        }else{
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent,1888);
        }
    }

    public void SaveImage(View view){
        FileOutputStream fs;
        try{
            fs = openFileOutput("img.bmp", Context.MODE_PRIVATE);
//            fs.write(getPreferences(Context.MODE_PRIVATE).getString("hero",null).getBytes());
            this.photo.compress(Bitmap.CompressFormat.PNG,100,fs);
            Toast.makeText(getApplicationContext(),"Image saved and loaded",Toast.LENGTH_LONG).show();
            imageView.setImageBitmap(LoadImage(null));
            fs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap LoadImage(View view){
        FileInputStream fs;
        Bitmap img = null;
        try{
            fs = openFileInput("img.bmp");
            img = BitmapFactory.decodeStream(fs);
        }catch(Exception e){
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"camera permission granted",Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,1888);
            }else{
                Toast.makeText(getApplicationContext(),"camera permission denied",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int reqCode,int resCode,Intent data){
        if(reqCode == 1888 && resCode == Activity.RESULT_OK){
            this.photo = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(this.photo);
        }
    }
}
