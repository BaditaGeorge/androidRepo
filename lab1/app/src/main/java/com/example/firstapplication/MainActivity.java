package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String extr_Msg = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Created!","Instance of the object");
        final ListView lst = (ListView)findViewById(R.id.mine);
        final Button btn = (Button)findViewById(R.id.button);
        final String[] heroes = new String[]{
                "Batman","Superman","Wonder Woman","Aquaman","Martian Manhunter"
        };
//        SharedPreferences tprefs = getSharedPreferences("firstfile",Context.MODE_PRIVATE);
//        if(tprefs != null) {
//            String hero = tprefs.getString("hero", null);
//            heroes[0] = hero;
//        }
        ArrayList<String> heroList = new ArrayList<String>();
        heroList.addAll(Arrays.asList(heroes));
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,heroList);
        lst.setAdapter(listAdapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Your hero is: " + parent.getItemAtPosition(position),Toast.LENGTH_LONG).show();
                btn.setText("Your hero is " + parent.getItemAtPosition(position));
            }
        });
//        for(int i=0;i<5;i++){
//            final int pos = i;
//            lst.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),"Mesaj in toast",Toast.LENGTH_LONG).show();
////                    System.out.println(heroes[pos]);
////                    btn.setText(heroes[pos]);
//                }
//            });
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("On Restore","Instance of the object");
        super.onRestoreInstanceState(savedInstanceState);
        final Button btn = (Button)findViewById(R.id.button);
        btn.setText(savedInstanceState.getString("btn"));
//        textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("On save","Instance of the object");
        final Button btn = (Button)findViewById(R.id.button);
        outState.putString("btn",btn.getText().toString());
//        outState.putString(GAME_STATE_KEY, gameState);
//        outState.putString(TEXT_VIEW_KEY, textView.getText());
//
//        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    public void changeToSecond(){
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(extr_Msg,"Valoare Reala");
        startActivity(intent);
    }

    public void changeToPref(){
        Intent intent = new Intent(this,ThirdActivity.class);
        intent.putExtra("preferences","Preferinte");
        startActivity(intent);
    }

    public void changeToThird(){
        Intent intent = new Intent(this,ActivityForSensor.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.first:
                changeToSecond();
                return true;
            case R.id.second:
                changeToThird();
                return true;
            case R.id.third:
                changeToPref();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clickTest(View view){
//        EditText t1 = (EditText)findViewById(R.id.editText);
//        TextView t2 = (TextView)findViewById(R.id.textView);
//        t2.setText(t1.getText());
    }
}

