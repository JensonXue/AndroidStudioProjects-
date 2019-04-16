package com.example.ss.litepalapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.BufferedReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreate = (Button) findViewById(R.id.create_database);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase();
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unkonw");
                book.save();
            }
        });

        Button btnUpdate = (Button) findViewById(R.id.update_data);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                /*book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unkonw");
                book.save();

                book.setPrice(10.99);
                book.save();*/

                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?","The Lost symbol","Dan Brown");

                /*
                Book book = new Book();
                book.setToDefault("pages");
                book.updateAll();
                */
            }
        });

        Button btnDelete = (Button) findViewById(R.id.delete_data);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class, "price < ?", "15");
            }
        });

        Button btnQuery = (Button) findViewById(R.id.query_data);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book: books)
                {
                    Log.d("MainActivity", "Book id "+book.getName()+" "+book.getAuthor());
                }

                /*
                List<Book> books = DataSupport.select("name","author").find(Book.class);
                List<Book> books = DataSupport.where("pages > ?","400").find(Book.class);
                List<Book> books = DataSupport.limit("3").find(Book.class);
                List<Book> books = DataSupport.limit("3").offser(1).find(Book.class);
                List<Book> books = DataSupport.select("name","author").where("pages > ?","400")
                  .limit("3").offser(1).find(Book.class);
                Cursor c = DataSupport.findBySQL("select * from Book where pages > ? and price < ?", "400", "20");
                */
            }
        });
    }
}
