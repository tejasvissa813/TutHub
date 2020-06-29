package com.example.tutorhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TeachingHub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_hub);
    }
    public void transition(View v){
        Intent i = new Intent(this, TeacherChat.class);
        startActivity(i);
    }
    public void clickedProfile(View v){
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }
}