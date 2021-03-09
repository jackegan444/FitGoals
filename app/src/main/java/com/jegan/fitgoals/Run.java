package com.jegan.fitgoals;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "run_table")
public class Run {

    @PrimaryKey(autoGenerate = true)
    public int runId;

    @NonNull
    @ColumnInfo(name = "run_name")
    private String runName;

    @ColumnInfo(name = "run_date")
    private String runDate;

    @ColumnInfo(name = "run_time")
    private String runTime;

    @ColumnInfo(name = "run_distance")
    private String runDistance;



    public Run(String runName, String runDate, String runTime, String runDistance) {
        this.runName = runName;
        this.runDate = runDate;
        this.runTime = runTime;
        this.runDistance = runDistance;
    }

    public int getRunId(){return this.runId;}

    public String getRunName(){return this.runName;}

    public String getRunDate(){return this.runDate;}

    public String getRunTime(){return this.runTime;}

    public String getRunDistance(){return this.runDistance;}

    public void setRunName(String runName){this.runName = runName;}

    public void setRunDate(String runDate){this.runDate = runDate;}

    public void setRunTime(String runTime){this.runTime = runTime;}

    public void setRunDistance(String runDistance){this.runDistance = runDistance;}
}
