package com.example.persistentstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NotePage extends AppCompatActivity {

    private TextView textView2;
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNote) {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
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
        String username = sharedPreferences.getString(MainActivity.usernameKey, "");

        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText("Welcome " + username + "!");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(username);

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes) {
            displayNotes.add(String.format("Title: %s\nDate:%s", note.getTitle(), note.getDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("noteid", i);
                startActivity(intent);
            }
        });
    }
}