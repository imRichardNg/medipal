package iss.nus.medipal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import iss.nus.medipal.R;

public class MainActivity extends AppCompatActivity {

    private Button btnManageICE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageICE = (Button) findViewById(R.id.btnManageICE);
        btnManageICE.setOnClickListener(new View.OnClickListener() {
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
