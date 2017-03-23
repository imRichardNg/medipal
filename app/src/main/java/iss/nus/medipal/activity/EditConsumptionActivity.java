package iss.nus.medipal.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Consumption;

import iss.nus.medipal.AppFolder.Medicine;
import iss.nus.medipal.R;
import iss.nus.medipal.application.App;

public class EditConsumptionActivity extends AppCompatActivity {

    private Consumption consumption;

    private EditText etConsumedOnDate;
    private EditText etConsumedOnTime;
    private EditText etQuantity;
    private Spinner spnMedicineType;

    private Button btnEditConsumption;
    private Button btnDeleteConsumption;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private Calendar currentCalendar = Calendar.getInstance();

    List<Medicine> medicineList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_consumption);

        consumption = getIntent().getExtras().getParcelable("consumption");
        currentCalendar.setTime(consumption.getConsumedOn());

        etConsumedOnDate = (EditText) findViewById(R.id.et_consumedOnDate);
        etConsumedOnTime = (EditText) findViewById(R.id.et_consumedOnTime);
        etQuantity = (EditText) findViewById(R.id.et_quantity);
        spnMedicineType = (Spinner) findViewById(R.id.spn_medicineType);

        medicineList = App.medipal.getMedicineList(this);
        List<String> spnMedList = new ArrayList<>();
        spnMedList.add("<Select Medicine>");
        for (Medicine medicine : medicineList) {
            spnMedList.add(medicine.getMedicineId(), medicine.toString());
        }
        ArrayAdapter<String> spnMedAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spnMedList);
        spnMedicineType.setAdapter(spnMedAdapter);

        btnEditConsumption = (Button) findViewById(R.id.btn_editConsumption);
        btnDeleteConsumption = (Button) findViewById(R.id.btn_deleteConsumption);

        etConsumedOnDate.setText(dateFormatter.format(currentCalendar.getTime()));
        etConsumedOnDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener onDateSetListener =
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        etConsumedOnDate.setText(dateFormatter.format(calendar.getTime()));
                    };

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(EditConsumptionActivity.this, onDateSetListener,
                            currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
                            currentCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        etConsumedOnTime.setText(timeFormatter.format(currentCalendar.getTime()));
        etConsumedOnTime.setOnClickListener((v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog.OnTimeSetListener onTimeSetListener =
                    (view, hourOfDay, minute) -> {
                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                        etConsumedOnTime.setText(timeFormatter.format(calendar.getTime()));
                    };

            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(EditConsumptionActivity.this, onTimeSetListener,
                            currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE), false);
            timePickerDialog.show();
        }));

        etQuantity.setText(String.valueOf(consumption.getQuantity()));
        spnMedicineType.setSelection(consumption.getMedicineId());

        btnEditConsumption = (Button) findViewById(R.id.btn_editConsumption);
        btnEditConsumption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar selectedDate = Calendar.getInstance();
                Calendar selectedTime = Calendar.getInstance();

                try {
                    selectedDate.setTime(dateFormatter.parse(etConsumedOnDate.getText().toString()));
                    selectedTime.setTime(timeFormatter.parse(etConsumedOnTime.getText().toString()));

                    selectedDate.set(Calendar.HOUR, selectedTime.get(Calendar.HOUR));
                    selectedDate.set(Calendar.MINUTE, selectedTime.get(Calendar.MINUTE));
                    selectedDate.set(Calendar.AM_PM, selectedTime.get(Calendar.AM_PM));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Medicine selectedMedicine= null;

                for (Medicine m : medicineList) {
                    Long id = spnMedicineType.getSelectedItemId();
                    if (id.intValue() == m.getMedicineId()) {
                        selectedMedicine = m;
                    }
                }

                String quantity = etQuantity.getText().toString().trim();
                int quantityInt = TextUtils.isEmpty(quantity) ? 0 : Integer.valueOf(quantity);

                if (isValidConsumption()) {
                    App.medipal.editConsumptoin(consumption.getId(),selectedMedicine.getMedicineId(),quantityInt,selectedDate.getTime(),getApplicationContext());
                    finish();
                }
            }
        });

        btnDeleteConsumption = (Button) findViewById(R.id.btn_deleteConsumption);
        btnDeleteConsumption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.medipal.deleteConsumption(consumption.getId(), getApplicationContext());
                finish();
            }
        });
    }



    private boolean isValidConsumption() {
        etQuantity.setError(null);

        String quantity = etQuantity.getText().toString().trim();
        int quantitylInt = -1;
        if (quantitylInt > 0) {
            if (TextUtils.isEmpty(quantity)) {
                etQuantity.setError("Please set quantity.");
            } else {
                quantitylInt = Integer.valueOf(quantity);

                if (quantitylInt < 1) {
                    etQuantity.setError("Minimum quantity is 1.");
                }
            }
        }

        boolean isValid = true;
        if (spnMedicineType.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select medicine", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return TextUtils.isEmpty(etQuantity.getError()) && isValid;
    }
}

