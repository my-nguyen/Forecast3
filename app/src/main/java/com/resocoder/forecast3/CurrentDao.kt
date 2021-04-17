package com.resocoder.forecast3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(current: Current)

    @Query("SELECT * FROM current WHERE id=$CURRENT_ID")
    fun getCurrentMetric(): LiveData<CurrentMetric>

    @Query("SELECT * FROM current WHERE id=$CURRENT_ID")
    fun getCurrentImperial(): LiveData<CurrentImperial>
}