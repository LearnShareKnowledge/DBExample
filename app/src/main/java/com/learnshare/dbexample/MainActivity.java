package com.learnshare.dbexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button btnCreateDatabase ;

    private MySqliteOpenHelper mySqliteOpenHelper ;

    private SQLiteDatabase mDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnCreateDatabase = (Button) findViewById(R.id.btnCreateDatabase);

        btnCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create the database with table here --

                mySqliteOpenHelper = new MySqliteOpenHelper(getApplicationContext());

                mDatabase = mySqliteOpenHelper.getReadableDatabase();

                Cursor cursor = mDatabase.rawQuery("select * from employee ;", null);

                System.out.println("MainActivity.onClick:"+ cursor.getColumnCount());


            }
        });
    }
}
