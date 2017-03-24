package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import iss.nus.medipal.R;
import iss.nus.medipal.adapter.AppointmentListAdapter;
import iss.nus.medipal.notification.MedipalNotificationManager;

public class ManageAppointmentActivity extends AppCompatActivity {

    private AppointmentListAdapter appointmentListAdapter;

    private TextView tvEmptyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_appointment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvEmptyValue = (TextView) findViewById(R.id.tv_empty_value);

        final ListView appointmentList = (ListView) findViewById(R.id.lv_appointment_list);
        appointmentListAdapter = new AppointmentListAdapter(this);
        appointmentList.setAdapter(appointmentListAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddAppointmentActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        appointmentListAdapter.refreshAppointmentList();
        tvEmptyValue.setVisibility(appointmentListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);

        MedipalNotificationManager.refreshAlarm(getApplicationContext());
    }
}
