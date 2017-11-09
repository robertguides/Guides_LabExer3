package com.example.guides.externalstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TextView tv_display;
    EditText file;
    SharedPreferences preferences;
    FileInputStream fis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_display = (TextView) findViewById(R.id.TV);
        file = (EditText) findViewById(R.id.EDT5);
        preferences = getSharedPreferences("myPref",MODE_WORLD_READABLE);
    }
    public void displayPreferences(View view){
        String username = preferences.getString(file.getText().toString(), "");

        tv_display.setText( username );
    }
    public void getInternalStorage (View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = openFileInput(file.getText().toString());
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_display.setText(buffer.toString());
    }
    public void getInternalCache(View view){
        StringBuffer buffer = new StringBuffer();
        File dir = getCacheDir();
        int read = 0;
        try {
            FileInputStream fis = new FileInputStream(new File(getCacheDir(), file.getText().toString()));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_display.setText(buffer.toString());
    }
    public void getExternalCache(View view){
        StringBuffer buffer = new StringBuffer();
        File dir = getExternalCacheDir();
        int read = 0;
        try {
            FileInputStream fis = new FileInputStream(new File(getExternalCacheDir(), file.getText().toString()));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_display.setText(buffer.toString());
    }
    public void getExternalStorage(View view){
        StringBuffer buffer = new StringBuffer();
        File dir = getExternalFilesDir("Temp");
        int read = 0;
        try {
            FileInputStream fis = new FileInputStream(new File(getExternalFilesDir("Temp"), file.getText().toString()));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_display.setText(buffer.toString());
    }
    public void getPublicExternal(View view){
        StringBuffer buffer = new StringBuffer();
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        int read = 0;
        try {
            FileInputStream fis = new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "d4.txt"));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_display.setText(buffer.toString());
    }
    public void Back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
