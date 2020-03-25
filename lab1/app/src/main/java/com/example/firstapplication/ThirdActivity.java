package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThirdActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    MyDialog mdg;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_third);

        Intent intent2 = getIntent();
        String msg2 = intent2.getStringExtra("preferences");
        TextView textView2 = findViewById(R.id.textViewPref);
        textView2.setText(msg2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater2 = getMenuInflater();
        inflater2.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.first:

                return true;
            case R.id.second:

                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public void createDialogPref(View view){
        mdg = new MyDialog();
        mdg.setActivitiy(this);
        mdg.setContext(getApplicationContext());
        mdg.onCreateDialog(null).show();

    }

    public void setPref(String data){
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("hero",data);
        edit.commit();
    }

    //aici setez preferintele
    public void checkForPrefs(View view){
        setPref(mdg.opt);
        SharedPreferences tprefs = getPreferences(Context.MODE_PRIVATE);
        String str = tprefs.getString("hero",null);
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }

    public void SaveFileToInternalStorage(View view){
        FileOutputStream fs;
        try{
            fs = openFileOutput("intern.txt",Context.MODE_PRIVATE);
            fs.write(getPreferences(Context.MODE_PRIVATE).getString("hero",null).getBytes());
            fs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void RaadFromFile(View view){
        FileInputStream fs;
        try{
            fs = openFileInput("intern.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String fin = "";
            String line;
            while((line = br.readLine()) != null){
                fin += line;
            }
            Toast.makeText(getApplicationContext(),fin,Toast.LENGTH_LONG).show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
