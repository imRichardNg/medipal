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

import iss.nus.medipal.R;
import iss.nus.medipal.application.App;

public class AddReminderActivity extends AppCompatActivity {

    private EditText etStartDate;
    private EditText etStartTime;
    private EditText etFrequency;
    private EditText etInterval;

    private Button btnAddReminder;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private Calendar currentCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        etStartDate = (EditText) findViewById(R.id.et_startDate);
        etStartTime = (EditText) findViewById(R.id.et_startTime);
        etFrequency = (EditText) findViewById(R.id.et_frequency);
        etInterval = (EditText) findViewById(R.id.et_interval);
        btnAddReminder = (Button) findViewById(R.id.btn_addReminder);

        etStartDate.setText(dateFormatter.format(currentCalendar.getTime()));
        etStartDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener onDateSetListener =
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(year, month, dayOfMonth);
                        etStartDate.setText(dateFormatter.format(calendar.getTime()));
                    };

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(AddReminderActivity.this, onDateSetListener,
                            currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
                            currentCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        etStartTime.setText(timeFormatter.format(currentCalendar.getTime()));
        etStartTime.setOnClickListener((v -> {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog.OnTimeSetListener onTimeSetListener =
                    (view, hourOfDay, minute) -> {
                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                        etStartTime.setText(timeFormatter.format(calendar.getTime()));
                    };

            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(AddReminderActivity.this, onTimeSetListener,
                            currentCalendar.get(Calendar.HOUR_OF_DAY), currentCalendar.get(Calendar.MINUTE), false);
            timePickerDialog.show();
        }));

        btnAddReminder.setOnClickListener(v -> {
            if (isValidReminder()) {
                Calendar selectedDate = Calendar.getInstance();
                Calendar selectedTime = Calendar.getInstance();

                try {
                    selectedDate.setTime(dateFormatter.parse(etStartDate.getText().toString()));
                    selectedTime.setTime(timeFormatter.parse(etStartTime.getText().toString()));

                    selectedDate.set(Calendar.HOUR, selectedTime.get(Calendar.HOUR));
                    selectedDate.set(Calendar.MINUTE, selectedTime.get(Calendar.MINUTE));
                    selectedDate.set(Calendar.AM_PM, selectedTime.get(Calendar.AM_PM));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String frequency = etFrequency.getText().toString().trim();
                int frequencyInt = Integer.valueOf(frequency);

                String interval = etInterval.getText().toString().trim();
                int intervalInt = TextUtils.isEmpty(interval) || frequencyInt < 1 ? 0 : Integer.valueOf(interval);

                App.medipal.addReminder(selectedDate.getTime(), frequencyInt, intervalInt, getApplicationContext());
                finish();
            }
        });
    }

    private boolean isValidReminder() {
        etFrequency.setError(null);
        etInterval.setError(null);

        String frequency = etFrequency.getText().toString().trim();
        int frequencyInt = -1;
        if (!TextUtils.isEmpty(frequency)) {
            if (!TextUtils.isDigitsOnly(frequency)) {
                etFrequency.setError("Only numbers allowed.");
            } else {
                frequencyInt = Integer.valueOf(frequency);

                if (frequencyInt < 1) {
                    etFrequency.setError("Minimum frequency is 1.");
                }
            }
        } else {
            etFrequency.setError("Minimum frequency is 1.");
        }

        String interval = etInterval.getText().toString().trim();
        int intervalInt = -1;
        if (frequencyInt > 1) {
            if (TextUtils.isEmpty(interval)) {
                etInterval.setError("Please set interval.");
            } else {
                intervalInt = Integer.valueOf(interval);

                if (intervalInt < 1) {
                    etInterval.setError("Minimum interval is 1.");
                }
            }
        }

        return TextUtils.isEmpty(etFrequency.getError()) && TextUtils.isEmpty(etInterval.getError());
    }
}
