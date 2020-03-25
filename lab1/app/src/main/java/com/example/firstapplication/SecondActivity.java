package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.extr_Msg);
        TextView textView = findViewById(R.id.textView);
        final Button btn = (Button)findViewById(R.id.button2);
        textView.setText(msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
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

    public void createDialog(View view){
        MyDialog mdg = new MyDialog();
        mdg.setActivitiy(this);
        mdg.setContext(getApplicationContext());
        mdg.onCreateDialog(null).show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Are you good?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG).show();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG).show();
//                    }
//                });
//        builder.create().show();
//        AlertDialog alt = new AlertDialog.Builder(this).create();
//        alt.setTitle("Title");
//        alt.setMessage("This is my app!");
//        alt.setButton("Yes..", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG).show();
//            }
//        });
//        alt.setButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG).show();
//            }
//        });
//        alt.show();
    }
}
