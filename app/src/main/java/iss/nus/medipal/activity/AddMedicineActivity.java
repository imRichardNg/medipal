package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.SpinnerObject;
import iss.nus.medipal.R;
import iss.nus.medipal.application.App;

public class AddMedicineActivity extends AppCompatActivity {

    private EditText etMedicine;
    private EditText etMedicineDes;
    private EditText etCategoryID;
    private EditText etReminderID;
    private EditText etQuantity;
    private EditText etDosage;
    private EditText etConsumedQuantity;
    private EditText etThershold;
    private EditText etExpireFactor;
    private EditText etIssuedDate;
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    private Button btnAddMedicine;

    private Spinner spnCategoryType;
    private Spinner spnReminderType;
    private CheckBox ckIsRemind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        etMedicine = (EditText) findViewById(R.id.et_medicineName);
        etMedicineDes = (EditText) findViewById(R.id.et_medicineDescription);
        etCategoryID = null; // (EditText) findViewById(R.id.et_category);
        etReminderID = null; //(EditText) findViewById(R.id.et_reminder);
        etQuantity = (EditText) findViewById(R.id.et_quantity);
        etDosage = (EditText) findViewById(R.id.et_dosage);
        etConsumedQuantity = (EditText) findViewById(R.id.et_consumedQuantity);
        etThershold = (EditText) findViewById(R.id.et_thershold);
        etExpireFactor = (EditText) findViewById(R.id.et_expireFactor);
        etIssuedDate = (EditText) findViewById(R.id.et_IssuedDate);
        spnCategoryType = (Spinner) findViewById(R.id.spn_categoryType);
        spnReminderType = (Spinner) findViewById(R.id.spn_reminderType);
        ckIsRemind = (CheckBox) findViewById(R.id.ck_IsRemind);
        //IsReminder should be checkBox to enable.

        //Bind Category
        loadSpinnerData();

        //Bind Reminder
        loadSpinnerReminderData();

        btnAddMedicine = (Button) findViewById(R.id.btn_addMedicine);
        btnAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    String medicine = etMedicine.getText().toString().trim();
                    String medicineDesc = etMedicineDes.getText().toString().trim();
                    int categoryID = 0; //Integer.parseInt(etCategoryID.getText().toString().trim());
                    int reminderID = 0; //Integer.parseInt(etReminderID.getText().toString().trim());
                    boolean isReminder = ckIsRemind.isChecked();
                    int quantity = Integer.parseInt(etQuantity.getText().toString().trim());
                    int dosage = Integer.parseInt(etDosage.getText().toString().trim());
                    int consumedQuantity = Integer.parseInt(etConsumedQuantity.getText().toString().trim());
                    int thershold = Integer.parseInt(etThershold.getText().toString().trim());
                    int expireFactor = Integer.parseInt(etExpireFactor.getText().toString().trim());
                    String issueDate = etIssuedDate.getText().toString().trim();
                    Date dt = null;
                    try {
                        dt = (dateformat.parse(etIssuedDate.getText().toString().trim()));
                    } catch (ParseException e) {
                        //Display something to user
                    }

                    categoryID = (int) spnCategoryType.getSelectedItemId();
                    System.out.print(spnCategoryType.getSelectedItem().toString());

                    spnCategoryType.getSelectedItemId();

                    reminderID = (int) spnReminderType.getSelectedItemId();
                    System.out.print(spnReminderType.getSelectedItem().toString());

                    App.medipal.addMedicine(medicine, medicineDesc, isReminder, categoryID, reminderID, quantity, dosage, consumedQuantity, thershold, expireFactor, dt, getApplicationContext());

                    //check the condition.
                    Intent intent = new Intent(AddMedicineActivity.this, ManageMedicineActivity.class);
                    startActivity(intent);
                    Toast toast = Toast.makeText(AddMedicineActivity.this, "Medicine Info Added.", Toast.LENGTH_LONG);
                    toast.show();

                    Log.i("AddMedicine", "SUBMIT CLICKED & Added Ok.");
                }
            }
        });
    }

    private void loadSpinnerData() {

        /*List <SpinnerObject> lables = new ArrayList<SpinnerObject>();
        lables.add((new SpinnerObject(1,"Acetaminophen")));
        lables.add((new SpinnerObject(2,"Diclofenac")));
        lables.add((new SpinnerObject(3,"Humulin")));*/

        List<SpinnerObject> lables = App.medipal.getAllCategoryList(getApplicationContext());

        // Creating adapter for spinner
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnCategoryType.setAdapter(dataAdapter);
    }

    private void loadSpinnerReminderData() {

        /*List <SpinnerObject> lables = new ArrayList<SpinnerObject>();
        lables.add((new SpinnerObject(1,"Acetaminophen")));
        lables.add((new SpinnerObject(2,"Diclofenac")));
        lables.add((new SpinnerObject(3,"Humulin")));*/

        List<SpinnerObject> lables = App.medipal.getAllReminderList(getApplicationContext());

        // Creating adapter for spinner
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnReminderType.setAdapter(dataAdapter);
    }

    public boolean isValid() {
        if (etMedicine.getText().toString().isEmpty()) {
            //Toast toast = Toast.makeText(this, "MedicineName cannot be empty.", Toast.LENGTH_SHORT);
            //toast.show();
            etMedicine.setError("MedicineName cannot be empty!");
            return false;
        }
        if (etQuantity.getText().toString().isEmpty()) {
            etQuantity.setError("Quantity cannot be empty!");
            return false;
        }
        if (etDosage.getText().toString().isEmpty()) {
            etQuantity.setError("Dosage cannot be empty!");
            //toast.show();
            return false;
        }
        if (etConsumedQuantity.getText().toString().isEmpty()) {
            etConsumedQuantity.setError("ConsumedQuantity cannot be empty.");
            //toast.show();
            return false;
        }
        if (etThershold.getText().toString().isEmpty()) {
            etThershold.setError("Threshold cannot be empty.");
            //toast.show();
            return false;
        }
        if (etExpireFactor.getText().toString().isEmpty()) {
            etExpireFactor.setError("ExpireFactor cannot be empty.");
            //toast.show();
            return false;
        }
        try {
            Date dt = (dateformat.parse(etIssuedDate.getText().toString().trim()));
            Date todayDate = dateformat.parse(dateformat.format(new Date()));
            if (todayDate.after(dt)) {
                etIssuedDate.setError("Invalid Issued Date!");
                //toast.show();
                return false;
            }
        } catch (ParseException e) {
            etIssuedDate.setError("Date Format Wrong!");
            //toast.show();
            return false;
        }

        return true;
    }

}
