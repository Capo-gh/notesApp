package com.donsmart.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText etTitle,etNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etTitle = findViewById(R.id.etTitle);
        etNotes = findViewById(R.id.etNotes);


        Intent intent = getIntent();

        String t = intent.getStringExtra("edit_title");
        String n = intent.getStringExtra("edit_note");

        etTitle.setText(t);
        etNotes.setText(n);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.note_icon);
        actionBar.setTitle(" Note");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main2,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.saveNote)
        {
            Intent intent = new Intent();
            intent.putExtra("title", etTitle.getText().toString());
            intent.putExtra("note", etNotes.getText().toString());
            setResult(RESULT_OK, intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

     @Override
    public void onBackPressed() {

         Intent intent = new Intent();
         intent.putExtra("title", etTitle.getText().toString());
         intent.putExtra("note", etNotes.getText().toString());
         setResult(RESULT_OK, intent);
         finish();


         super.onBackPressed();
    }

}