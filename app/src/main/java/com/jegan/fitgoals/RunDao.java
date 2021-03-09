package com.jegan.fitgoals;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RunDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Run run);

    @Update
    void update(Run run);

    @Delete
    void delete(Run run);

    @Query("DELETE FROM run_table")
    void deleteAll();

    @Query("SELECT * FROM run_table")
    LiveData<List<Run>> getAllRuns();
}


