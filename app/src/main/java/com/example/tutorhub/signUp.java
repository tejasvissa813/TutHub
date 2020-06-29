package com.example.tutorhub;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class signUp extends AppCompatActivity {
    EditText pass2;
    EditText pass1;
    EditText user_name;
    EditText email;
    RadioGroup rg;
    public static PasswordStorage pshelper;
    public static SQLiteDatabase pdb;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp.context = getApplicationContext();
        String[] files = context.fileList();
        for (String file : files) {
            System.out.println(file);
        }
        pdb = null;
        pass2 = (EditText) findViewById(R.id.editTextTextPassword2);
        pass1 = (EditText) findViewById(R.id.editTextTextPassword);
        user_name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress2);
        rg = findViewById(R.id.radioGroup);
    }
    public static Context getAppContext(){
        return signUp.context;
    }
    public void hitPasswordOne(View v){
        pass1.setText("");
        pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        pass1.setTextColor(Color.parseColor("black"));
    }
    public void hitPasswordTwo(View v){
        pass2.setText("");
        pass2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        pass2.setTextColor(Color.parseColor("black"));
    }
    public void hitEmail(View v){
        email.setText("");
    }
    public void hitName(View v){
        user_name.setText("");
    }

    public void advance(View v){
        String name = user_name.getText().toString();
        String password1 = pass1.getText().toString();
        String password2 = pass2.getText().toString();
        String address = email.getText().toString();
        int selid = rg.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selid);
        String type = radioButton.getText().toString();
        if(password1.equals(password2)) {
            ContentValues values = new ContentValues();
            pshelper = new PasswordStorage(getAppContext());
            PasswordStorage.DATABASE_VERSION++;
            pdb = pshelper.getWritableDatabase();
            getDataBase();
            //DbHelper dbh = new DbHelper(getAppContext());
            values.put(PasswordStorageInfo.FeedEntry.COLUMN_ONE, address);
            values.put(PasswordStorageInfo.FeedEntry.COLUMN_TWO, name);
            values.put(PasswordStorageInfo.FeedEntry.COLUMN_THREE, password1);
            values.put(PasswordStorageInfo.FeedEntry.COLUMN_FOUR, type);
            long newRowId = pdb.insert(PasswordStorageInfo.FeedEntry.TABLE_NAME, null, values);
            readData();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            pass1.setTextColor(Color.parseColor("red"));
            pass2.setTextColor(Color.parseColor("red"));
        }
    }
    public static SQLiteDatabase getDataBase(){
        return pdb;
    }
    public void readData(){
        String selection = PasswordStorageInfo.FeedEntry.COLUMN_ONE + " = ?";
        String[] selectionArgs = { "Password" };
        String sortOrder =
                PasswordStorageInfo.FeedEntry.COLUMN_FOUR + " DESC";
        Cursor cursor = pdb.query(
                PasswordStorageInfo.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        if(cursor.moveToFirst()){
            System.out.println(cursor.getString(cursor.getColumnIndex(PasswordStorageInfo.FeedEntry.COLUMN_FOUR)));
            cursor.close();
        }else{
            System.out.println("NO DATA");
        }
    }
}
final class PasswordStorageInfo{
    protected PasswordStorageInfo() {}
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Password";
        public static final String COLUMN_ONE = "EmailAddress";
        public static final String COLUMN_TWO = "Username";
        public static final String COLUMN_THREE = "Password";
        public static final String COLUMN_FOUR = "Type";
    }
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (\n" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY,\n" +
                    FeedEntry.COLUMN_ONE + " TEXT,\n" +
                    FeedEntry.COLUMN_TWO + " TEXT,\n" +
                    FeedEntry.COLUMN_THREE + " TEXT,\n" +
                    FeedEntry.COLUMN_FOUR + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}
