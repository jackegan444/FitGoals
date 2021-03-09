package com.jegan.fitgoals;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class PreviousRuns extends AppCompatActivity {
    private RecyclerView runRecyclerView;
    private RunRecycleAdap adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RunViewModel runViewModel;
    public static final String RUN_INDEX = "com.jegan.fitgoals.RUN_INDEX";
    public static final int DELETE_RUN_REQUEST_CODE = 1;
    private List<Run> runs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_runs);

        runRecyclerView = findViewById(R.id.run_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        runRecyclerView.setLayoutManager(layoutManager);
        adapter = new RunRecycleAdap(this);
        runRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RunRecycleAdap.OnItemCLickListener() {
            @Override
            public void onItemClick(int position) {
                launchRunDetails(position);
            }
        });

        runViewModel = new ViewModelProvider(this).get(RunViewModel.class);

        runViewModel.getAllRuns().observe(this, new Observer<List<Run>>() {
            @Override
            public void onChanged(@Nullable final List<Run> runs) {
                // Update the cached copy of the words in the adapter.
                adapter.setRuns(runs);
                setRuns(runs);
            }
        });


    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }

    public void launchRunDetails(int position) {
        Intent launchRunDetails = new Intent(this, RunDetails.class);
        launchRunDetails.putExtra(RUN_INDEX, position);
        startActivityForResult(launchRunDetails, DELETE_RUN_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        int position = data.getExtras().getInt(RunDetails.EXTRA_REPLY);
        if (requestCode == DELETE_RUN_REQUEST_CODE && resultCode == RESULT_OK) {
            Run run = runs.get(position);
            runViewModel.delete(run);
            adapter.notifyDataSetChanged();
            Toast toast = Toast.makeText(getApplicationContext(), "Run deleted", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 60);
            toast.show();
        }
    }
}