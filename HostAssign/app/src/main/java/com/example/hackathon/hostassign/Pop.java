package com.example.hackathon.hostassign;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.hackathon.hostassign.R.layout.*;

/**
 * Created by hackathon on 11/4/17.
 */

public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(popwindow);

        DisplayMetrics dm =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.6));

        Button close = (Button)findViewById(R.id.submit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View popup) {
                finish();
            }
        });
    }
}
