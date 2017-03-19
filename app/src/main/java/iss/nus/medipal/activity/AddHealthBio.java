package iss.nus.medipal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import iss.nus.medipal.AppFolder.Medipal;
import iss.nus.medipal.AppFolder.Health_Bio;
import iss.nus.medipal.R;

public class AddHealthBio extends AppCompatActivity {

    private EditText txtCondition;
    private EditText txtStartDate;
    private Spinner txtConditonType;
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private Button submit;
    Medipal medipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__health__bio);
        txtCondition= (EditText) findViewById(R.id.healthBioCondition);
        txtConditonType=(Spinner) findViewById(R.id.healthBioConditionType);
        txtStartDate = (EditText) findViewById(R.id.healthBioStartDate);
        submit=(Button)findViewById(R.id.btnHealthBioSubmit);
    }

    public void healthBioSubmit(View view){

        Health_Bio health_bio = new Health_Bio();
        medipal = new Medipal();

        if(isValid() == true) {
            health_bio.setCondition(txtCondition.getText().toString());
            try {
                health_bio.setStrartDate(dateformat.parse(txtStartDate.getText().toString()));
            } catch (ParseException e) {
                // To be notified to user
            }
            //health_bio.setConditionType(txtConditonType.getText().toString());
            health_bio.setConditionType(txtConditonType.getSelectedItem().toString());

            medipal.addHEALTHBIO(health_bio, this);



            Intent intent = new Intent(AddHealthBio.this, MainActivity.class);
            startActivity(intent);
            Toast toast = Toast.makeText(this, "Record is successfully Added.", Toast.LENGTH_LONG);
            toast.show();

            Log.i("HealthBIO", "SUBMIT CLICKED.");
        }
    }

    public boolean isValid(){
        if(txtCondition.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Condition cannot be empty.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if(txtStartDate.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Date cannot be empty.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        return true;
    }
}
