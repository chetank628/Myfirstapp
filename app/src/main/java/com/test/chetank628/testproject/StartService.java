package com.test.chetank628.testproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StartService extends Activity {
    String msg = "service app ";
    Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
        Log.d(msg, "The onCreate() event");
        start = (Button)findViewById(R.id.button2_startservice);
        stop = (Button)findViewById(R.id.button_stopservice);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startService(new Intent(getBaseContext(), MyService.class));
                }

        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopService(new Intent(getBaseContext(), MyService.class));
            }

        });
    }
}
