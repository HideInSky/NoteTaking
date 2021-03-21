package com.example.notetaking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle, noteDetails;

    // get the current time as user add a new note
    Calendar c;
    String todaysDate;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //enable back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //note title & note details
        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);

        // in the new note, set new note as the title of toolbar
        getSupportActionBar().setTitle("New Note");

        // make note Title become the title of toolbar
        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()!=0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // get current date and time
        c = Calendar.getInstance();
        // c.get(Calendar.YEAR)
        todaysDate = c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH);
        // To get hour number from 0 to 10, add pad
        currentTime = pad(c.get(Calendar.HOUR)) +":"+ pad(c.get(Calendar.MINUTE));

        //Log
        Log.d("现在时间", "日期：" + todaysDate + " 时间： " + currentTime);

    }

    private String pad(int i) {
        if (i<10)
            return "0" + i;
        else
            return String.valueOf(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                Note note = new Note(noteTitle.getText().toString(),
                        noteDetails.getText().toString(),
                        todaysDate, currentTime);
                NoteDatabase db = new NoteDatabase(this);
                db.addNote(note);
                Toast.makeText(this, "This note is saved", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
            case R.id.delete:
                Toast.makeText(this, "This note is deleted", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }
}