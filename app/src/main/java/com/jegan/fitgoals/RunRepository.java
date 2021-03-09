package com.jegan.fitgoals;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RunRepository {

    private RunDao runDao;
    private LiveData<List<Run>> allRuns;

    RunRepository(Application application) {
        FitRoomDatabase db = FitRoomDatabase.getDatabase(application);
        runDao = db.runDao();
        allRuns = runDao.getAllRuns();
    }

    LiveData<List<Run>> getAllRuns() {
        return allRuns;
    }

    void insert(Run run) {
        FitRoomDatabase.databaseWriteExecutor.execute(() -> runDao.insert(run));
    }

    void delete(Run run) {
        FitRoomDatabase.databaseWriteExecutor.execute(() -> runDao.delete(run));
    }

    void update(Run run) {
        FitRoomDatabase.databaseWriteExecutor.execute(() -> runDao.update(run));
    }

    void deleteAll() {
        FitRoomDatabase.databaseWriteExecutor.execute(() -> runDao.deleteAll());
    }
}
