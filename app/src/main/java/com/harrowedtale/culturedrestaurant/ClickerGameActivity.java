package com.harrowedtale.culturedrestaurant;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickerGameActivity extends AppCompatActivity {

    TextView tv_Time, tv_Clicks;
    Button b_Clicks, b_Start;

    CountDownTimer timer;
    int time = 30;

    int clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker_game);

        tv_Time = (TextView) findViewById(R.id.tvTime);
        tv_Clicks = (TextView) findViewById(R.id.tvClicks);

        b_Clicks = (Button) findViewById(R.id.bClick);
        b_Start = (Button) findViewById(R.id.bStart);

        b_Start.setEnabled(true);
        b_Clicks.setEnabled(false);

        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                time--;
                tv_Time.setText("Time: " + time);
            }

            @Override
            public void onFinish() {
                b_Start.setEnabled(true);
                b_Clicks.setEnabled(false);
                tv_Time.setText("Time: 0");
            }
        };

        b_Clicks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                clicks++;
                tv_Clicks.setText("Clicks: " + clicks);
            }
        });

        b_Start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                timer.start();
                b_Start.setEnabled(false);
                b_Clicks.setEnabled(true);

                clicks = 0;
                time = 30;

                tv_Time.setText("Time: " + time);
                tv_Clicks.setText("Clicks: " + clicks);
            }
        });
    }
}
