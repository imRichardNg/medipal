package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import iss.nus.medipal.R;

public class MainActivity extends AppCompatActivity {

    private Button btnManageICE;
    private Button btnManageReminder;
    private Button btnManageAppointment;
    private Button btnManageConsumption;

    private Button btnPersonalBio;
    private Button btnMeasurement;
    private Button btnHealthBio;
    private Button btnManageCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageICE = (Button) findViewById(R.id.btnManageICE);
        btnManageICE.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ManageICEActivity.class)));

        btnManageReminder = (Button) findViewById(R.id.btnManageReminder);
        btnManageReminder.setOnClickListener((v -> startActivity(new Intent(getApplicationContext(), ManageReminderActivity.class))));

        btnManageAppointment = (Button) findViewById(R.id.btnManageAppointment);
        btnManageAppointment.setOnClickListener((v -> startActivity(new Intent(getApplicationContext(), ManageAppointmentActivity.class))));

        btnPersonalBio = (Button) findViewById(R.id.btnPersonalBio);
        btnPersonalBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPersonlBio();
            }
        });

        btnMeasurement = (Button) findViewById(R.id.btnMeasurement);
        btnMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMeasurement();
            }
        });

        btnHealthBio = (Button) findViewById(R.id.btnHealthBio);
        btnHealthBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHealthBio();
            }
        });

        btnManageConsumption = (Button) findViewById(R.id.btnManageConsumption);
        btnManageConsumption.setOnClickListener((v -> startActivity(new Intent(getApplicationContext(), ManageConsumptionActivity.class))));


        btnManageCategory = (Button) findViewById(R.id.btnManageCategory);
        btnManageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startManageCategory();
            }
        });
    }

    public void startPersonlBio() {
        Intent intent = new Intent(this, ManagePersonalBioActivity.class);
        startActivity(intent);
    }

    public void startMeasurement() {
        Intent intent = new Intent(this, ManageMeasurementActivity.class);
        startActivity(intent);
    }

    public void startHealthBio() {
        Intent intent = new Intent(this, ManageHealthBio.class);
        startActivity(intent);
    }

    public void startManageCategory() {
        Intent intent = new Intent(this, ManageCategoryActivity.class);
        startActivity(intent);
    }
}
