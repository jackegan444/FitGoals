package com.jegan.fitgoals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchRunning(View view) {
        Intent launchRun = new Intent(this, RunningHome.class);
        startActivity(launchRun);
    }

    public void launchStrengthTrain(View view) {
        Intent launchStrength = new Intent(this, StrengthHome.class);
        startActivity(launchStrength);
    }
}
