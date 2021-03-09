package com.jegan.fitgoals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RunningHome extends AppCompatActivity {

    public static final int NEW_RUN_ACTIVITY_REQUEST_CODE = 1;
    private RunViewModel runViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_home);
        runViewModel = new ViewModelProvider(this).get(RunViewModel.class);
    }

    public void launchLogARun(View view) {
        Intent launchLogARun = new Intent(this, LogARun.class);
        startActivityForResult(launchLogARun, NEW_RUN_ACTIVITY_REQUEST_CODE);
    }

    public void launchPrevRuns(View view) {
        Intent launchPrevRuns = new Intent(this, PreviousRuns.class);
        startActivity(launchPrevRuns);
    }

    public void launchRacePredict(View view) {
        Intent launchRacePredict = new Intent(this, RacePredictor.class);
        startActivity(launchRacePredict);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int toastText;

        if (requestCode == NEW_RUN_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            toastText = R.string.run_saved;
            String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
            Run run = new Run(data.getStringExtra(LogARun.RUN_NAME), currentDate, data.getStringExtra(LogARun.RUN_TIME), data.getStringExtra(LogARun.RUN_DIST));
            runViewModel.insert(run);

        } else {
            toastText = R.string.empty_not_saved;
        }

        Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 60);
        toast.show();
    }

}
