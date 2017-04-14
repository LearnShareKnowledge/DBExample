package com.learnshare.dbexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button  btnCreateDatabase,btnInsertData, btnUpdateData;

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

        btnInsertData = (Button) findViewById(R.id.btnInsertData);

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        btnUpdateData = (Button) findViewById(R.id.btnUpdateData);

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(1);
            }
        });




    }

    private void updateData(int id )
    {
        String table_name = "employee";

        ContentValues values = new ContentValues();

        values.put("name","Steve");

        if(mDatabase!=null) {

            int numberOfRows = mDatabase.update(table_name, values, "id=" + id, null);

            if (numberOfRows > 0) {
                Toast.makeText(MainActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error  updating", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(MainActivity.this,"Database is not initialized",Toast.LENGTH_SHORT).show();
        }

    }


    private void insertData()
    {
        String name = "john";

        int id = 1 ;

        String table_name = "employee";

        ContentValues values = new ContentValues();

        values.put("id",id);

        values.put("name",name);

        long rowId = 0 ;

        if(mDatabase!=null)
        {
            rowId = mDatabase.insert(table_name, null, values);

            if (rowId != -1)
            {
                Toast.makeText(MainActivity.this, "Inserted Successfully !!!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Error inserting !!!", Toast.LENGTH_SHORT).show();
            }
        }
        else

        {
            Toast.makeText(MainActivity.this, "Database is null!!!", Toast.LENGTH_SHORT).show();
        }


    }


}
