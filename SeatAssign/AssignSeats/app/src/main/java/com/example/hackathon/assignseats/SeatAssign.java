package com.example.hackathon.assignseats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SeatAssign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_assign);
        //Button b = (Button) findViewById(R.id.button2);
        Button c = (Button) findViewById(R.id.table);
        //b.setBackgroundResource(R.drawable.sq_icon);

//
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeatAssign.this, Pop.class));
            }
        });
    }
}
//    private void showPopup(){
//        Button btn_closepopup=(Button)layout.findViewById(R.id.btn_closePoppup);
//        pwindo=new PopupWindow(layout,480,500,true);
//        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 40);
//        chartContainer1.addView(mChart);
//        btn_closepopup.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                pwindo.dismiss();
//            }
//        });

