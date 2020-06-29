package com.example.tutorhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;

public class TeacherChat extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_chat);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void goPressed(View v){
        String choice = spinner.getTransitionName();
        ScrollView requests = findViewById(R.id.requestScroll);
        ScrollView chat = findViewById(R.id.chatScroll);
        if(choice.equals("View Requests")){
            chat.setVisibility(View.INVISIBLE);
            requests.setVisibility(View.VISIBLE);
        }
        if(choice.equals("View Requests")){
            requests.setVisibility(View.INVISIBLE);
            chat.setVisibility(View.VISIBLE);
        }
    }
}