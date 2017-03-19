package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Health_Bio;
import iss.nus.medipal.AppFolder.Medipal;

import iss.nus.medipal.R;
import iss.nus.medipal.adaptor.HealthBio_Adaptor;



import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


/**
 * Created by nus on 11/3/17.
 */

public class ManageHealthBio extends AppCompatActivity {
    private ListView helathBioListView;

    Medipal medipal;
    List<Health_Bio> healthBioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_bio);// this is compoulsory


        helathBioListView= (ListView) findViewById(R.id.litHealthBio);

        List<String> healbio = new ArrayList<>();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);


        medipal= new Medipal();
        healthBioList = medipal.getHealthBio(this);// calling function from App folder healthbio


        FloatingActionButton fltAddHealthBio = (FloatingActionButton) findViewById(R.id.fltAddHealthBio);
        fltAddHealthBio.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(getApplicationContext(), AddHealthBio.class);
                startActivity(intent);
            }
        });

    }



    @Override
    protected void onStart() {

//        healthBioList=medipal.getHealthBio(this);
     /**   healthBioList = new ArrayList<Health_Bio>();
        for(int i = 1; i< 20; i++){
            Health_Bio a = new Health_Bio();
            a.setCondition("AAA" + i);
            a.setStrartDate(new Date());
            a.setConditionType("A");
            healthBioList.add(a);
        }*/
        HealthBio_Adaptor healthBio_adaptor= new HealthBio_Adaptor(healthBioList,this);
        helathBioListView.setAdapter(healthBio_adaptor);
        super.onStart();
    }


}
