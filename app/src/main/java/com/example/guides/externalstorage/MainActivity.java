package com.example.guides.externalstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText EDTMessage,Filename;
    Button BTN1,BTN2,BTN3,BTN4,BTN5,BTNSP,BTNIS;
    SharedPreferences preferences;
    FileOutputStream fos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EDTMessage = (EditText) findViewById(R.id.EDT1);
        Filename = (EditText) findViewById(R.id.EDT2);
        BTN1 = (Button) findViewById(R.id.BTN1);
        BTN2 = (Button) findViewById(R.id.BTN2);
        BTN3 = (Button) findViewById(R.id.BTN3);
        BTN4 = (Button) findViewById(R.id.BTN4);
        BTN5 = (Button) findViewById(R.id.BTN5);
        BTNSP = (Button) findViewById(R.id.BTNSP);
        BTNIS = (Button) findViewById(R.id.BTNIS);
        preferences = getSharedPreferences("myPref",MODE_WORLD_READABLE);
    }

    public void savePreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Filename.getText().toString(), EDTMessage.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_LONG).show();
    }
    public void saveInternalStorage (View view) {
        String message = EDTMessage.getText().toString();

        try{
            fos = openFileOutput(Filename.getText().toString(), Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_LONG).show();

    }

    public void saveInternalCache(View view){
        File folder = getCacheDir();
        File file = new File(folder, Filename.getText().toString());
        String message = EDTMessage.getText().toString();
        FileOutputStream fos = null;
        try {
            fos=new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Saved in Internal Cache!", Toast.LENGTH_LONG).show();
    }
    public void saveExternalCache(View view){
        File folder = getExternalCacheDir();
        File file = new File(folder, Filename.getText().toString());
        String message = EDTMessage.getText().toString();
        writeData(file, message);

        Toast.makeText(this, "Saved in External Cache!", Toast.LENGTH_LONG).show();
    }
    public void saveExternalStorage(View view){
        File folder = getExternalFilesDir("Temp");
        File file =  new File(folder, Filename.getText().toString());
        String message = EDTMessage.getText().toString();

        writeData(file, message);

        Toast.makeText(this, "Saved in External Storage!", Toast.LENGTH_LONG).show();
    }

    public void saveExternalPublic(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file =  new File(folder, Filename.getText().toString());
        String message = EDTMessage.getText().toString();

        writeData(file, message);

        Toast.makeText(this, "Saved in External Public Storage!", Toast.LENGTH_LONG).show();
    }
    public void Next(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }


    public void writeData(File file, String message){
        FileOutputStream fos = null;
        try {
            fos=new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
