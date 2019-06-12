package com.example.notes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.lang.ref.WeakReference;


//this will demonstrate the working of insert, update and delete functionalities in a room database
public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.notes.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.notes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.notes.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY=
            "com.example.notes.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)) {
             setTitle("Edit Nodte");
             editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
             editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
             numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this,"Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!= -1) {
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
