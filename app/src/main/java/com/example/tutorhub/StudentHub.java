package com.example.tutorhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentHub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_hub);
    }
    public void clickedProfile(View v){
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }
}