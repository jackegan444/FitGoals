package com.jegan.fitgoals;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Run.class}, version = 1, exportSchema = false)
public abstract class FitRoomDatabase extends RoomDatabase {

    public abstract RunDao runDao();

    private static volatile FitRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FitRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FitRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FitRoomDatabase.class, "run_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}