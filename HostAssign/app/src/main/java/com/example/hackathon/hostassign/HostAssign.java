package com.example.hackathon.hostassign;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class HostAssign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_assign);

//        final Spinner s = (Spinner) findViewById(R.id.servers_list);
        final Button c = (Button) findViewById(R.id.table);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HostAssign.this, Pop.class));

                c.setBackgroundColor((int) (Color.parseColor("#ed1c24")));
                c.setText("TN");
//                final String tn = "TN";
//                final String tc = "TC";
//                final String sd = "SD";
//                final String dh = "DH";

//                Button close = (Button) findViewById(R.id.submit);
//                close.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View popup) {
//                        String value = s.getSelectedItem().toString();
//                        switch (value) {
//                            case ("Tim Ngo"):
//                        {
//                            c.setBackgroundColor((int) (Color.parseColor("#ed1c24")));
//                            c.setText(tn);
//                        }
//                            case ("Truman Chan"):
//                        {
//                            c.setBackgroundColor((int) (Color.parseColor("#22b14c")));
//                            c.setText(tc);
//                        }
//                            case ("Sean Darsie"):
//                        {
//                            c.setBackgroundColor((int) (Color.parseColor("#ffc90e")));
//                            c.setText(sd);
//                        }
//                            case ("Damia Hill"):
//                        {
//                            c.setBackgroundColor((int) (Color.parseColor("#00a2e8")));
//                            c.setText(dh);
//                        }
//                        }
//                    }
//                });
            }
        });
    }
}