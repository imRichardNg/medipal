package iss.nus.medipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import iss.nus.medipal.AppFolder.Medipal;
import iss.nus.medipal.AppFolder.Personal_Bio;
import iss.nus.medipal.R;

/**
 * Created by nus on 11/3/17.
 */

public class ManagePersonalBioActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtdob;
    private EditText txtidNo;
    private EditText txtaddress;
    private EditText txtpostalCode;
    private EditText txtheight;
    private EditText txtbloodType;
    private Button submit;
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    Medipal medipal;
    private boolean recFlag = false;
    private int recID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_bio);// this is compoulsory


        txtName = (EditText) findViewById(R.id.personalBioName);
        txtdob = (EditText) findViewById(R.id.personalBioDob);
        txtidNo = (EditText) findViewById(R.id.personalBioIdNo);
        txtaddress =(EditText) findViewById(R.id.personalBioAddress);
        txtpostalCode = (EditText) findViewById(R.id.personalBioPostalCode);
        txtheight = (EditText) findViewById(R.id.personalBioHeight);
        txtbloodType = (EditText) findViewById(R.id.personalBioBloodType);
        submit = (Button) findViewById(R.id.btnsubmit);

        medipal = new Medipal();
        List<Personal_Bio> personalBios = medipal.getPersonal_Bio(this);
        if( personalBios.size() > 0){
            recFlag= true;
            Personal_Bio personalBio = personalBios.get(0);
            recID=personalBio.getPersonalId();
            txtName.setText(personalBio.getPersonalName());
            txtdob.setText(dateformat.format(personalBio.getDob()));
            txtidNo.setText(personalBio.getIdNo());
            txtaddress.setText(personalBio.getAddress());
            txtpostalCode.setText(personalBio.getPostalcode());
            txtheight.setText(String.valueOf(personalBio.getHeight()));
            txtbloodType.setText(personalBio.getBloodType());

        }
        Log.i("PERSONAL BIO SIZE", String.valueOf(personalBios.size()));
    }

    public void personalBioSubmit(View view){

        Personal_Bio personal_bio = new Personal_Bio();
    personal_bio.setPersonalName(txtName.getText().toString());
        try{
            personal_bio.setDob(dateformat.parse(txtdob.getText().toString()));
        }catch (ParseException e){
            // To be notified to user
        }
        personal_bio.setIdNo(txtidNo.getText().toString());
        personal_bio.setAddress(txtaddress.getText().toString());
        personal_bio.setPostalcode(txtpostalCode.getText().toString());
        personal_bio.setHeight(Integer.parseInt(txtheight.getText().toString()));
        personal_bio.setBloodType(txtbloodType.getText().toString());

        if(isValid() == true){
            if (recFlag==true){
                personal_bio.setPersonalId(recID);
                medipal.UpdatePERSONALBIO(personal_bio,this);
            }else {
                medipal.addPERSONALBIO(personal_bio, this);
            }
            Log.i("PERSONALBIO", "SUBMIT CLICKED.");
          Intent intent = new Intent(ManagePersonalBioActivity.this, MainActivity.class);
            startActivity(intent);
            Toast toast = Toast.makeText(this, "Record Added.", Toast.LENGTH_LONG);
            toast.show();
        }


    }

    public boolean isValid(){
        if(txtName.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Name cannot be empty.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }
}
