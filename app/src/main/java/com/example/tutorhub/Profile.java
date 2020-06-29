package com.example.tutorhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Profile extends AppCompatActivity {
    EditText emailbox;
    EditText userbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        emailbox = findViewById(R.id.profileEmail);
        userbox = findViewById(R.id.profileUsername);
        emailbox.setText(MainActivity.email);
        userbox.setText(MainActivity.uname);
    }
    public void saveChanges(View v){
        MainActivity.uname = userbox.getText().toString();
        MainActivity.email = emailbox.getText().toString();
    }
}