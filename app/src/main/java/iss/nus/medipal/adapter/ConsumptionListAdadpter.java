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

import iss.nus.medipal.AppFolder.Consumption;
import iss.nus.medipal.R;
import iss.nus.medipal.activity.AddConsumptionActivity;
import iss.nus.medipal.application.App;

public class ConsumptionListAdadpter extends ArrayAdapter<Consumption>{
    private Context applicationContext;
    private List<Consumption> consumptionList;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private Calendar calendar = Calendar.getInstance();

    public ConsumptionListAdadpter(Context applicationContext) {
        super(applicationContext, R.layout.consumption_layout);
        this.applicationContext = applicationContext;
        consumptionList = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = new ViewHolder();
        final Consumption consumption = consumptionList.get(position);

        LayoutInflater inflater =
                (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.consumption_layout, parent, false);

        viewHolder.id = consumption.getId();
        viewHolder.tv_medicine = (TextView) convertView.findViewById(R.id.tv_medicine);
        viewHolder.tv_medicine_title = (TextView)convertView.findViewById(R.id.tv_medicine_title);
        viewHolder.tv_consumedOnDate = (TextView) convertView.findViewById(R.id.tv_consumedOnDate);
        viewHolder.tv_consumedOnTime = (TextView) convertView.findViewById(R.id.tv_consumedOnTime);
        viewHolder.tv_quantity = (TextView) convertView.findViewById(R.id.tv_quantity);
        viewHolder.tv_quantity_title = (TextView) convertView.findViewById(R.id.tv_quantity_title);
        viewHolder.btn_editConsumption = (Button) convertView.findViewById(R.id.btn_editConsumption);

       /* viewHolder.btn_editConsumption.setOnClickListener(v -> {
            Intent intent = new Intent(getContext().getApplicationContext(), EditAppointmentActivity.class);
            intent.putExtra("consumption", consumption);
            getContext().getApplicationContext().startActivity(intent);
        });*/

        convertView.setTag(viewHolder);

        // have to check in here
        viewHolder.tv_medicine.setText(String.valueOf(consumption.getMedicineId()));
        viewHolder.tv_quantity.setText(String.valueOf(consumption.getQuantity()));

        calendar.setTime(consumption.getConsumedOn());
        viewHolder.tv_consumedOnDate.setText(dateFormatter.format(calendar.getTime()));
        viewHolder.tv_consumedOnTime.setText(timeFormatter.format(calendar.getTime()));

        return convertView;
    }

    public void refreshConsumptionList() {
        consumptionList.clear();
        consumptionList.addAll(App.medipal.getConsumptionList(getContext().getApplicationContext()));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return consumptionList.size();
    }

    static class ViewHolder {
        int id;
        TextView tv_medicine;
        TextView tv_medicine_title;
        TextView tv_quantity;
        TextView tv_quantity_title;
        TextView tv_consumedOnDate;
        TextView tv_consumedOnTime;

        Button btn_editConsumption;
    }
}

