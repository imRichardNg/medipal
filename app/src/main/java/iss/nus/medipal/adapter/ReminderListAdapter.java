package iss.nus.medipal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Reminder;
import iss.nus.medipal.R;
import iss.nus.medipal.activity.EditReminderActivity;
import iss.nus.medipal.application.App;

/**
 * Created by richard on 17/3/17.
 */
public class ReminderListAdapter extends ArrayAdapter<Reminder> {
    private Context applicationContext;
    private List<Reminder> reminderList;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private Calendar calendar = Calendar.getInstance();

    public ReminderListAdapter(Context applicationContext) {
        super(applicationContext, R.layout.reminder_layout);
        this.applicationContext = applicationContext;
        reminderList = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = new ViewHolder();
        final Reminder reminder = reminderList.get(position);

        LayoutInflater inflater =
                (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.reminder_layout, parent, false);

        viewHolder.id = reminder.getId();
        viewHolder.tv_startDate = (TextView) convertView.findViewById(R.id.tv_startDate);
        viewHolder.tv_startTime = (TextView) convertView.findViewById(R.id.tv_startTime);
        viewHolder.tv_frequency = (TextView) convertView.findViewById(R.id.tv_frequency);
        viewHolder.tv_frequency_title = (TextView) convertView.findViewById(R.id.tv_frequency_title);
        viewHolder.tv_interval = (TextView) convertView.findViewById(R.id.tv_interval);
        viewHolder.tv_interval_title = (TextView) convertView.findViewById(R.id.tv_interval_title);

        convertView.setOnClickListener(v -> {
            System.out.println("Editing.." + reminder);
            Intent intent = new Intent(getContext().getApplicationContext(), EditReminderActivity.class);
            intent.putExtra("reminder", reminder);
            getContext().getApplicationContext().startActivity(intent);
        });

        convertView.setTag(viewHolder);

        calendar.setTime(reminder.getStartDate());
        viewHolder.tv_startDate.setText(dateFormatter.format(calendar.getTime()));
        viewHolder.tv_startTime.setText(timeFormatter.format(calendar.getTime()));

        if (reminder.getFrequency() < 1) {
            viewHolder.tv_frequency_title.setVisibility(View.GONE);
            viewHolder.tv_frequency.setVisibility(View.GONE);
            viewHolder.tv_interval_title.setVisibility(View.GONE);
            viewHolder.tv_interval.setVisibility(View.GONE);
        } else {
            viewHolder.tv_frequency.setText(String.valueOf(reminder.getFrequency()));
            viewHolder.tv_interval.setText(String.valueOf(reminder.getInterval()));
        }

        return convertView;
    }

    public void refreshReminderList() {
        reminderList.clear();
        reminderList.addAll(App.medipal.getReminderList(getContext().getApplicationContext()));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return reminderList.size();
    }

    static class ViewHolder {
        int id;
        TextView tv_startDate;
        TextView tv_startTime;
        TextView tv_frequency;
        TextView tv_frequency_title;
        TextView tv_interval;
        TextView tv_interval_title;
    }
}
