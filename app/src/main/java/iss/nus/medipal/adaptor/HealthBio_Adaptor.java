package iss.nus.medipal.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Health_Bio;
import iss.nus.medipal.R;


/**
 * Created by nus on 12/3/17.
 */

public class HealthBio_Adaptor extends ArrayAdapter<Health_Bio> implements View.OnClickListener {
    private List<Health_Bio> healthBioList;
    private Context context;
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private  int lsPstion = -1; //lastpostion

    public HealthBio_Adaptor(List<Health_Bio> healthBioList,Context context) {
        super(context, R.layout.healthbio_rowlayout, healthBioList);
        this.healthBioList= healthBioList;
        this.context= context;
    }

    public static class ViewHealthBio{
        TextView txtCondition;
        TextView txtStartDate;
    }


    @Override
    public void onClick(View view) {

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Health_Bio health_bio= getItem(position);
        ViewHealthBio viewHealthBio;
        if (convertView==null){
            viewHealthBio=new ViewHealthBio();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.healthbio_rowlayout,parent,false);
            viewHealthBio.txtCondition=(TextView)convertView.findViewById(R.id.rowCondition);
            viewHealthBio.txtStartDate=(TextView)convertView.findViewById(R.id.rowStartDate);
            convertView.setTag(viewHealthBio);

        }
        else {
            viewHealthBio=(ViewHealthBio)convertView.getTag();
        }

        lsPstion=position;
        viewHealthBio.txtCondition.setText(health_bio.getCondition());
        viewHealthBio.txtStartDate.setText(dateformat.format(health_bio.getStrartDate()));
        return convertView;
    }

}
