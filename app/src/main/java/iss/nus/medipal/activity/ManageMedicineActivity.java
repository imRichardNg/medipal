package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Medicine;
import iss.nus.medipal.AppFolder.Medipal;
import iss.nus.medipal.R;
import iss.nus.medipal.adapter.ICEContactListAdapter;
import iss.nus.medipal.adapter.MedicineListAdapter;
import iss.nus.medipal.notification.MedipalNotificationManager;

public class ManageMedicineActivity extends AppCompatActivity {
    private MedicineListAdapter medicineListAdapter;
    private ListView medicineListView;
    List<Medicine> medicineList;
    Medicine med ;
    private TextView tvEmptyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Call_Manage_Medicine_Activity");

        setContentView(R.layout.activity_manage_medicine);
        System.out.println("Call_medicine_activity_res");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvEmptyValue = (TextView) findViewById(R.id.tv_empty_value);
        System.out.println("tv_empty_value");

        final ListView medicineList = (ListView) findViewById(R.id.lv_medicine_list);
        System.out.println("lv_medicine_list");
        medicineListAdapter = new MedicineListAdapter(this);
        medicineList.setAdapter(medicineListAdapter);
        System.out.println("Call_med_adptr");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("medicine_acty_btn_click");
                Intent intent = new Intent(getApplicationContext(), AddMedicineActivity.class);
                startActivity(intent);
                System.out.println("call med_intent");
            }
        });
    }

    /*@Override
    protected void onStart() {

        MedicineListAdapter medicine_adaptor= new MedicineListAdapter(this);
        System.out.println("create med_apt");
        medicineListView.setAdapter(medicine_adaptor);
        System.out.println("setAdptr");
        super.onStart();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        medicineListAdapter.refreshMedicineList();
        tvEmptyValue.setVisibility(medicineListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);

        MedipalNotificationManager.refreshAlarm(getApplicationContext());
    }
}
