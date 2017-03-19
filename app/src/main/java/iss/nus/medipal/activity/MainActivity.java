package iss.nus.medipal.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import iss.nus.medipal.R;
import iss.nus.medipal.notification.MedipalNotificationManager;

public class MainActivity extends AppCompatActivity {

    private Button btnManageICE;
    private Button btnManageReminder;

    private static final int MY_PERMISSION_SET_ALARM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageICE = (Button) findViewById(R.id.btnManageICE);
        btnManageICE.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ManageICEActivity.class)));

        btnManageReminder = (Button) findViewById(R.id.btnManageReminder);
        btnManageReminder.setOnClickListener((v -> startActivity(new Intent(getApplicationContext(), ManageReminderActivity.class))));

        int alarmPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SET_ALARM);
        if (alarmPermission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Permission not granted for set alarm", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SET_ALARM}, MY_PERMISSION_SET_ALARM);
        } else {
            MedipalNotificationManager.refreshAlarm(this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_SET_ALARM: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission granted for set alarm", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MedipalNotificationManager.refreshAlarm(this);
    }
}
