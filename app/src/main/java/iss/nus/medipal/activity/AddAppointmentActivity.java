package iss.nus.medipal.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import iss.nus.medipal.R;
import iss.nus.medipal.application.App;
import iss.nus.medipal.asyncTask.AddAppointment;

public class AddAppointmentActivity extends AppCompatActivity {

    private EditText etLocation;
    private EditText etAppointmentDate;
    private EditText etAppointmentTime;
    private EditText etDescription;

    private Button btnAddAppointment;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private Calendar currentCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        etLocation=(EditText) findViewById(R.id.et_location);
        etAppointmentDate = (EditText) findViewById(R.id.et_appointmentDate);
        etAppointmentTime = (EditText) findViewById(R.id.et_appointmentTime);
        etDescription = (EditText) findViewById(R.id.et_description);
        btnAddAppointment = (Button) findViewById(R.id.btn_addAppointment);

        etAppointmentDate.setText(dateFormatter.format(currentCalendar.getTime()));
        etAppointmentDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener onDateSetListener =
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        etAppointmentDate.setText(dateFormatter.format(calendar.getTime()));
                    };

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(AddAppointmentActivity.this, onDateSetListener,
                            currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
                            currentCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        etAppointmentTime.setText(timeFormatter.format(currentCalendar.getTime()));
        etAppointmentTime.setOnClickListener((v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog.OnTimeSetListener onTimeSetListener =
                    (view, hourOfDay, minute) -> {
                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                        etAppointmentTime.setText(timeFormatter.format(calendar.getTime()));
                    };

            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(AddAppointmentActivity.this, onTimeSetListener,
                            currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE), false);
            timePickerDialog.show();
        }));

        btnAddAppointment.setOnClickListener(v -> {

            Calendar selectedDate = Calendar.getInstance();
            Calendar selectedTime = Calendar.getInstance();

            try {
                selectedDate.setTime(dateFormatter.parse(etAppointmentDate.getText().toString()));
                selectedTime.setTime(timeFormatter.parse(etAppointmentTime.getText().toString()));

                selectedDate.set(Calendar.HOUR, selectedTime.get(Calendar.HOUR));
                selectedDate.set(Calendar.MINUTE, selectedTime.get(Calendar.MINUTE));
                selectedDate.set(Calendar.AM_PM, selectedTime.get(Calendar.AM_PM));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String location = etLocation.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (isValidAppointment()) {
                App.medipal.addAppointment(location,selectedDate.getTime(),description,getApplicationContext());
                finish();
            }
        });
    }

    private boolean isValidAppointment() {
        etLocation.setError(null);

        if (TextUtils.isEmpty(etLocation.getText().toString().trim())) {
            etLocation.setError("Location cannot be empty.");
        }

        return TextUtils.isEmpty(etLocation.getError());
    }
}
