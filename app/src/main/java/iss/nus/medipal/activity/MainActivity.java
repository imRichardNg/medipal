package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import iss.nus.medipal.R;

public class MainActivity extends AppCompatActivity {

    private Button btnManageICE;
    private Button btnManageReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageICE = (Button) findViewById(R.id.btnManageICE);
        btnManageICE.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ManageICEActivity.class)));

        btnManageReminder = (Button) findViewById(R.id.btnManageReminder);
        btnManageReminder.setOnClickListener((v -> startActivity(new Intent(getApplicationContext(), ManageReminderActivity.class))));
    }
}
