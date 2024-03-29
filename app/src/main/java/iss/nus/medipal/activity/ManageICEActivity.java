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
import iss.nus.medipal.adapter.ICEContactListAdapter;

public class ManageICEActivity extends AppCompatActivity {
    private ICEContactListAdapter iceContactListAdapter;

    private TextView tvEmptyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvEmptyValue = (TextView) findViewById(R.id.tv_empty_value);

        final ListView iceContactsList = (ListView) findViewById(R.id.lv_iceContacts_list);
        iceContactListAdapter = new ICEContactListAdapter(this);
        iceContactsList.setAdapter(iceContactListAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddICEActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        iceContactListAdapter.refreshICEContacts();
        tvEmptyValue.setVisibility(iceContactListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
