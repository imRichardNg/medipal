package iss.nus.medipal.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Category;
import iss.nus.medipal.R;
import iss.nus.medipal.application.App;

public class EditCategoryActivity extends AppCompatActivity {
    private Category category;

    private EditText etCategory;
    private EditText etCode;
    private EditText etCategoryDescription;
    private EditText etReminder;

    private Button btnEditCategory;
    private Button btnDeleteCategory;

    private Calendar currentCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        category = getIntent().getExtras().getParcelable("category");

        etCategory = (EditText) findViewById(R.id.et_category);
        etCode = (EditText) findViewById(R.id.et_code);
        etCategoryDescription = (EditText) findViewById(R.id.et_categoryDescription);
        etReminder = (EditText) findViewById(R.id.et_isReminder);

        btnEditCategory.setOnClickListener(v -> {

        String cat1 = etCategory.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String description = etCategoryDescription.getText().toString().trim();
        boolean isReminder = true;

            App.medipal.editCategory(-1, cat1, code, description, isReminder, getApplicationContext());
            finish();
          });


        btnDeleteCategory.setOnClickListener(v -> {
            App.medipal.deleteCategory(category.getCategoryID(), getApplicationContext());
            finish();
        });
    }

}
