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
import iss.nus.medipal.adapter.ReminderListAdapter;

public class ManageReminderActivity extends AppCompatActivity {
    ReminderListAdapter reminderListAdapter;

    private TextView tvEmptyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvEmptyValue = (TextView) findViewById(R.id.tv_empty_value);

        final ListView reminderList = (ListView) findViewById(R.id.lv_reminder_list);
        reminderListAdapter = new ReminderListAdapter(this);
        reminderList.setAdapter(reminderListAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddReminderActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        reminderListAdapter.refreshReminderList();
        tvEmptyValue.setVisibility(reminderListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
