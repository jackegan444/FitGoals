package com.jegan.fitgoals;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RunViewModel extends AndroidViewModel {

    private RunRepository runRepository;

    private LiveData<List<Run>> allRuns;

    public RunViewModel (Application application) {
        super(application);
        runRepository = new RunRepository(application);
        allRuns = runRepository.getAllRuns();
    }

    LiveData<List<Run>> getAllRuns() { return allRuns; }

    public void insert(Run run) {
        runRepository.insert(run);
    }

    public void delete(Run run) {
        runRepository.delete(run);
    }

    public void update(Run run) {
        runRepository.update(run);
    }

    public void deleteAll() {
        runRepository.deleteAll();
    }
}
