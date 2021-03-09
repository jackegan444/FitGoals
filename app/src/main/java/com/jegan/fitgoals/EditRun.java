package com.jegan.fitgoals;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.List;
import java.util.Locale;

public class EditRun extends AppCompatActivity {

    public static final String RUN_NAME = "com.jegan.fitgoals.RUN_NAME";
    public static final String RUN_TIME = "com.jegan.fitgoals.RUN_TIME";
    public static final String RUN_DIST = "com.jegan.fitgoals.RUN_DIST";

    private EditText rNameEditText;
    private NumberPicker rHoursPicker;
    private NumberPicker rMinsPicker;
    private NumberPicker rSecsPicker;
    private EditText rDistEditText;
    private Button logButton;
    private RunViewModel runViewModel;
    private List<Run> runs;
    private Run displayRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_run);
        runViewModel = new ViewModelProvider(this).get(RunViewModel.class);

        runViewModel.getAllRuns().observe(this, new Observer<List<Run>>() {
            @Override
            public void onChanged(@Nullable final List<Run> runs) {
                setRuns(runs);
                displayRun = runs.get(getIntent().getExtras().getInt("com.jegan.fitgoals.RUN_INDEX"));
                rNameEditText.setText(displayRun.getRunName());
                if (displayRun.getRunTime().charAt(1) == ':') {
                    rHoursPicker.setValue(Integer.parseInt(displayRun.getRunTime().substring(0, 1)));
                    rMinsPicker.setValue(Integer.parseInt(displayRun.getRunTime().substring(2, 4)));
                    rSecsPicker.setValue(Integer.parseInt(displayRun.getRunTime().substring(5, 7)));
                } else {
                    rHoursPicker.setValue(Integer.parseInt(displayRun.getRunTime().substring(0, 2)));
                    rMinsPicker.setValue(Integer.parseInt(displayRun.getRunTime().substring(3, 5)));
                    rSecsPicker.setValue(Integer.parseInt(displayRun.getRunTime().substring(6, 8)));
                }
                rDistEditText.setText(displayRun.getRunDistance().substring(0, displayRun.getRunDistance().length() - 2));
            }
        });

        rNameEditText = findViewById(R.id.erun_name_edittext);

        rHoursPicker = findViewById(R.id.erun_time_hour);
        rHoursPicker.setMinValue(0);
        rHoursPicker.setMaxValue(99);
        rMinsPicker = findViewById(R.id.erun_time_min);
        rMinsPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format(Locale.getDefault(), "%02d", i);
            }
        });
        rMinsPicker.setMinValue(0);
        rMinsPicker.setMaxValue(59);
        rSecsPicker = findViewById(R.id.erun_time_sec);
        rSecsPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format(Locale.getDefault(), "%02d", i);
            }
        });
        rSecsPicker.setMinValue(0);
        rSecsPicker.setMaxValue(59);

        rDistEditText = findViewById(R.id.erun_distance_edittext);
        logButton = findViewById(R.id.elog_run_button);
    }

    public void saveRunInput(View view) {
        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(rNameEditText.getText()) || TextUtils.isEmpty(rDistEditText.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String name = rNameEditText.getText().toString();
            replyIntent.putExtra(RUN_NAME, name);
            String time = (rHoursPicker.getValue() + ":" + String.format(Locale.getDefault(), "%02d", rMinsPicker.getValue()) + ":" + String.format(Locale.getDefault(), "%02d", rSecsPicker.getValue()));
            replyIntent.putExtra(RUN_TIME, time);
            String dist = (rDistEditText.getText().toString() + " km");
            replyIntent.putExtra(RUN_DIST, dist);
            setResult(RESULT_OK, replyIntent);
        }
        finish();

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }




    }