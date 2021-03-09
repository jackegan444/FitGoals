package com.jegan.fitgoals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.Locale;

public class LogARun extends AppCompatActivity {

    public static final String RUN_NAME = "com.jegan.fitgoals.RUN_NAME";
    public static final String RUN_TIME = "com.jegan.fitgoals.RUN_TIME";
    public static final String RUN_DIST = "com.jegan.fitgoals.RUN_DIST";

    private EditText rNameEditText;
    private NumberPicker rHoursPicker;
    private NumberPicker rMinsPicker;
    private NumberPicker rSecsPicker;
    private EditText rDistEditText;
    private Button logButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_a_run);
        rNameEditText = findViewById(R.id.run_name_edittext);

        rHoursPicker = findViewById(R.id.run_time_hour);
        rHoursPicker.setMinValue(0);
        rHoursPicker.setMaxValue(99);
        rMinsPicker = findViewById(R.id.run_time_min);
        rMinsPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format(Locale.getDefault(), "%02d", i);
            }
        });
        rMinsPicker.setMinValue(0);
        rMinsPicker.setMaxValue(59);
        rSecsPicker = findViewById(R.id.run_time_sec);
        rSecsPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format(Locale.getDefault(), "%02d", i);
            }
        });
        rSecsPicker.setMinValue(0);
        rSecsPicker.setMaxValue(59);

        rDistEditText = findViewById(R.id.run_distance_edittext);
        logButton = findViewById(R.id.save_run_button);
    }

    public void logRunInput(View view) {
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
}