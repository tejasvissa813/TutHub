package com.example.tutorhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    public static String state = null;
    protected static String uname;
    protected static String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void signup_switch(View v){
        Intent i = new Intent(this, signUp.class);
        startActivity(i);
    }
    public void student_switch(View v){
        Intent i = new Intent(this, LogInStudent.class);
        state = "STUDENT";
        startActivity(i);
    }
    public void tutor_switch(View v){
        Intent i = new Intent(this, LogInStudent.class);
        state = "TUTOR";
        startActivity(i);
    }
    public void teacher_switch(View v){
        Intent i = new Intent(this, LogInStudent.class);
        state = "TEACHER";
        startActivity(i);
    }
}