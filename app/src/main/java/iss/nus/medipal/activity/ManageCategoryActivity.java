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
import iss.nus.medipal.adapter.CategoryListAdapter;
import iss.nus.medipal.adapter.ReminderListAdapter;

public class ManageCategoryActivity extends AppCompatActivity {
    CategoryListAdapter categoryListAdapter;
    private TextView tvEmptyValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvEmptyValue = (TextView) findViewById(R.id.tv_empty_value);
        setSupportActionBar(toolbar);
        System.out.println("Inside on create of ManageCategory Activity");
        final ListView categoryList = (ListView) findViewById(R.id.lv_category_list);
        categoryListAdapter = new CategoryListAdapter(this);
        categoryList.setAdapter(categoryListAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        categoryListAdapter.refreshCategoryList();
        tvEmptyValue.setVisibility(categoryListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
