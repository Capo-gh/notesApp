package com.donsmart.notesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RowThingsAdapter.ItemClicked {

    public static final int myRequestCode = 1;
    String return_title;
    String return_note;
    String edit_title;
    String edit_note;

    ArrayList<RowThings> things;

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.noteList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        things = new ArrayList<RowThings>();



        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" All notes");
        actionBar.setIcon(R.drawable.note_icon);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

      if (item.getItemId() == R.id.addNote)
      {
          Intent intent = new Intent(MainActivity.this, com.donsmart.notesapp.MainActivity2.class);
          startActivityForResult(intent,myRequestCode);
      }

       return super.onOptionsItemSelected(item);
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == myRequestCode)
        {
            if (resultCode == RESULT_OK)
            {

                return_title = data.getStringExtra("title");
                return_note = data.getStringExtra("note");


                if (return_title.isEmpty() && return_note.isEmpty())
                {
                    Toast.makeText(this, "No new note added", Toast.LENGTH_SHORT).show();
                }
                else if (return_title.isEmpty() && !return_note.isEmpty())
                {
                    Toast.makeText(this, "No title set to note", Toast.LENGTH_SHORT).show();
                    things.add(new RowThings("No title",return_note));

                }
                else
                {
                    Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();

                    things.add(new RowThings(return_title,return_note));

                }


            }
        }
       myAdapter = new RowThingsAdapter(this,things);

       recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onItemClicked(int index) {

        edit_title = things.get(index).getTitle();
        edit_note = things.get(index).getNote();
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        intent.putExtra("edit_title",edit_title);
        intent.putExtra("edit_note",edit_note);
        startActivity(intent);
        finish();

    }
}