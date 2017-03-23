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
import android.widget.CheckBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Medicine;
import iss.nus.medipal.R;
import iss.nus.medipal.activity.EditMedicineActivity;
import iss.nus.medipal.application.App;

/**
 * Created by richard on 16/3/17.
 */
public class MedicineListAdapter extends ArrayAdapter<Medicine> {
    private Context applicationContext;
    private List<Medicine> medicineList;
    private String[] contactTypeDescription;

    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public MedicineListAdapter(Context applicationContext) {
        super(applicationContext, R.layout.medicine_list_layout);
        System.out.println("call_adpt_super");
        //this.applicationContext = applicationContext;
        //medicineList = medicinelst;
        this.applicationContext = applicationContext;
        medicineList = new ArrayList<>();
        //contactTypeDescription = getContext().getResources().getStringArray(R.array.iceType_array);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        System.out.println("call med_getView");
        final Medicine medicine = medicineList.get(position);

        System.out.println("medicine position : " + String.valueOf(position));
        System.out.println("get Medicine id: " + medicine.getMedicineId());

        LayoutInflater inflater =
                (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.medicine_list_layout, parent, false);

        viewHolder = new ViewHolder();

        viewHolder.tv_med_name = (TextView) convertView.findViewById(R.id.tv_med_name);
        viewHolder.tv_med_desc = (TextView) convertView.findViewById(R.id.tv_med_desc);
        viewHolder.tv_categoryID = (TextView) convertView.findViewById(R.id.tv_categoryID);
        viewHolder.tv_reminderID = (TextView) convertView.findViewById(R.id.tv_reminderID);
        viewHolder.ck_IsRemind = (CheckBox) convertView.findViewById(R.id.ck_IsRemind);
        viewHolder.tv_quantity = (TextView) convertView.findViewById(R.id.tv_quantity);
        viewHolder.tv_dosage = (TextView) convertView.findViewById(R.id.tv_dosage);
        viewHolder.tv_consumedQuantity = (TextView) convertView.findViewById(R.id.tv_consumedQuantity);
        viewHolder.tv_thereshold = (TextView) convertView.findViewById(R.id.tv_thereshold);
        viewHolder.tv_issuedDate = (TextView) convertView.findViewById(R.id.tv_issuedDate);
        viewHolder.tv_expireFactor = (TextView) convertView.findViewById(R.id.tv_expireFactor);
        viewHolder.btn_editMedicine = (Button) convertView.findViewById(R.id.btn_editMedicine);

        //convertView.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View v)
        viewHolder.btn_editMedicine.setOnClickListener(v -> {
                Intent intent = new Intent(getContext().getApplicationContext(), EditMedicineActivity.class);
                intent.putExtra("medicine", medicine);
                getContext().getApplicationContext().startActivity(intent);
        });

        convertView.setTag(viewHolder);

        viewHolder.id = medicine.getMedicineId();
        viewHolder.tv_med_name.setText(medicine.getMedicineName());
        viewHolder.tv_med_desc.setText(medicine.getMedicineDescription());
        String stCat = String.valueOf(medicine.getCategoryID());
        viewHolder.tv_categoryID.setText(stCat);
        String stRmdr = String.valueOf(medicine.getReminderID());
        viewHolder.tv_reminderID.setText(stRmdr);
        viewHolder.ck_IsRemind.setChecked(medicine.isRemind());
        viewHolder.tv_quantity.setText(String.valueOf(medicine.getQuantity()));
        viewHolder.tv_dosage.setText(String.valueOf(medicine.getDosage()));
        viewHolder.tv_consumedQuantity.setText(String.valueOf(medicine.getConsumedQuantity()));
        viewHolder.tv_thereshold.setText(String.valueOf(medicine.getThereshold()));
        viewHolder.tv_issuedDate.setText(dateformat.format(medicine.getIssuedDate()));
        viewHolder.tv_expireFactor.setText(String.valueOf(medicine.getExpireFactor()));

        String description = medicine.getMedicineDescription();
        if (TextUtils.isEmpty(description)) {
            description = "-";
        }
        viewHolder.tv_med_desc.setText(description);

        return convertView;
    }

    public void refreshMedicineList() {
        medicineList.clear();
        medicineList.addAll(App.medipal.getMedicineList(getContext().getApplicationContext()));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return medicineList.size();
    }

    static class ViewHolder {
        int id;
        TextView tv_med_name;
        TextView tv_med_desc;
        TextView tv_categoryID;
        TextView tv_reminderID;
        CheckBox ck_IsRemind;
        TextView tv_quantity;
        TextView tv_dosage;
        TextView tv_consumedQuantity;
        TextView tv_thereshold;
        TextView tv_issuedDate;
        TextView tv_expireFactor;
        Button btn_editMedicine;
    }
}
