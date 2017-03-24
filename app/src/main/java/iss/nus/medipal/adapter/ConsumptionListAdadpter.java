package iss.nus.medipal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Category;
import iss.nus.medipal.AppFolder.Consumption;
import iss.nus.medipal.AppFolder.Medicine;
import iss.nus.medipal.R;
import iss.nus.medipal.activity.EditConsumptionActivity;
import iss.nus.medipal.application.App;

public class ConsumptionListAdadpter extends ArrayAdapter<Consumption> {
    private Context applicationContext;
    private List<Consumption> consumptionList;

    // for searching
    private ArrayList<Consumption> arraylist;

    public ConsumptionListAdadpter(Context applicationContext) {
        super(applicationContext, R.layout.consumption_layout);
        this.applicationContext = applicationContext;

        // for searching
        this.arraylist = new ArrayList<Consumption>();
        this.consumptionList = new ArrayList<Consumption>();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConsumptionListAdadpter.ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.consumption_layout, parent, false);
            viewHolder = new ConsumptionListAdadpter.ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.btn_editConsumption = (Button) convertView.findViewById(R.id.btn_editConsumption);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ConsumptionListAdadpter.ViewHolder) convertView.getTag();
        }
        final Consumption consumption = consumptionList.get(position);
        viewHolder.tv_name.setText(consumption.toString());
        viewHolder.btn_editConsumption.setOnClickListener(v -> {
            Intent intent = new Intent(getContext().getApplicationContext(), EditConsumptionActivity.class);
            intent.putExtra("consumption", consumption);
            getContext().getApplicationContext().startActivity(intent);
        });
        return convertView;
    }

    public void refreshConsumptionList() {
        consumptionList.clear();
        consumptionList.addAll(App.medipal.getConsumptionList(this.applicationContext));

        List<Medicine> lMedicines = App.medipal.getMedicineList(this.applicationContext);
        List<Category> lCategories = App.medipal.getCategoryList(this.applicationContext);

        for (Consumption consumption : consumptionList) {
            for (Medicine medicine : lMedicines) {
                if (consumption.getMedicineId() == medicine.getMedicineId()) {
                    consumption.setMedicine(medicine);
                }
                for (Category category : lCategories) {
                    if (medicine.getCategoryID() == category.getCategoryID()) {
                        consumption.setCategory(category);
                    }
                }
            }
        }

        notifyDataSetChanged();

        this.arraylist.addAll(consumptionList);
    }

    @Override
    public int getCount() {
        return consumptionList.size();
    }

    static class ViewHolder {
        int id;
        TextView tv_name;
        Button btn_editConsumption;
    }

    // for searching
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        consumptionList.clear();

        if (charText.length() == 0) {
            consumptionList.addAll(arraylist);
        } else {
            for (Consumption consumption : arraylist) {
                if (consumption.toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                    consumptionList.add(consumption);
                }
            }
        }
        notifyDataSetChanged();
    }
}

