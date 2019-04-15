package com.example.ss.uicontroltest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btn;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.process_bar);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId())
      {
          case R.id.button:
              int process = progressBar.getProgress();
              process += 10;
              progressBar.setProgress(process);

              if (process >= 100)
              {
                  AlertDialog.Builder dialog = new  AlertDialog.Builder(MainActivity.this);
                  dialog.setTitle("This is Dialog");
                  dialog.setMessage("Something important");
                  dialog.setCancelable(false);
                  dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {

                      }
                  });
                  dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {

                      }
                  });

                  dialog.show();
              }

              break;
              default:
                  break;
      }
    }
}
