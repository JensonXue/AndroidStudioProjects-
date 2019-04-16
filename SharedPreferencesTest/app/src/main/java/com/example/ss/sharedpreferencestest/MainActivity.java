package com.example.ss.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = (Button) findViewById(R.id.save_data);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String name  = pref.getString("name","");
        int age = pref.getInt("age",0);
        boolean married = pref.getBoolean("married", false);

        Toast.makeText(MainActivity.this, "name:"+name+" age:"+age+" married:"+married, Toast.LENGTH_SHORT).show();
    }
}
