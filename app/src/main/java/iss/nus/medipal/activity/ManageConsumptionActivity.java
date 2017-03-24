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
import iss.nus.medipal.adapter.ConsumptionListAdadpter;
import iss.nus.medipal.notification.MedipalNotificationManager;

public class ManageConsumptionActivity extends AppCompatActivity {
    private ConsumptionListAdadpter consumptionListAdadpter;

    private TextView tvEmptyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_consumption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvEmptyValue = (TextView) findViewById(R.id.tv_empty_value);

        final ListView consumptionList = (ListView) findViewById(R.id.lv_consumption_list);
        consumptionListAdadpter = new ConsumptionListAdadpter(this);
        consumptionList.setAdapter(consumptionListAdadpter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddConsumptionActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        consumptionListAdadpter.refreshConsumptionList();
        tvEmptyValue.setVisibility(consumptionListAdadpter.getCount() == 0 ? View.VISIBLE : View.GONE);

        MedipalNotificationManager.refreshAlarm(getApplicationContext());
    }
}

