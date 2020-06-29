package com.example.tutorhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LogInStudent extends AppCompatActivity {
    EditText name;
    EditText pass;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_student);
        name = findViewById(R.id.studentUser);
        pass = findViewById(R.id.editTextTextPassword3);
        error = findViewById(R.id.error);
    }
    public void hitName(View v){
        name.setText("");
    }
    public void hitPass(View v){
        pass.setText("");
        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
    public void advance(View v){
        SQLiteDatabase pdb = signUp.getDataBase();
        String username = name.getText().toString();
        String password = pass.getText().toString();
        if(check(pdb)){
            Cursor cursor = pdb.query(
                    PasswordStorageInfo.FeedEntry.TABLE_NAME,   // The table to query
                    null,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );
            boolean flag = false;
            if(cursor.getPosition()<0)  cursor.moveToNext();
            do{
                String data_type = cursor.getString(cursor.getColumnIndex(PasswordStorageInfo.FeedEntry.COLUMN_FOUR));
                String data_name = cursor.getString(cursor.getColumnIndex(PasswordStorageInfo.FeedEntry.COLUMN_TWO));
                String data_pass = cursor.getString(cursor.getColumnIndex(PasswordStorageInfo.FeedEntry.COLUMN_THREE));
                if(data_type.equals(MainActivity.state) && data_name.equals(username) && data_pass.equals(password)){
                    flag = true;
                    MainActivity.uname = username;
                    MainActivity.email = cursor.getString(cursor.getColumnIndex((PasswordStorageInfo.FeedEntry.COLUMN_ONE)));
                    break;
                }
                cursor.moveToNext();
            }while (!cursor.isLast());
            if(flag){
                Intent i = null;
                if(MainActivity.state.equals("STUDENT")){
                    i = new Intent(this, StudentHub.class);
                }else{
                    i = new Intent(this, TeachingHub.class);
                }
                startActivity(i);
            }
            else {
                error.setVisibility(View.VISIBLE);
            }
        } else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
    public boolean check(SQLiteDatabase pdb){
        if(pdb == null){
            error.setVisibility(View.VISIBLE);
            return false;
        }else return true;
    }
}