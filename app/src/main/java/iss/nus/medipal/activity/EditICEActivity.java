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

import iss.nus.medipal.AppFolder.ICEContact;
import iss.nus.medipal.R;
import iss.nus.medipal.application.App;

public class EditICEActivity extends AppCompatActivity {

    private ICEContact iceContact;

    private EditText etName;
    private EditText etContactNo;
    private Spinner spnContactType;
    private EditText etDescription;

    private Button btnEditICE;
    private Button btnRemoveICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ice);

        iceContact = getIntent().getExtras().getParcelable("iceContact");

        etName = (EditText) findViewById(R.id.et_name);
        etContactNo = (EditText) findViewById(R.id.et_contactNo);
        etDescription = (EditText) findViewById(R.id.et_description);

        etName.setText(iceContact.getName());
        etContactNo.setText(iceContact.getContactNo());
        etDescription.setText(iceContact.getDescription());

        spnContactType = (Spinner) findViewById(R.id.spn_contactType);
        final ArrayAdapter<CharSequence> spnContactTypeAdapter =
                ArrayAdapter.createFromResource(this, R.array.iceType_array, android.R.layout.simple_spinner_item);
        spnContactTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnContactType.setAdapter(spnContactTypeAdapter);
        spnContactType.setSelection(iceContact.getContactType());

        btnEditICE = (Button) findViewById(R.id.btn_editICE);
        btnEditICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String contactNo = etContactNo.getText().toString().trim();
                int contactType = spnContactType.getSelectedItemPosition();
                String description = etDescription.getText().toString().trim();

                if (isValidICEContact()) {
                    App.medipal.editICE(iceContact.getId(), name, contactNo, contactType, description, getApplicationContext());
                    finish();
                }
            }
        });

        btnRemoveICE = (Button) findViewById(R.id.btn_removeICE);
        btnRemoveICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.medipal.deleteICE(iceContact.getId(), getApplicationContext());
                finish();
            }
        });
    }

    private boolean isValidICEContact() {
        if (TextUtils.isEmpty(etName.getText().toString().trim())) {
            etName.setError("Name cannot be empty.");
        }

        if (!PhoneNumberUtils.isGlobalPhoneNumber(etContactNo.getText().toString().trim())) {
            etContactNo.setError("Please enter a valid contact number. eg. +6583331111");
        }

        return (TextUtils.isEmpty(etContactNo.getError()) && TextUtils.isEmpty(etName.getError()));
    }
}
