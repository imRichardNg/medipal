package iss.nus.medipal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iss.nus.medipal.AppFolder.ICEContact;
import iss.nus.medipal.R;
import iss.nus.medipal.activity.EditICEActivity;
import iss.nus.medipal.application.App;

/**
 * Created by richard on 16/3/17.
 */
public class ICEContactListAdapter extends ArrayAdapter<ICEContact> {
    private Context applicationContext;
    private List<ICEContact> iceContactList;
    private String[] contactTypeDescription;

    public ICEContactListAdapter(Context applicationContext) {
        super(applicationContext, R.layout.ice_contacts_layout);
        this.applicationContext = applicationContext;
        iceContactList = new ArrayList<>();
        contactTypeDescription = getContext().getResources().getStringArray(R.array.iceType_array);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final ICEContact iceContact = iceContactList.get(position);

        LayoutInflater inflater =
                (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.ice_contacts_layout, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        viewHolder.tv_contactNo = (TextView) convertView.findViewById(R.id.tv_contactNo);
        viewHolder.tv_contactType = (TextView) convertView.findViewById(R.id.tv_contactType);
        viewHolder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
        viewHolder.tv_description_title = (TextView) convertView.findViewById(R.id.tv_description_title);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), EditICEActivity.class);
                intent.putExtra("iceContact", iceContact);
                getContext().getApplicationContext().startActivity(intent);
            }
        });

        convertView.setTag(viewHolder);

        viewHolder.id = iceContact.getId();
        viewHolder.tv_name.setText(iceContact.getName());
        viewHolder.tv_contactNo.setText(iceContact.getContactNo());
        viewHolder.tv_contactType.setText(contactTypeDescription[iceContact.getContactType()]);

        String description = iceContact.getDescription();
        if (TextUtils.isEmpty(description)) {
            description = "-";
        }
        viewHolder.tv_description.setText(description);

        return convertView;
    }

    public void refreshICEContacts() {
        iceContactList.clear();
        iceContactList.addAll(App.medipal.getICEContacts(getContext().getApplicationContext()));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return iceContactList.size();
    }

    static class ViewHolder {
        int id;
        TextView tv_name;
        TextView tv_contactNo;
        TextView tv_contactType;
        TextView tv_description;
        TextView tv_description_title;
    }
}
