package com.example.persistentstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NotePage extends AppCompatActivity {

    TextView textView2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNote) {
            //TODO
            return true;
        }
        else if (item.getItemId() == R.id.logout) {
            Intent logout = new Intent(this, MainActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("persistentstorage", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove(MainActivity.usernameKey).apply();
            startActivity(logout);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);
        SharedPreferences sharedPreferences = getSharedPreferences("persistentstorage", Context.MODE_PRIVATE);

        textView2 = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        textView2.setText("Welcome " + sharedPreferences.getString(MainActivity.usernameKey, "") + "!");
    }
}