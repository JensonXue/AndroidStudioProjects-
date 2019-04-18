package com.example.ss.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownLoadBinder downLoadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downLoadBinder = (MyService.DownLoadBinder)iBinder;
            downLoadBinder.startDownload();;
            downLoadBinder.getProcess();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button) findViewById(R.id.start_service);
        btnStart.setOnClickListener(this);
        Button btnStop = (Button) findViewById(R.id.stop_service);
        btnStop.setOnClickListener(this);
        Button btnBind = (Button) findViewById(R.id.bind_service);
        btnBind.setOnClickListener(this);
        Button btnUnBind = (Button) findViewById(R.id.unbind_service);
        btnUnBind.setOnClickListener(this);
        Button btnSatrtIntent = (Button) findViewById(R.id.intentservice);
        btnSatrtIntent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.intentservice:
                Intent startIntentService = new Intent(this, MyIntentService.class);
                startService(startIntentService);
                break;
        }
    }
}
