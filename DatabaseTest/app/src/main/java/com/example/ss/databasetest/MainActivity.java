package com.example.ss.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private  MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        Button btnCreate= (Button) findViewById(R.id.create_database);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });

        Button btnAdd = (Button) findViewById(R.id.add_data);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci code");
                values.put("author", "Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book", null, values);

                db.execSQL("insert into Book(name, author, pages, price) values(?,?,?,?)",
                        new String[]{"No name", "Dan Brown", "510", "19.95"});
            }
        });

        Button btnUpdate = (Button) findViewById(R.id.update_data);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.96);
                db.update("Book",values, "name = ?", new String[]{"The Da Vinci code"});

                db.execSQL("update Book Set price = ? where name = ?",
                        new String[]{"10.99", "No name"});
            }
        });

        Button btnDelete = (Button) findViewById(R.id.delete_data);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                db.delete("Book", "pages > ?", new String[]{"500"});

                db.execSQL("delete from Book where pages > ?", new String[]{"500"});
            }
        });

        Button btnQuery = (Button) findViewById(R.id.query_data);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst())
                {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double prices = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d("MainActivity", "name is "+name);
                        Log.d("MainActivity", "author is "+author);
                        Log.d("MainActivity", "pages is "+pages);
                        Log.d("MainActivity", "prices is "+prices);
                    }while (cursor.moveToNext());

                }
                cursor.close();
            }
        });
    }
}
