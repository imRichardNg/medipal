package iss.nus.medipal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Appointment;
import iss.nus.medipal.R;
import iss.nus.medipal.activity.EditAppointmentActivity;
import iss.nus.medipal.application.App;

public class AppointmentListAdapter extends ArrayAdapter<Appointment>{
    private Context applicationContext;
    private List<Appointment> appointmentList;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private Calendar calendar = Calendar.getInstance();

    public AppointmentListAdapter(Context applicationContext) {
        super(applicationContext, R.layout.appointment_layout);
        this.applicationContext = applicationContext;
        appointmentList = new ArrayList<>();
      }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = new ViewHolder();
        final Appointment appointment = appointmentList.get(position);

        LayoutInflater inflater =
                (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.appointment_layout, parent, false);

        viewHolder.id = appointment.getId();
        viewHolder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
        viewHolder.tv_location_title = (TextView)convertView.findViewById(R.id.tv_location_title);
        viewHolder.tv_appointmentDate = (TextView) convertView.findViewById(R.id.tv_appointmentDate);
        viewHolder.tv_appointmentTime = (TextView) convertView.findViewById(R.id.tv_appointmentTime);
        viewHolder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
        viewHolder.tv_description_title = (TextView) convertView.findViewById(R.id.tv_description_title);
        viewHolder.btn_editAppointment = (Button) convertView.findViewById(R.id.btn_editAppointment);

        viewHolder.btn_editAppointment.setOnClickListener(v -> {
            Intent intent = new Intent(getContext().getApplicationContext(), EditAppointmentActivity.class);
            intent.putExtra("appointment", appointment);
            getContext().getApplicationContext().startActivity(intent);
        });

        /*convertView.setOnClickListener(v -> {
            System.out.println("Editing.." + appointment);
            Intent intent = new Intent(getContext().getApplicationContext(), EditAppointmentActivity.class);
            intent.putExtra("appointment", appointment);
            getContext().getApplicationContext().startActivity(intent);
        });*/

        convertView.setTag(viewHolder);

        viewHolder.tv_location.setText(String.valueOf(appointment.getLocation()));

        calendar.setTime(appointment.getAppointmentDT());
        viewHolder.tv_appointmentDate.setText(dateFormatter.format(calendar.getTime()));
        viewHolder.tv_appointmentTime.setText(timeFormatter.format(calendar.getTime()));

        viewHolder.tv_description.setText(String.valueOf(appointment.getDescription()));
        return convertView;
    }

    public void refreshAppointmentList() {
        appointmentList.clear();
        appointmentList.addAll(App.medipal.getAppointmentList(getContext().getApplicationContext()));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return appointmentList.size();
    }

    static class ViewHolder {
        int id;
        TextView tv_location;
        TextView tv_location_title;
        TextView tv_appointmentDate;
        TextView tv_appointmentTime;
        TextView tv_description;
        TextView tv_description_title;

        Button btn_editAppointment;
    }
}
