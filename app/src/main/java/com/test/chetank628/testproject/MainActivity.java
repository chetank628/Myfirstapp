package com.test.chetank628.testproject;

import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jaredrummler.android.device.DeviceName;
import com.jaredrummler.android.processes.ProcessManager;
import com.jaredrummler.android.processes.models.AndroidAppProcess;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
       Button b,logs,Tasks;
      String cpuid=null;
       int cores;
    List<String> RunningTaskInfo = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
               b = (Button)findViewById(R.id.button_generatelog);
             logs=(Button)findViewById(R.id.button2_logs);
        Tasks=(Button)findViewById(R.id.button_tasklist);
      final   String deviceName = DeviceName.getDeviceName();

        ///get device details



           BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        final String name = mBluetoothAdapter.getName();

        if(deviceName.equals(Build.MODEL)) {   // inner class
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();

                }
            });
        }

        else{
            // inner class
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,deviceName,Toast.LENGTH_LONG).show();

                }
            });}
        try {
            Process proc = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStream is = proc.getInputStream();
            cores= Runtime.getRuntime().availableProcessors();
              System.out.print(cores);
            cpuid =is.toString();


        }
        catch (IOException e) {
            Log.e("TAG", "------ getCpuInfo " + e.getMessage());
        }

        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("devicename",deviceName);
                Log.v("manufactures",android.os.Build.MANUFACTURER);
                Log.v("model", Build.MODEL);
                Log.v("ID",Build.ID);
                Log.v("Board",Build.BOARD);
                //Log.v("CPU",cpuid);
                //Log.v("No of cores", );

            }
        });

        Tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String top;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) { //For versions less than lollipop
                    ActivityManager am = ((ActivityManager) getSystemService(ACTIVITY_SERVICE));
                    List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(5);
                    top = taskInfo.get(0).topActivity.getPackageName();
                    Log.v("My process", "top app = " + top);
                }else{ //For versions Lollipop and above


                    List<AndroidAppProcess> processes = ProcessManager.getRunningForegroundApps(getApplicationContext());
                    Collections.sort(processes, new ProcessManager.ProcessComparator());
                    for (AndroidAppProcess process : processes) {
                        if (process.foreground) {
                            top =process.name;
                            Log.v("My process",top);
                        }
                    }
                }
            }
        });
        }



    }

