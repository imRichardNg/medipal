package iss.nus.medipal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import iss.nus.medipal.R;
import iss.nus.medipal.application.App;

public class AddICEActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etContactNo;
    private Spinner spnContactType;
    private EditText etDescription;

    private Button btnAddICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ice);

        etName = (EditText) findViewById(R.id.et_name);
        etContactNo = (EditText) findViewById(R.id.et_contactNo);
        etDescription = (EditText) findViewById(R.id.et_description);

        spnContactType = (Spinner) findViewById(R.id.spn_contactType);
        final ArrayAdapter<CharSequence> spnContactTypeAdapter =
                ArrayAdapter.createFromResource(this, R.array.iceType_array, android.R.layout.simple_spinner_item);
        spnContactTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnContactType.setAdapter(spnContactTypeAdapter);

        btnAddICE = (Button) findViewById(R.id.btn_addICe);
        btnAddICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String contactNo = etContactNo.getText().toString().trim();
                int contactType = (int) spnContactType.getSelectedItemPosition();
                String description = etDescription.getText().toString().trim();

                if (isValidICEContact()) {
                    App.medipal.addICE(name, contactNo, contactType, description, getApplicationContext());
                    finish();
                }
            }
        });
    }

    private boolean isValidICEContact() {
        etName.setError(null);
        etContactNo.setError(null);

        if (TextUtils.isEmpty(etName.getText().toString().trim())) {
            etName.setError("Name cannot be empty.");
        }

        if (!PhoneNumberUtils.isGlobalPhoneNumber(etContactNo.getText().toString().trim())) {
            etContactNo.setError("Please enter a valid contact number. eg. +6583331111");
        }

        return TextUtils.isEmpty(etContactNo.getError()) && TextUtils.isEmpty(etName.getError());
    }
}
