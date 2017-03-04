package iss.nus.medipal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import iss.nus.medipal.R;
import iss.nus.medipal.application.App;
import iss.nus.medipal.AppFolder.ICE;

public class AddICEActivity extends AppCompatActivity {

    private EditText etName;
    private Button btnAddICE;
    private Button btnGet;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ice);

        etName = (EditText) findViewById(R.id.et_name);
        btnAddICE = (Button) findViewById(R.id.btnAddICE);
        btnGet = (Button) findViewById(R.id.btnGet);

        btnAddICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.medipal.addICE(etName.getText().toString().trim(), getApplicationContext());
                finish();
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ICE> listIce = App.medipal.getICE(getApplicationContext());

                for (int i=0; i<listIce.size(); i++) {
                    ICE ice = listIce.get(i);

                    // print to console
                    System.out.println(ice);
                }
            }
        });
    }


}
