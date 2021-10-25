package com.example.persistentstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {
    int noteid = -1;

    public void saveMethod(View view) {
        EditText editText = (EditText) findViewById(R.id.note);
        String content = editText.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("persistentstorage", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(MainActivity.usernameKey, "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (NotePage.notes.size() + 1);
            dbHelper.saveNotes(title, date, content, username);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        Intent intent = new Intent(this, NotePage.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        EditText editText = (EditText) findViewById(R.id.note);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);

        if (noteid != -1) {
            Note note = NotePage.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }
}