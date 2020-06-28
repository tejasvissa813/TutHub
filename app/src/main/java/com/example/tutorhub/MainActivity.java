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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void perform_action(View v) {
        TextView tv = findViewById(R.id.teacherLogin);

        tv.setText("Logged In");
    }

    public void signup_switch(View v){
        Intent i = new Intent(this, signUp.class);
        startActivity(i);
    }
}