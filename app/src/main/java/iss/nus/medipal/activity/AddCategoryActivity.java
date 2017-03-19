package iss.nus.medipal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import iss.nus.medipal.R;
import iss.nus.medipal.application.App;

public class AddCategoryActivity extends AppCompatActivity {


    private EditText etCategory;
    private EditText etCode;
    private EditText etCategoryDescription;
    private EditText etReminder;

    private Button btnAddCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        etCategory = (EditText) findViewById(R.id.et_category);
        etCode = (EditText) findViewById(R.id.et_code);
        etCategoryDescription = (EditText) findViewById(R.id.et_categoryDescription);
        etReminder = (EditText) findViewById(R.id.et_isReminder);

        btnAddCategory = (Button) findViewById(R.id.btn_addCategory);
        System.out.println("Inside Add CategoryActivity");
        btnAddCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Inside Add Category Onclick");

                String category = etCategory.getText().toString().trim();
                String code = etCode.getText().toString().trim();
                String description = etCategoryDescription.getText().toString().trim();
                boolean isReminder = true;

                App.medipal.addCategory(category, code, description, isReminder, getApplicationContext());
                System.out.println("After addCategory call");
                finish();
            }
        });
    }

}
