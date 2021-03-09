package com.jegan.fitgoals;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RunDetails extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.jegan.fitgoals.REPLY";
    public static final String RUN_INDEX = "com.jegan.fitgoals.RUN_INDEX";
    public static final int EDIT_RUN_REQUEST_CODE = 1;
    private RunViewModel runViewModel;
    private List<Run> runs;
    private Run displayRun;
    private TextView runName;
    private TextView runDate;
    private TextView runTime;
    private TextView runDist;
    private Toolbar toolbar;
    private boolean deleting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_details);
        toolbar = findViewById(R.id.run_details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        runName = findViewById(R.id.display_run_name_textview);
        runDate = findViewById(R.id.display_run_date_textview);
        runTime = findViewById(R.id.display_run_time_textview);
        runDist = findViewById(R.id.display_run_dist_textview);
        runViewModel = new ViewModelProvider(this).get(RunViewModel.class);

        runViewModel.getAllRuns().observe(this, new Observer<List<Run>>() {
            @Override
            public void onChanged(@Nullable final List<Run> runs) {
                setRuns(runs);
                if(deleting == false) {
                    displayRun = runs.get(getIntent().getExtras().getInt("com.jegan.fitgoals.RUN_INDEX"));
                    runName.setText(displayRun.getRunName());
                    runDate.setText(displayRun.getRunDate());
                    runTime.setText(displayRun.getRunTime());
                    runDist.setText(displayRun.getRunDistance());
                }
            }
        });



    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_run_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.run_settings_edit:
                Intent launchRunDetails = new Intent(this, EditRun.class);
                launchRunDetails.putExtra(RUN_INDEX, getIntent().getExtras().getInt("com.jegan.fitgoals.RUN_INDEX"));
                startActivityForResult(launchRunDetails, EDIT_RUN_REQUEST_CODE);

                break;
            case R.id.run_settings_delete:
                deleting = true;
                Intent replyIntent = new Intent();
                int index = getIntent().getExtras().getInt("com.jegan.fitgoals.RUN_INDEX");
                replyIntent.putExtra(EXTRA_REPLY, index);
                setResult(RESULT_OK, replyIntent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int toastText;

        if (requestCode == EDIT_RUN_REQUEST_CODE && resultCode == RESULT_OK) {
            toastText = R.string.run_saved;
            displayRun.setRunName(data.getStringExtra(EditRun.RUN_NAME));
            displayRun.setRunTime(data.getStringExtra(EditRun.RUN_TIME));
            displayRun.setRunDistance(data.getStringExtra(EditRun.RUN_DIST));
            runViewModel.update(displayRun);

        } else {
            toastText = R.string.empty_not_saved;
        }

        Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 60);
        toast.show();
    }


}