package iss.nus.medipal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import iss.nus.medipal.R;

public class MainActivity extends AppCompatActivity {

    private Button btnManageMedication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageMedication = (Button) findViewById(R.id.btnManageMedication);
        btnManageMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startManageICE();
            }
        });


    }

    public void startManageICE() {
        Intent intent = new Intent(this, ManageICEActivity.class);
        startActivity(intent);
    }
}
